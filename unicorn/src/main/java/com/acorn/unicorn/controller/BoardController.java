package com.acorn.unicorn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.service.BoardService;

@Controller
public class BoardController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value = "/views/BoardList.do")
	public ModelAndView BoardList(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/views/boardlist");

		List<BoardDTO> list = (List<BoardDTO>) boardService.selectBoardList();
		mv.addObject("list", list);

		return mv;
	}
	
	@RequestMapping(value = "/views/BoardDetail.do")
	public ModelAndView BoardDetail(@RequestParam("idx") int idx) throws Exception {
		ModelAndView mv = new ModelAndView("/views/boardDetail");
		
		BoardDTO dto = boardService.selectBoardDetail(idx);
		mv.addObject("dto", dto);
		
		return mv;
	}

	@RequestMapping(value = "/views/BoardWrite.do")
	public ModelAndView BoardWirte(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("userid") == null || session.getAttribute("userid").equals(""))
			mv.setViewName("/login/login");
		else
			mv.setViewName("/views/boardwrite");

		return mv;
	}

	@RequestMapping(value = "/views/insertBoard.do")
	public ModelAndView insertBoard(BoardBean bean) throws Exception {

		ModelAndView mv = new ModelAndView("redirect:/views/BoardList.do");

		boardService.insertBoard(bean);

		return mv;
	}
}