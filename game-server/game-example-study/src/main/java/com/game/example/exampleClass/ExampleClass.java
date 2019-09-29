package com.game.example.exampleClass;


import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:23 2019/9/12 0012
 * @explain :
 */
@Data
public class ExampleClass  implements Example{

    private Integer id;

    private String name;

    public ExampleClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
