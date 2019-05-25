package com.game.gateway.netty;

import com.game.gateway.netty.initializer.WebSocketServerChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.Data;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:45 2019/5/21 0021
 * @explain :
 */
@Data
public abstract class AbstractNetService {

     boolean ssl;

     WebSocketServerChannelInitializer webSocketChannelInitialer;

     static SslContext sslContext;

    /**
     * @Author: wx
     * @Date  : 上午 11:49 2019/5/21 0021 
     * @params: 
     * @Desc  :
     */
    public void initNetService() {
        
    }
    
    /**
     * @Author: wx
     * @Date  : 上午 11:48 2019/5/21 0021 
     * @params: 
     * @Desc  :  初始化管道 ssl验证
     */
    public void initChannlInitializer() {
        ssl = Boolean.valueOf("");
        if(ssl) {
            SelfSignedCertificate ssc = null;
            try {
                //ssl配置
                ssc = new SelfSignedCertificate();
                sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
                //初始化
                webSocketChannelInitialer = new WebSocketServerChannelInitializer();
            } catch (CertificateException | SSLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author: wx
     * @Date  : 上午 11:48 2019/5/21 0021 
     * @params: 
     * @Desc  :
     */
    public void startup() {}


}
