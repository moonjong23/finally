package com.acorn.unicorn.service;

import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dto.UserDTO;

public interface JoinService {
	void insertUser(UserBean bean) throws Exception;
	
	UserDTO configUser(UserBean bean) throws Exception;
}
