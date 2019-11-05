package com.bjsxt;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**日期操作
 * @author lvyelanshan
 * @create 2019-11-05 18:51
 */
public class DateOperation {
    public static void main(String[] args) {

    }

    /**
     * 插入系统当前日期
     */
    @Test
    public void insertDocumentSystemDate(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        //可以使用.append也可以使用put方法
        Document docu = new Document();
        docu.put("username","wangwu");
        docu.put("userage",22);
        docu.put("userdesc","Very Good");
        docu.put("userLike", Arrays.asList(new String[]{"Music","Art"}));
        //java中的java.util.Date是带时区的，但是MongoDB中的时间是格林尼治时间，会慢八个小时
        docu.put("userbirth",new Date());
        //插入一条文档
        collection.insertOne(docu);
    }
    /**
     * 插入指定日期
     */
    @Test
    public void insertDocumentCustomDate(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        Date date = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss","2019-11-05 19:20:30");
        //可以使用.append也可以使用put方法
        Document docu = new Document();
        docu.put("username","zhaoliu");
        docu.put("userage",24);
        docu.put("userdesc","Very nice");
        docu.put("userLike", Arrays.asList(new String[]{"Music","Art"}));
        //插入指定日期
        docu.put("userbirth",date);
        //插入一条文档
        collection.insertOne(docu);
    }
    /**
     * 插入日期：查询生日为2019-11-05 19:20:30的用户信息
     */
    @Test
    public void selectDocumentDateUserEq(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop","devtest");
        Date date = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss","2019-11-05 19:20:30");
        /*返回的是一个文档的迭代器,相当于db.devtest.find()*/
        FindIterable<Document> iterable = collection.find(Filters.eq("userbirth",date));
        /*通过这个迭代器可以拿到这个游标*/
        MongoCursor<Document> cursor = iterable.iterator();
        /*判断当前游标（指针）里面是否有元素*/
        while (cursor.hasNext()){
            /*每次移动一次指针，取出指针地址下中对应的每一个文档*/
            Document docu = cursor.next();
            String temp = DateUtil.dateToString("yyyy-MM-dd HH:mm:ss",(Date) docu.get("userbirth"));
            /*给定key可以返回key下对应的value*/
            System.out.println(docu.get("username")+"\t"+docu.get("userage")+"\t"+docu.get("userdesc")+"\t"+docu.get("userLike")+"\t"+temp);
        }

    }
}
