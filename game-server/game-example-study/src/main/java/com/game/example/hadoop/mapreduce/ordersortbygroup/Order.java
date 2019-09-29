package com.game.example.hadoop.mapreduce.ordersortbygroup;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:01 2019/9/16 0016
 * @explain :
 */
public class Order  implements WritableComparable<Order> {

    private int id;
    private int price;

    @Override
    public int compareTo(Order o) {
        int result;
        //先按订单升序 再按价格降序
        if(id<o.id){
            result = -1;
        }else if(id>o.id){
            result = 1;
        }else {
            result = price>o.price?-1:1;
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeInt(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        price = in.readInt();
    }

    public  Order(){
    }

    public  Order(int id,int price){
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "\t" + price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
