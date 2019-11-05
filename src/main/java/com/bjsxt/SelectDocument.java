package com.bjsxt;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * 查询文档
 * @author lvyelanshan
 * @create 2019-11-05 14:20
 */
public class SelectDocument {
    /*public static void main(String[] args) {
//        SelectDocument.selectDocumentAll();
//        SelectDocument.selectDocumentById();
//        SelectDocument.selectDocumentConditionByGt();
//        SelectDocument.selectDocumentConditionByType();
//        SelectDocument.selectDocumentConditionByIn();
//        SelectDocument.selectDocumentConditionByNotIn();
        SelectDocument.selectDocumentConditionByRegex();
    }
*/
    /**
     * 查询全部文档
     *
     */
    public static void selectDocumentAll(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        /*返回的是一个文档的迭代器,相当于db.devtest.find()*/
        FindIterable<Document> iterable = collection.find();
        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }
    /**
     * 根据id查询文档
     */
    public static void selectDocumentById(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于db.devtest.find({_id:ObjectId("5dc0db06e67c0a3274f1b17e")})
        FindIterable iterable = collection.find(Filters.eq("_id",new ObjectId("5dc0db06e67c0a3274f1b17e")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }
    /**
     * 根据年龄查询文档，条件是年龄大于19岁的
     */
    public static void selectDocumentConditionByGt(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于db.devtest.find({userage:{$gt:19}})
        FindIterable iterable = collection.find(Filters.gt("userage",19));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }

    /**
     * 根据年龄查询文档，条件是年龄的类型是整数类型（number）
     */
    public static void selectDocumentConditionByType(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        //相当于db.devtest.find({userage:{$type:"number"}})
        FindIterable iterable = collection.find(Filters.type("userage","number"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }

    /**
     * 查询用户的名字为zhangsan1,zhangshan2
     */
    public static void selectDocumentConditionByIn(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({username:{$in:["zhangsan1","zhangsan2"]}})
        FindIterable iterable = collection.find(Filters.in("username","zhangsan1","zhangsan2"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }


    /**
     * 查询用户的名字不是zhangsan1,zhangshan2
     */
    public static void selectDocumentConditionByNotIn(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({username:{$nin:["zhangsan1","zhangsan2"]}})
        FindIterable iterable = collection.find(Filters.nin("username","zhangsan1","zhangsan2"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }

    /**
     * 查询用户的名字是z开头2结尾的
     */
    @Test
    public void selectDocumentConditionByRegex(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({username:{$regex:/^z.*2$/}})
        FindIterable iterable = collection.find(Filters.regex("username", Pattern.compile("^z.*2$")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }

    }
    /**
     * 查询用户username是zhangsan1并且年龄为25岁的用户
     */
    @Test
    public void selectDocumentConditionUseAnd(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({$and:[{username:{$eq:"zhangsan1"}},{userage:{$eq:25}}]})
        FindIterable iterable = collection.find(Filters.and(Filters.eq("username","zhangsan1"),Filters.eq("userage",25)));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }
    }


    /**
     * 查询用户要求年龄为25岁的用户，或者名字是“zhangsan2”的
     */
    @Test
    public void selectDocumentConditionUseOr(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({$or:[{username:{$eq:"zhangsan2"}},{userage:{$eq:25}}]})
        FindIterable iterable = collection.find(Filters.or(Filters.eq("username","zhangsan2"),Filters.eq("userage",25)));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }
    }

    /**
     * 查询用户要求年龄为25岁的并且名字为“zhangsan5”，或者名字是“zhangsan2”的
     */
    @Test
    public void selectDocumentConditionAndOr(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({$or:[{$and:[{username:"zhangsan5"},{userage:25}]},{username:"zhangsan2"}]})
        FindIterable iterable = collection.find(Filters.or(Filters.and(Filters.eq("userage",25),Filters.eq("username","zhangsan5")),Filters.eq("username","zhangsan2")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }
    }

    /**
     * 查询文档中username是z开头的，根据username对结果进行降序排序，1升序，-1降序
     */
    @Test
    public void selectDocumentSorting(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //相当于db.devtest.find({username:{$regex:/^z/}}).sort({"username":-1})
        FindIterable iterable = collection.find(Filters.regex("username",Pattern.compile("^z"))).sort(new Document("username",-1));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike"));
        }
    }



}
