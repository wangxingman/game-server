package com.game.sg.common.netty.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.sg.common.Command;
import com.game.common.dto.DataPacket;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:52 2019/5/5 0005
 */
@Slf4j
public abstract class Handler {

    public abstract void handle(WebSocket webSocket, JSONObject data);

    public void handle(WebSocket webSocket, DataPacket request) {
        String requestData = request.getData();
        JSONObject data = null;
        if (requestData != null && !"".equals(requestData)) {
            //调试阶段,加密动作先去掉
//            byte[] bArray = StringUtils.hex2Binary(requestData);
//            String text = AES256.decode(bArray, StringUtils.getUTFBytes(Wsconfig.getWsAES256Key()));
//            text = UrlUtils.decode(text);
//            data = JSON.parseObject(text);
            data = JSON.parseObject(requestData);
        }
        if (webSocket != null && request.getCmd() != 10000 && request.getCmd() != Command.DELAY_CHECK_REQ) {
            log.info("Recive msg:cmd="+request.getCmd() +";userId="+webSocket.getUserId()+";data="+ (data != null ?data.toJSONString() :"NULL"));
        }

        this.handle(webSocket, data);
    }
}
