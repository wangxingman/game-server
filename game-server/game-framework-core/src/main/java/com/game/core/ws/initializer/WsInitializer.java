package com.game.core.ws.initializer;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 12:20 2019/5/17 0017
 * @explain :
 */
public interface WsInitializer {

    /**
     * init
     *
     * @throws Exception Throwable
     */
    default void init() throws Exception {
    }

    /**
     * destroy
     *
     * @throws Exception Throwable
     */
    default void destroy() throws Exception {
    }

    /**
     * order
     *
     * @return int
     */
    default int order() {
        return 0;
    }
}
