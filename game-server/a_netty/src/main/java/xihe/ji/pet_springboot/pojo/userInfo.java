package xihe.ji.pet_springboot.pojo;

/**
 * @author : jch
 * @version V1.0
 * @Project: jch_netty
 * @Package xihe.ji.pet_springboot.pojo
 * @Description: 用户信息
 * @date Date : 2018年09月19日 15:35
 */
public class userInfo {
    private String name;
    private String img;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
