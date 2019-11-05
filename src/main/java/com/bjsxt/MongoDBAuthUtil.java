package com.bjsxt;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * 创建MongoDB拦截-使用用户认证
 * @author lvyelanshan
 * @create 2019-11-04 18:35
 */
public class MongoDBAuthUtil {
    private static MongoClient client =null;
    static {
        if (client == null){
            //创建一个封装用户认证信息
            MongoCredential credential = MongoCredential.createCredential("itsxt","develop","itsxtpwd".toCharArray());
            //封装Mongodb的地址与端口
            ServerAddress address = new ServerAddress("192.168.43.15",27017);
            client = new MongoClient(address, Arrays.asList(credential));
        }
    }
    //获取MongoDB数据库
    public static MongoDatabase getDatabase(String dbName){
        return client.getDatabase(dbName);
    }

    //获取MongoDB中的集合
    public static MongoCollection getCollection(String dbName, String coll){
        //获取集合前需要先获取数据库
        MongoDatabase database = getDatabase(dbName);
        //通过MOngoDB的数据库对象获取到集合对象
        return database.getCollection(coll);
    }
}
