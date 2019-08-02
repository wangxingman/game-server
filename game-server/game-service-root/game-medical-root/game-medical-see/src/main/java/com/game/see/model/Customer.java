package com.game.see.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:38 2019/8/2 0002
 * @explain : 配置和es数据库映射
 */
@Data
@Document(indexName = " " ,type = " ",shards = 2, replicas = 1, refreshInterval = "-1")
public class Customer implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;
}
