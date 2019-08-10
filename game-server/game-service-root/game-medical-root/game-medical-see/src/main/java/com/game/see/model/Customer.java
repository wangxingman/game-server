package com.game.see.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:38 2019/8/2 0002
 * @explain : 配置和es数据库映射
 *            数据库 表 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elasticsearch",type = "elasticsearch")
public class Customer implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
