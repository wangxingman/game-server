package com.game.example.exampleClass;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:25 2019/9/12 0012
 * @explain :
 */
public class ExampleMain {

    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass(1,"2342");
        ExampleClassBase exampleClassBase = new ExampleClassBase(exampleClass,"1231");
        ExampleClass example = (ExampleClass)exampleClassBase.getExample();
        String name = example.getName();
    }
}
