package com.acorn.unicorn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.dao.BoardDAO;
import com.acorn.unicorn.dto.BoardDTO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="boardDAO")
    private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		 return boardDAO.selectBoardList();
	}
	 
	@Override
	public void insertBoard(BoardBean bean) throws Exception {
		boardDAO.insertBoard(bean);
	}
	
	@Override
	public BoardDTO selectBoardDetail(int idx) throws Exception{
		boardDAO.updateHitCnt(idx);
		BoardDTO dto = boardDAO.selectBoardDetail(idx);
		return dto;
	}
}
