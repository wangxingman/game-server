package com.game.example.hadoop.mapreduce.ordersortbygroup;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:06 2019/9/16 0016
 * @explain :
 */
public class OrderSortGrouping  extends WritableComparator {

    protected OrderSortGrouping(){
        super(Order.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable  b) {
        Order aBean = (Order) a;
        Order bBean = (Order) b;
        int result;
        if(aBean.getId()>bBean.getId()){
            result = 1;
        }else if(aBean.getId()<bBean.getId()){
            result = -1;
        }else {
            result = 0;
        }
        return result;
    }
}
