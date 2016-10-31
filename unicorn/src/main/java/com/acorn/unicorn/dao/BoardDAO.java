package com.acorn.unicorn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.dto.UserDTO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<BoardDTO> selectBoardList() throws Exception{
		 return selectList("board.selectBoardList");
	}
	
	@SuppressWarnings("unchecked")
	public BoardDTO selectBoardDetail(int idx) throws Exception{
		return selectOne("board.selectBoardDetail", idx);
	}

	public void insertBoard(BoardBean bean) {
		insert("board.insertBoard", bean);
		
	}
	
	public void insertUser(UserBean bean) {
		insert("user.insertUser", bean);
		
	}
	
	public UserDTO configUser(String id, String password) throws Exception{
		return selectOne("user.configUser", id, password);
		
	}

	public UserDTO configUser(UserBean bean) {
		return selectOne("user.configUser", bean);
	}

	public void updateHitCnt(int idx) throws Exception{   //hit up
		update("board.updateHitCnt", idx);
	}
 
}
