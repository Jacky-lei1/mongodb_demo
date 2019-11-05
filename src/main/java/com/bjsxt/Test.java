package com.bjsxt;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.Document;

/**
 * @author lvyelanshan
 * @create 2019-11-05 15:09
 */
public class Test {

    public static void main(String[] args) {
//        UpdateDocument.updateSingleDocumentSingKey();
//        UpdateDocument.updateSingleDocumentManykey();
//        UpdateDocument.updateManyDocumentSingleKey();
//        UpdateDocument.updateManyDocumentManyKey();
        UpdateDocument.updateDocumentArray();
    }
    /**
     * 更新单个文档的单个键对应的值
     */
    public static  void updateSingleDocumentSingKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //更新文档,Mongodb中把所有的操作符都定义到了Filters类中的方法中
        //Filters封装了条件的工具类{$set:{userage:28}}，这里的document对象相当于“{}”
        //相当于db.devtest.update({usename:"lisi"},{$set:{userage:28}})
        collection.updateOne(Filters.eq("usename","lisi"),new Document("$set",new Document("userage",28)));
    }

    /**
     * 更新单个文档的多个键中的值
     */
    public static void updateSingleDocumentManykey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于：db.devtest.update({usename:"lisi"},{$set:{userage:20,userdesc:"nihao"}})
        collection.updateOne(Filters.eq("usename","lisi"),new Document("$set",new Document("userage",18).append("userdesc","henhao")));
    }

    /**
     * 更新多个文档的单个键中的值
     */
    public static void updateManyDocumentSingleKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于db.devtest.updateMany({username:{$ne:null}},{$set:{userdesc:"hello!"}})
        collection.updateMany(Filters.ne("username",null),new Document("$set",new Document("userdesc","hello!")));

    }

    /**
     * 更新多个文档的多个键
     */
    public static void updateManyDocumentManyKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于：db.devtest.updateMany({username:{$ne:null}},{$set:{userage:25,userdesc:"come here!"}})
        collection.updateMany(Filters.ne("username",null),new Document("$set",new Document("userage",20).append("userdesc","goodbye!")));
    }

    /**
     * 更新单个文档中的数组(向数组中添加一个元素)
     */
    public static void updateDocumentArray(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于：db.devtest.update({usename:{$eq:"lisi"}},{$push:{userlike:"guitar"}})
        collection.updateOne(Filters.eq("usename","lisi"),new Document("$push",new Document("userlike","Art")));
    }

    /**
     * 更新单个文档中的数组(向数组中添加多个元素)
     */
    public static void updateDocumentManyArray(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        collection.updateOne(Filters.eq("username","zhangsan4"),new Document("$addToSet",new Document("$each", new Document("drawing","cooking"))));

    }

}
