package com.game.hall.config.name;


import com.game.hall.dto.UserDto;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:42 2019/5/30 0030
 * @explain :
 */
public class AbsServer {

    /**数据结构*/
    /*https://www.cnblogs.com/jingmoxukong/p/4329079.html*/

    /**介绍集合的地址*/
    /*https://www.jianshu.com/p/4345069ada16*/
    /*https://www.jianshu.com/p/63e76826e852*/


    /*果除了在末尾外不能在其他位置插入或者删除元素，那么ArrayList效率更高，
    如果需要经常插入或者删除元素，就选择LinkedList。*/
    /**
     * 每次size增长50%
     * 数组   长度在一开始指定  需要扩展的时候 需要重新定义 比较麻烦
     */
    List<String> list = new ArrayList<>();

    /**
     * 链表   单项链表 双向链表 循环链表
     *      插入数据库快 很简单的添加长度 但是没有标识 查找数据就很慢
     */
    List<String> linkedList = new LinkedList<>();

    /**存储是hashMap 无排序 唯一*/
    Set<String> set = new HashSet<>();

    /**
     * 排序的set
     */
    static Set<String> linkedHash = new LinkedHashSet<>();

    /**
     * 有序的set
     */
    Set<User> treeSet = new TreeSet<>();

    /**如果更新图时不需要保持图中元素的顺序，就使用HashMap，
     * 如果需要保持图中元素的插入顺序或者访问顺序，就使用LinkedHashMap，
     * 如果需要使图按照键值排序，就使用TreeMap。*/


    /**数组加链表 ->红黑树 当链表的数量大于8的时候 就转成红黑树*/
    Map<String, String> map = new HashMap<>();

    /**排序的map 元素*/
    Map<String,String> linkedMap = new LinkedHashMap();

    /**排序map  键*/
    Map<String,String> treeMap = new TreeMap<>();

    /**并发*/
     static  Map<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();

    public static Map<Integer,String> getConcurrentHashMap() {
           return concurrentHashMap;
     }

    static  Map<Integer, UserDto> concurrentHashMapUserDto = new ConcurrentHashMap<>();

    public static Map<Integer,UserDto> getConcurrentHashMapUserDto() {
        return concurrentHashMapUserDto;
    }

    /**它支持在两端插入和删除元素 先进先出
     *
     * 【好像可以将 接收到协议 慢慢的处理 使用queue】
     * */
    Queue<String> queue = new LinkedList<>();

}
