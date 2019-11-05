package com.bjsxt;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-11-05 19:42
 */
public class AggergateOper {
    /**
     * 查询集合当中文档的数量
     * 相当于db.dev.aggregate([{$group:{_id:null,count:{$sum:1}}}])
     * 每一个大括号代表一个文档对象，括号中每一个“：”表示一个键值对
     */
    @Test
    public void selectDocumentAggregateCount(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document sum = new Document();
        sum.put("$sum",1);
        Document count = new Document();
        count.put("_id",null);
        count.put("count",sum);
        Document group = new Document();
        group.put("$group",count);

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        //与find不同返回的是一个AggregateIterable,参数要求是一个list集合
        AggregateIterable iterable = collection.aggregate(list);

        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("_id")+"\t"+docu.get("count"));
        }
    }

    /**
     * 需求：查询集合中所有size键中的值的总和
     * 相当于：Mongo Shell：db.dev.aggregate([{$group:{_id:null,totalSize:{$sum:"$size"}}}])
     */
    @Test
    public void selectDocumentAggregateSum(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document sum = new Document();
        sum.put("$sum","$size");

        Document totalSize = new Document();
        totalSize.put("_id",null);
        totalSize.put("totalSize",sum);

        Document group = new Document();
        group.put("$group",totalSize);

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("_id")+"\t"+docu.get("totalSize"));
        }

    }

    /**
     * 需求：对 title 进行分组，计算每组中的 size 的总和
     * 相当于：Mongo Shell：db.dev.aggregate([{$group:{_id:"$title",totalSize:{$sum:"$size"}}}])
     */
    @Test
    public void selectDocumentAggregateGroupBySum(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document sum = new Document();
        sum.put("$sum","$size");

        Document totalSize = new Document();
        totalSize.put("_id","$title");
        totalSize.put("totalSize",sum);

        Document group = new Document();
        group.put("$group",totalSize);

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("_id")+"\t"+docu.get("totalSize"));
        }

    }

    /**
     * 需求：查询 dev 集合有多少文档的 size 大于 200。
     * Mongo Shell： db.dev.aggregate([{$match:{size:{$gt:200}}},{$group:{_id:null,totalSize:{$sum:1}}}])
     */
    @Test
    public void selectDocumentAggregateGroupBySumWhere(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","dev");
        Document gt = new Document();
        gt.put("$gt",200);

        Document size = new Document();
        size.put("size",gt);

        Document match = new Document();
        match.put("$match",size);

        Document sum = new Document();
        sum.put("$sum",1);

        Document id = new Document();
        id.put("_id",null);
        id.put("totalSize",sum);

        Document group = new Document();
        group.put("$group",id);

        List<Document> list = new ArrayList<Document>();
        list.add(match);
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("_id")+"\t"+docu.get("totalSize"));
        }

    }









}
