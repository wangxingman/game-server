package com.game.example.exampleClass;

import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:24 2019/9/12 0012
 * @explain :
 */
@Data
public class ExampleClassBase {
    
    private Example example;
    private String niHao;

    public ExampleClassBase(Example example, String niHao) {
        this.example = example;
        this.niHao = niHao;
    }
}
