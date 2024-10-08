package com.myweb.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.diary.com.Search;
import com.myweb.diary.dto.BoardDto;
import com.myweb.diary.dto.PostDto;
import com.myweb.diary.dto.ReplyDto;


@Service
public interface BoardService {
	List<PostDto> getPostList();
	List<PostDto> getPostListByBoard(int board_no, Search page);
	List<PostDto> getPostListByKeyword(int board_no, Search page);
	PostDto putPost(PostDto dto);
	PostDto getPost(int post_no);
	void cntHitCnt(int post_no);
	void delPost(int post_no);
	PostDto editPost(PostDto dto);
	void putReply(ReplyDto dto);
	List<ReplyDto> getReply(int post_no);
	List<BoardDto> getBoardMenu();
	BoardDto getBoard(int board_no);
	int getBoardNo(int post_no);
	void editBoard(BoardDto dto);
	void deleteBoard(int board_no);
	void addBoard(BoardDto dto);
}
