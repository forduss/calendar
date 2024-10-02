package com.myweb.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myweb.diary.dto.BoardDto;
import com.myweb.diary.service.BoardService;


@ControllerAdvice
public class IndexController {
	@Autowired
	private BoardService service;
	
	@ModelAttribute("menu")
	public List<BoardDto> getMenu(){
		return service.getBoardMenu();
	}
}
