package com.acorn.unicorn.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.acorn.unicorn.bean.BasketBean;
import com.acorn.unicorn.bean.CartBean;
import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.dto.CartDTO;
import com.acorn.unicorn.dto.ProductDTO;
import com.acorn.unicorn.dto.UserDTO;

public class AbstractDAO {
    protected Log log = LogFactory.getLog(AbstractDAO.class);
     
    @Autowired
    private SqlSessionTemplate sqlSession;
     
    protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }
    
    public int insert(String queryId, BasketBean bean){
    	printQueryId(queryId);
    	return sqlSession.insert(queryId, bean);
    }
     
    public void update(String queryId, CartBean bean){
        printQueryId(queryId);
        sqlSession.update(queryId, bean);
    }
    
    public int update(String queryId, BasketBean bean){
    	printQueryId(queryId);
    	int re = sqlSession.update(queryId, bean);
    	return re;
    }
    
    public void update(String queryId, int idx){
    	printQueryId(queryId);
    	sqlSession.update(queryId, idx);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }
    
    public int delete(String queryId, BasketBean bean){
    	printQueryId(queryId);
    	return sqlSession.delete(queryId, bean);
    }
    
    public int update(String string, String string2){
    	return sqlSession.update(string, string2);
    }
     
    public UserDTO selectOne(String queryId, String id, String password){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, id);
    }
     
    public UserDTO selectOne(String queryId, UserBean bean){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, bean);
    }
    
    public ProductDTO selectOne(String queryId){
    	printQueryId(queryId);
    	return sqlSession.selectOne(queryId);
    }
    
    public BoardDTO selectOne(String queryId, int idx){   //detail
    	printQueryId(queryId);
    	return sqlSession.selectOne(queryId, idx);
    }
    
    public CartDTO selectOne(String queryId, CartBean bean){   //detail
    	printQueryId(queryId);
    	return sqlSession.selectOne(queryId, bean);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, String user_id){
    	printQueryId(queryId);
    	return sqlSession.selectList(queryId, user_id);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
}
