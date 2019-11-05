package com.bjsxt;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * $project 投影操作
 * @author lvyelanshan
 * @create 2019-11-05 20:39
 */
public class ProjectOper {

    /**
     * 需求：查询 dev 集合，将数组中的内容拆分显示，并只显示 title 键与 tags 键的值。
     * Mongo Shell：
     * db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,tags:"$tags",title:"$title"}}])
     */
    @Test
    public void selectDocumentProject(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","dev");
        Document unwind = new Document();
        unwind.put("$unwind","$tags");

        Document id = new Document();
        id.put("_id",0);
        id.put("tags","$tags");
        id.put("title","$title");

        Document project = new Document();
        project.put("$project",id);

        List<Document> list = new ArrayList<Document>();
        list.add(unwind);
        list.add(project);

        AggregateIterable aggregate = collection.aggregate(list);
        MongoCursor<Document> cursor = aggregate.iterator();
        while (cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.get("tags")+"\t"+doc.get("title"));
        }

    }

    /**
     * 需求：查询 dev 集合，将数组中的内容拆分显示。将 title 字段和 tags 字段的值拼接为
     * 一个完整字符串并在 Title_Tags 字段中显示。
     * Mongo Shell：db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,Title_Tags:{$concat:["$title","-","$tags"]}}}])
     */
    @Test
    public void selectDocumentProjectConcat(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document unwind = new Document();
        unwind.put("$unwind","$tags");

        Document concat = new Document();
        concat.put("$concat", Arrays.asList(new String[]{"$title","-","$tags"}));

        Document id = new Document();
        id.put("_id",0);
        id.put("Title_Tags",concat);

        Document project = new Document();
        project.put("$project",id);

        List<Document> list = new ArrayList<Document>();
        list.add(unwind);
        list.add(project);

        AggregateIterable aggregate = collection.aggregate(list);
        MongoCursor cursor = aggregate.iterator();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }

    }

}
