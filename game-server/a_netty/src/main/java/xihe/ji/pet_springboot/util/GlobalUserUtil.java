package xihe.ji.pet_springboot.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;
import xihe.ji.pet_springboot.dao.MessageMapper;
import xihe.ji.pet_springboot.pojo.BackMessage;
import xihe.ji.pet_springboot.pojo.Message;
import xihe.ji.pet_springboot.pojo.userInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class GlobalUserUtil {


    //保存全局的  连接上服务器的客户
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor
            .INSTANCE);
    public static Map<String,ChannelId> USERMAP =new HashMap<>();

    private static final List<String> keys=Arrays.asList("fromId","toId","msg","game","type");

    public static void sendAdminMsg(String msg){
        for(Channel channel:channels){
            channel.writeAndFlush(new TextWebSocketFrame(msg));
        }
    }

    public static Map<String,Object> sendMsg(String msg, MessageMapper mapper, ChannelId id){
        Message message=new Message();
        Map<String,Object> result=new HashMap<>();
        JSONObject jsonObject= null;
        try {
            jsonObject=JSONObject.fromObject(msg);
        }catch (Exception e){
            result.put("flag",false);
            result.put("msg","json数据异常");
            return result;
        }
        if(!checkKek(jsonObject)){
            result.put("flag",false);
            result.put("msg","不存在关键的key,请校验发送的数据");
            return result;
        }
        String toId= jsonObject.getString("toId");
        String game= jsonObject.getString("game");
        String url= MessageUtil.Server.codeOf(game);
        userInfo toUser = TransformationUtil.toBean(UrlConnect.get(url+"my/userInfoId?id="+toId),userInfo.class);
        userInfo fromUser = TransformationUtil.toBean(UrlConnect.get(url+"my/userInfoId?id="+jsonObject.getString("fromId")),userInfo.class);
        ChannelId fromId=USERMAP.get(fromUser.getToken());
//        if(id!=null){
//            if(fromId!=id){
//                return null;
//            }
//        }
        //封装数据库存储消息
        message.setFromid(fromUser.getToken());
        message.setToid(toUser.getToken());
        message.setMsgtype(jsonObject.getString("type"));
        message.setMsgcontent(xihe.ji.pet_springboot.utils.SensitiveWord.filterMsg(jsonObject.getString("msg")));
        message.setMsgtimestamp(String.valueOf(System.currentTimeMillis()));
        //设置反馈消息
        BackMessage backMessage=new BackMessage();
        //来至那个游戏
        backMessage.setGame(jsonObject.getString("game"));
        //发送的用户信息
        backMessage.setFromId(jsonObject.getString("fromId"));
        backMessage.setToId(jsonObject.getString("toId"));
        backMessage.setImg(fromUser.getImg());
        backMessage.setName(fromUser.getName());
        message.setOthermsg(JSONObject.fromObject(backMessage).toString());
        if(!USERMAP.containsKey(toUser.getToken())){
            //增加到数据库 等到用户连接上来做消息推动
            message.setPushtype(1);
            result.put("flag",true);
            result.put("msg",message.getMsgcontent());
            mapper.insertSelective(message);
            return result;
        }
        System.out.println(toUser.getToken());
        try {
            System.out.println(USERMAP.get(toUser.getToken()));
            channels.find(USERMAP.get(toUser.getToken())).writeAndFlush(new TextWebSocketFrame(getJsonMessage(message).toString()));
            message.setPushtype(2);
        }catch (Exception e){
            // 推送失败
            channels.remove(channels.find(USERMAP.get(toUser.getToken())));
            USERMAP.remove(toUser.getToken());
            message.setPushtype(1);
            //增加到数据库 等到用户连接上来做消息推动
            result.put("flag",true);
            result.put("msg",message.getMsgcontent());
            mapper.insertSelective(message);
            return result;
        }
        result.put("flag",true);
        result.put("msg",message.getMsgcontent());
        mapper.insertSelective(message);
        return result;
    }
    private static boolean checkKek(JSONObject jsonObject){
        for(String key: keys){
            if(!jsonObject.containsKey(key)){
                return false;
            }
        }
        return true;
    }
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    public static JSONObject getJsonMessage(Message message){
        JSONObject item =new JSONObject();
        item.put("msg",message.getMsgcontent());
        item.put("time",GlobalUserUtil.timeStamp2Date(message.getMsgtimestamp(),null));
        BackMessage backMessage=TransformationUtil.toBean(message.getOthermsg(),BackMessage.class);
        item.put("fromId",backMessage.getFromId());
        item.put("name",backMessage.getName());
        item.put("img",backMessage.getImg());
        item.put("game",backMessage.getGame());
        item.put("type",message.getMsgtype());
        return item;
    }
}
