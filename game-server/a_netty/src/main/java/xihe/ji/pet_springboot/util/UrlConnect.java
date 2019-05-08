package xihe.ji.pet_springboot.util;

/**
 * @author : jch
 * @version V1.0
 * @Project: HappyFarm
 * @Package xihe.game.utils
 * @Description: 发送请求
 * @date Date : 2018年08月09日 17:16
 */


import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UrlConnect {
    public static JSONObject get(String path) {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = getResult(conn);
        } catch (Exception e) {
        }
        return jsonObject;
    }

    public static JSONObject post(String path,String params) throws Exception {
        return getContent(path,params);
    }

    public static JSONObject getContent(String  path,String params) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        byte[] data = params.getBytes();
        conn.setRequestProperty("Content-Length", String
                .valueOf(data.length));
        conn.setRequestProperty("Content-Type", "application/json");
        pushData(conn,data);
        JSONObject jsonObject = getResult(conn);
        return jsonObject;
    }

    private static void pushData(HttpURLConnection conn,byte[] obj) throws IOException {
        OutputStream out = conn.getOutputStream();
        out.write(obj);
        out.flush();
        out.close();
        conn.connect();
    }
    private static JSONObject getResult(HttpURLConnection conn) throws Exception {
        JSONObject object=null;
        String res;
        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);
            res = new String(data1,"utf-8");
            object=JSONObject.fromObject(res);
            return  object;
        }
        else {
            throw new Exception();
        }
    }
}
