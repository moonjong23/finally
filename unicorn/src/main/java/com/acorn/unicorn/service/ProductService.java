package com.acorn.unicorn.service;

import java.util.List;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> selectProductList() throws Exception;

//	void insertBoard(BoardBean bean) throws Exception;
//
//	BoardDTO selectBoardDetail(int idx) throws Exception;

}
