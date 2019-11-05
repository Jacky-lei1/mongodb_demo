package com.bjsxt;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**支持用户认证的池连
 * @author lvyelanshan
 * @create 2019-11-04 19:14
 */
public class MongoDBAuthPoolUtil {
    private static MongoClient client =null;
    static {
        if(client == null){
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(10);//每个地址的最大连接数
            builder.connectTimeout(5000);//连接超时时间
            builder.socketTimeout(5000);//设置读写操作超时时间的

            //创建一个封装用户认证信息(参数表示该用户在哪个库中进行认证)
            MongoCredential credential = MongoCredential.createCredential("itsxt","develop","itsxtpwd".toCharArray());

            ServerAddress address = new ServerAddress("192.168.43.15",27017);
            //创建连接对象
            client = new MongoClient(address,credential,builder.build());
        }
    }

    //获取MongoDB数据库对象
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

    //创建集合
    public static void createCollection(String dbName,String collName){
        MongoDatabase database = getDatabase(dbName);
        database.createCollection(collName);
    }

    //删除集合
    public static void dropCollection(MongoCollection coll){
        coll.drop();
    }
}
