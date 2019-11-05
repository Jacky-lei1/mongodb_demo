package com.bjsxt;

import com.mongodb.client.MongoCollection;

/**
 * @author lvyelanshan
 * @create 2019-11-04 17:31
 */
public class MongodbDemo {
    public static void main(String[] args) {
        /*//创建连接对象
        MongoClient client = new MongoClient("192.168.43.15",27017);

        //获取MongoDB的数据库
        MongoDatabase database = client.getDatabase("develop");

        //获取MongoDB中的集合(相当于关系型数据库中的表格)
        MongoCollection collection = database.getCollection("dev");*/

        MongoCollection collection = MongoDBUtil.getCollection("develop", "dev2");

        System.out.println(collection);
        MongoDBAuthPoolUtil.createCollection("develop","devtest");

    }
}
