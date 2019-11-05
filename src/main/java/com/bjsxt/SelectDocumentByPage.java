package com.bjsxt;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * MOngoDB分页查询
 * @author lvyelanshan
 * @create 2019-11-05 21:26
 */
public class SelectDocumentByPage {

    /**
     * 通过skip与limit方法实现分页
     */
    public void selectDocumentByPageUseSkipAndLimit(int indexPage){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        long countNum = collection.countDocuments();
        FindIterable iterable = collection.find().skip(0).limit(2);

    }
}
