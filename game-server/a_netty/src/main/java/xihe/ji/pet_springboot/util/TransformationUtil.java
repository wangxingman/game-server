package xihe.ji.pet_springboot.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author : jch
 * @version V1.0
 * @Project: pet_springboot
 * @Package xihe.ji.pet_springboot.utils
 * @Description: 类型转换
 * @date Date : 2018年09月12日 16:48
 */
public class TransformationUtil {
    public static <T>T toBean(Object ss,Class<T> clazz){
        try {
            return (T)JSONObject.toBean(JSONObject.fromObject(ss),clazz);
        }catch (JSONException e){
            return (T)JSONObject.toBean(JSONArray.fromObject(ss).getJSONObject(0),clazz);
        }

    }
    public static String toString(Object ss){
        return JSONObject.fromObject(ss).toString();
    }
}
