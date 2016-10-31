package com.acorn.unicorn.service;

import java.util.List;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> selectBoardList() throws Exception;

	void insertBoard(BoardBean bean) throws Exception;

	BoardDTO selectBoardDetail(int idx) throws Exception;

}
