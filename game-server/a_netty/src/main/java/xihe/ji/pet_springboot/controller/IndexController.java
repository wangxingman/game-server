package xihe.ji.pet_springboot.controller;

import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xihe.ji.pet_springboot.dao.MessageMapper;
import xihe.ji.pet_springboot.pojo.BackMessage;
import xihe.ji.pet_springboot.pojo.Message;
import xihe.ji.pet_springboot.pojo.userInfo;
import xihe.ji.pet_springboot.util.*;

import java.util.*;

@RestController
public class IndexController {


    @Autowired
    private MessageMapper messageMapper;

    @GetMapping("/getOnlineNum")
    public Map getOnlineNum(){
        Map<String,Object> map=new HashMap<>();
        map.put("size",GlobalUserUtil.USERMAP.size());
        map.put("status",200);
        return map;
    }
    @RequestMapping("/sendMessage")
    public Map sendMessage(String msg){
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.putAll(GlobalUserUtil.sendMsg(msg,messageMapper, null));
        return map;
    }
    @RequestMapping("/v2/sendMessage")
    public Map sendMessage2(String msg,String fromId,String toId,String game,String type){
        Map<String,Object> msgs=new HashMap<>();
        msgs.put("msg",msg);
        msgs.put("fromId",fromId);
        msgs.put("toId",toId);
        msgs.put("game",game);
        msgs.put("type",type);
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.putAll(GlobalUserUtil.sendMsg(JSONObject.fromObject(msgs).toString(),messageMapper, null));
        return map;
    }
    @RequestMapping("/sendAdmin")
    public Map sendAdmin(String msg){
        Map<String,Object> map=new HashMap<>();
        GlobalUserUtil.sendAdminMsg(msg);
        map.put("status",200);
        map.put("msg","已推送消息");
        return map;
    }


    @GetMapping("/getUserMessage")
    public Map getUserMessage(String uuid){
        Map<String,Object> map=new HashMap<>();
        List<Message> messages=messageMapper.selectViewMessage(uuid);
        List<Message> Tomessages=messageMapper.selectViewMessageTo(uuid);
        for(Message message:Tomessages){
            boolean flag=true;
            for(Message toMessage:messages){
                if(message.getToid().equals(toMessage.getFromid())){
                    flag=false;
                    Long time1= Long.valueOf(message.getMsgtimestamp());
                    Long time2= Long.valueOf(toMessage.getMsgtimestamp());
                    if(time1>=time2){
                        BackMessage backMessage=TransformationUtil.toBean(message.getOthermsg(),BackMessage.class);
                        String from=backMessage.getFromId();
                        backMessage.setFromId(backMessage.getToId());
                            backMessage.setToId(from);
                        String url= MessageUtil.Server.codeOf(backMessage.getGame());
                        userInfo toUser = TransformationUtil.toBean(UrlConnect.get(url+"my/userInfoId?id="+backMessage.getFromId()),userInfo.class);
                        backMessage.setImg(toUser.getImg());
                        backMessage.setName(toUser.getName());
                        toMessage.setOthermsg(TransformationUtil.toString(backMessage));
                        toMessage.setMsgcontent(message.getMsgcontent());
                        toMessage.setMsgtimestamp(time1.toString());
                        toMessage.setFromid(message.getToid());
                        toMessage.setToid(message.getFromid());
                    }
                }
            }
            if(flag){
                Message tomessage=new Message();
                BackMessage backMessage=TransformationUtil.toBean(message.getOthermsg(),BackMessage.class);
                String from=backMessage.getFromId();
                backMessage.setFromId(backMessage.getToId());
                backMessage.setToId(from);
                String url= MessageUtil.Server.codeOf(backMessage.getGame());
                userInfo toUser = TransformationUtil.toBean(UrlConnect.get(url+"my/userInfoId?id="+backMessage.getFromId()),userInfo.class);
                backMessage.setImg(toUser.getImg());
                backMessage.setName(toUser.getName());
                tomessage.setOthermsg(TransformationUtil.toString(backMessage));
                tomessage.setMsgcontent(message.getMsgcontent());
                tomessage.setFromid(message.getToid());
                tomessage.setToid(message.getFromid());
                tomessage.setId(message.getId());
                tomessage.setPushtype(message.getPushtype());
                tomessage.setMsgtype(message.getMsgtype());
                tomessage.setMsgtimestamp(message.getMsgtimestamp());
                messages.add(tomessage);
            }
        }
//        Set<Message> set = new TreeSet<>(new Message());
//        for(Message message:messages){
//            set.add(message);
//        }
        Collections.sort(messages,new Message());
        JSONArray jsonArray=new JSONArray();
        for(Message message:messages){
            jsonArray.add(GlobalUserUtil.getJsonMessage(message));
        }
        map.put("status",200);
        map.put("messages",jsonArray);
        return map;
    }

    @GetMapping("/getUserHistory")
    public Map getHistory(String uuid, Integer friendId,
                          @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize,
                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam String game){
        Map<String,Object> map=new HashMap<>();
        String url= MessageUtil.Server.codeOf(game);
        userInfo toUser = TransformationUtil.toBean(UrlConnect.get(url+"my/userInfoId?id="+friendId),userInfo.class);
        if(toUser==null){
            return null;
        }
        if(toUser.getToken()==null){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Message> messages=messageMapper.selectHistoryMessage(uuid,toUser.getToken());
        JSONArray jsonArray=new JSONArray();
        for(Message message:messages){
            jsonArray.add(GlobalUserUtil.getJsonMessage(message));
        }
        map.put("status",200);
        map.put("messages",jsonArray);
        return map;
    }
}
