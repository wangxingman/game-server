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
            "          _______   __      _     _____  _   _          \n" +
                    "         |_   _\\ \\ / /     | |   /  __ \\| \\ | |     \n" +
                    "           | |  \\ V /______| |   | /  \\/|  \\| |      \n" +
                    "           | |  /   \\______| |   | |    | . ` |        \n" +
                    "           | | / /^\\ \\     | |___| \\__/\\| |\\  |    \n" +
                    "           \\_/ \\/   \\/     \\_____/\\____/\\_| \\_/  \n";

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
