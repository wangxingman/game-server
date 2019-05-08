package xihe.ji.pet_springboot.util;

public class MessageUtil {
    public enum Server{
        /**
         * 服务器地址
         */
        SERVER1("bendi","http://192.168.1.20:8888/"),
        SERVER2("mcwww","https://www.youni123.com/zpxg/");
        private String  value;
        private  String code;
        Server(String code,String value){
            this.code=code;
            this.value=value;
        }
        public static String codeOf(String code){
            for (Server obj:values()){
                if (obj.getCode().equals(code)){
                    return obj.getValue();
                }
            }
            return "http://127.0.0.1:8081/";
        }
        public String getValue() {
            return value;
        }
        public String getCode() {
            return code;
        }
    }
}
