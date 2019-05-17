package com.game.hall.netty.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.common.dto.DataPacket;
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
            data = JSON.parseObject(requestData);
        }
        /*&& request.getAgreementNo() != Command.DELAY_CHECK_REQ*/
        if (webSocket != null && request.getAgreementNo() != 10000 ) {
            log.info("Recive msg:cmd="+request.getAgreementNo() +";userId="+webSocket.getUserId()+";data="+ (data != null ?data.toJSONString() :"NULL"));
        }
        this.handle(webSocket, data);
    }
}
