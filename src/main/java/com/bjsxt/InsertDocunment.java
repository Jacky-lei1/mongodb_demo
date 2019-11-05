package com.bjsxt;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**添加文档
 * @author lvyelanshan
 * @create 2019-11-05 9:43
 */
public class InsertDocunment {
    public static void main(String[] args) {
//        InsertDocunment.insertSingleDocument();
        InsertDocunment.insertManyDocument();
    }
    /**
     * 添加单个文档
     */
    public static void insertSingleDocument(){
        //获取集合连接对象
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //{}---->Doucument对象,org.bson包下的Document对象
        //append(String key,Object value)--->{key:value}
        //创建一个文档对象
        Document document = new Document();
        document.append("usename","lisi").append("userage",26).append("userdesc","Very Good").append("userlike", Arrays.asList(new String[]{"Music","Sport"}));
        //表示向当前集合中插入一个文档
        collection.insertOne(document);
    }
    /**
     *文档的批量添加
     */
    public static void insertManyDocument(){
        //同样先获取到集合对象
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        List<Document> list = new ArrayList<Document>();
        for (int i = 0; i<=5; i++){
            Document document = new Document();
            document.append("username","zhangsan"+i);
            document.append("userage",20+i);
            document.append("userdesc","Come baby!");
            document.append("userLike",Arrays.asList(new String[]{"guitar","Singing","coding"}));
            list.add(document);
        }
        collection.insertMany(list);
    }
}
