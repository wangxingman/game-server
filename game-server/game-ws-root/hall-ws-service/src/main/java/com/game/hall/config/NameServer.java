package com.game.hall.config;

import java.util.*;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:37 2019/5/29 0029
 * @explain : 快速获取所有的用户
 */
public class NameServer {

    /**介绍集合的地址*/
    /*https://www.jianshu.com/p/4345069ada16*/
    /*https://www.jianshu.com/p/63e76826e852*/
    
    Set<String> set = new HashSet<>();

    /** 每次size增长50%*/
    List<String> list = new ArrayList<>();

    
    Map<String,String> map = new HashMap<>();

    Queue queue = new Queue() {
        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean offer(Object o) {
            return false;
        }

        @Override
        public Object remove() {
            return null;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public Object element() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    

    public static boolean getUserName() {

        return true;
    }

    
    
}
