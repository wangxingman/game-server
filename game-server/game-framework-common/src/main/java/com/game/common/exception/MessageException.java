package com.game.common.exception;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:40 2019/5/20 0020
 * @explain :
 */
public class MessageException extends RuntimeException {
    private static final long serialVersionUID = 4944691869943576099L;
    private int code = -1;

    public MessageException(String message) {
        super(message);
    }

    public MessageException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

