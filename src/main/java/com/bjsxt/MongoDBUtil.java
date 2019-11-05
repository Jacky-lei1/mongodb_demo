package com.bjsxt;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author lvyelanshan
 * @create 2019-11-04 17:37
 */
public class MongoDBUtil {
    private static MongoClient client =null;
    //类加载时，如果连接对象为空则直接创建MongoDB的连接对象
    static {
        if(client == null){
            //创建连接对象
            client = new MongoClient("192.168.43.15",27017);
        }
    }

    //获取MongoDB数据库对象
    public static MongoDatabase getDatabase(String dbName){
        return client.getDatabase(dbName);
    }

    //获取MongoDB中的集合
    public static MongoCollection getCollection(String dbName,String coll){
        //获取集合前需要先获取数据库
        MongoDatabase database = getDatabase(dbName);
        //通过MOngoDB的数据库对象获取到集合对象
        return database.getCollection(coll);
    }

}
