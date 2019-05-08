package xihe.ji.pet_springboot.pojo;

import java.util.Comparator;

public class Message implements Comparator<Message> {
    private Integer id;

    private String fromid;

    private String toid;

    private String msgtimestamp;

    private String msgcontent;

    private String othermsg;

    private String msgtype;

    private Integer pushtype;

    public Message(Integer id, String fromid, String toid, String msgtimestamp, String msgcontent, String othermsg, String msgtype, Integer pushtype) {
        this.id = id;
        this.fromid = fromid;
        this.toid = toid;
        this.msgtimestamp = msgtimestamp;
        this.msgcontent = msgcontent;
        this.othermsg = othermsg;
        this.msgtype = msgtype;
        this.pushtype = pushtype;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid == null ? null : fromid.trim();
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid == null ? null : toid.trim();
    }

    public String getMsgtimestamp() {
        return msgtimestamp;
    }

    public void setMsgtimestamp(String msgtimestamp) {
        this.msgtimestamp = msgtimestamp == null ? null : msgtimestamp.trim();
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public String getOthermsg() {
        return othermsg;
    }

    public void setOthermsg(String othermsg) {
        this.othermsg = othermsg == null ? null : othermsg.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public Integer getPushtype() {
        return pushtype;
    }

    public void setPushtype(Integer pushtype) {
        this.pushtype = pushtype;
    }


    @Override
    public int compare(Message o1, Message o2) {
        long time1= Long.parseLong(o1.getMsgtimestamp());
        long time2= Long.parseLong(o2.getMsgtimestamp());
        if(time1>time2){
            return -1;
        }else {
            return 1;
        }
    }
}