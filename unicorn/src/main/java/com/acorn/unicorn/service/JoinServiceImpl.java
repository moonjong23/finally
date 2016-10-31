package com.acorn.unicorn.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dao.BoardDAO;
import com.acorn.unicorn.dto.UserDTO;

@Service("joinService")
public class JoinServiceImpl implements JoinService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="boardDAO")
    private BoardDAO boardDAO;
	
    @Override
    public void insertUser(UserBean bean) throws Exception {
    	boardDAO.insertUser(bean);
    	
    }
    
    @Override
    public UserDTO configUser(UserBean bean) throws Exception {
    	return boardDAO.configUser(bean);
    }
}
