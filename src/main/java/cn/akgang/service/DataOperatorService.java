package cn.akgang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author akgang
 * @date 2017/10/18.
 */
@Repository
public class DataOperatorService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertEntity(Object object) {
        mongoTemplate.insert(object);
    }

    public List findList(Query query, Class entity) {
        return mongoTemplate.find(query, entity);
    }

    public Object findOne(Query query, Class entity) {
        return mongoTemplate.findOne(query, entity);
    }

}
