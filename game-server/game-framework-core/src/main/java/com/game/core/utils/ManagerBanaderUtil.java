package com.game.core.utils;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 3:05 2019/5/16 0016
 * @explain : 配置启动时候的图案配置
 */
public class ManagerBanaderUtil implements Banner {
    public static final String VERSION = "5.0.2.RELEASE";

    private static final String BANNER =
                     "                         _oo0oo_\n"+
                            "                  o8888888o\n"+
                            "                  88\" . \"88\n"+
                            "                  (| -_- |)\n"+
                            "                  0\\ =  /0\n"+
                            "                ___/`---'\\___\n"+
                            "              .' \\\\|     |-- '.\n"+
                            "             / \\\\|||  :  |||-- \\\n"+
                            "            / _||||| -:- |||||- \\\n"+
                            "           |   | \\\\\\  -  --/ |   |\n"+
                            "           | \\_|  ''\\---/''  |_/ |\n"+
                            "           \\  .-\\__  '-'  ___/-. /\n"+
                            "         ___'. .'  /--.--\\  `. .'___\n"+
                            "      .\"\" '<  `.___\\_<|>_/___.' >' \"\".\n"+
                            "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n"+
                            "     \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n"+
                            " =====`-.____`.___ \\_____/___.-`___.-'=====\n"+
                            "                   `=---='                   \n " +
                             " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n"+
                                                                            "\n"+
                             "             佛祖保佑    永无BUG\n"+
                              " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";


    private static final String SERVER_INFO = "       HTTP port:%s  DTX port:%s";

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        String serverPortProperty = environment.getProperty("server.port");
        int httpPort = 8080;
        if (serverPortProperty != null) {
            httpPort = Integer.parseInt(serverPortProperty);
        }
        String string = String.format(SERVER_INFO,VERSION, httpPort);
        printStream.println();
        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, BANNER));
        printStream.println();
        printStream.println(AnsiOutput.toString(AnsiColor.GREEN,string));
        printStream.println();
        printStream.println(AnsiOutput.toString("代码什么的是不可能的！"));
    }
}
