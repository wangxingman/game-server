package com.game.core.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wx
 * @Desc : jwt的token的工具类
 * @Date : 上午 11:15 2019/5/9 0009
 */
public class JwtToken {

    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();

    /**
     * @Author: wx
     * @Desc  :  生成token
     * @Date  : 上午 11:17 2019/5/9 0009 
     * @params: 
     */
    public static String creatToken(Map<String,Object> payloadMap){
        //先建立一个头部Header,调用加密算法
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(String.valueOf(new JSONObject(payloadMap)));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = null;
        try {
            jwsSigner = new MACSigner(secret);
            jwsObject.sign(jwsSigner);              //签名
        } catch (JOSEException e) {
                e.printStackTrace();
        }
        return jwsObject.serialize();
    }

    /**
     * @Author: wx
     * @Desc  :验证token
     * @Date  : 下午 6:41 2019/5/9 0009 
     * @params: 
     */
    public static Map<String,Object> valid(String token) throws ParseException, JOSEException {
        JWSObject jwsObject =  JWSObject.parse(token);                        //解析token
        Payload payload = jwsObject.getPayload();                             //获取到载荷
        JWSVerifier jwsVerifier = new MACVerifier(secret);                     //建立一个解锁钥匙
        Map<String, Object> resultMap = new HashMap<>();

        if(jwsObject.verify(jwsVerifier)) {                                     //判断token
            resultMap.put("Result", 0);
            net.minidev.json.JSONObject jsonObject = payload.toJSONObject();    //载荷的数据解析成json对象。
            resultMap.put("data", jsonObject);

            if(jsonObject.containsKey("exp")) {                                  //判断token是否过期
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();

                if (nowTime > expTime) {                                         //判断是否过期
                    resultMap.clear();                                           //已经过期
                    resultMap.put("Result", 2);
                }
            }  else {
                resultMap.put("Result", 1);
            }
            return resultMap;
        }
        return null;
    }

    /**
     * @Author: wx
     * @Desc  : 生成token
     * @Date  : 下午 7:01 2019/5/9 0009
     * @params:
     */
    private static String createToken()  {
        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", 12);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+100000000000000l);
        String s = null;
        try {
            s = JwtToken.creatToken(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @Author: wx
     * @Desc  : 解析token
     * @Date  : 下午 7:06 2019/5/9 0009
     * @params:
     */
    public static void ValidToken(String token) {
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = JwtToken.valid(token);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    System.out.println("token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    System.out.println("uid是" + jsonObject.get("uid"));
                    System.out.println("sta是"+jsonObject.get("sta"));
                    System.out.println("exp是"+jsonObject.get("exp"));
                } else if (i == 2) {
                    System.out.println("token已经过期");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String token = JwtToken.createToken();
        JwtToken.ValidToken(token);
    }
}
