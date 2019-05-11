package com.game.core.url;

/**
 * @Auther : wx
 * @Desc :   UrlMatcher url匹配接口
 * @Date :  下午 8:41 2019/5/9 0009
 * @explain :
 */
public interface UrlMatcher {
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversalMatchPattern();
    boolean requiresLowerCaseUrl();
}