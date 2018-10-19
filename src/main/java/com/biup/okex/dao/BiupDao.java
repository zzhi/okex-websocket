package com.biup.okex.dao;

import com.biup.okex.core.dao.base.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zzhi
 */
@Repository
public class BiupDao extends BaseDao {

    @Resource
    public void setBiupSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
