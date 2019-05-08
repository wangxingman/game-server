package xihe.ji.pet_springboot.pojo;

/**
 * @author : jch
 * @version V1.0
 * @Project: jch_netty
 * @Package xihe.ji.pet_springboot.pojo
 * @Description: 反馈消息 img以及 name
 * @date Date : 2018年09月19日 18:04
 */

public class BackMessage {
    private String fromId;
    private String toId;
    private String name;
    private String img;
    private String game;

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
