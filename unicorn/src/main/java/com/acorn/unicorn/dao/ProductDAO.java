package com.acorn.unicorn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.dto.ProductDTO;
import com.acorn.unicorn.dto.UserDTO;

@Repository("productDAO")
public class ProductDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<ProductDTO> selectProductList() throws Exception{
		 return selectList("product.selectProductList");
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
}
