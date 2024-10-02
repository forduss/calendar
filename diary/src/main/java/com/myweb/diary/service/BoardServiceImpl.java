package com.myweb.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.diary.com.Search;
import com.myweb.diary.dao.BoardDao;
import com.myweb.diary.dto.BoardDto;
import com.myweb.diary.dto.PostDto;
import com.myweb.diary.dto.ReplyDto;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao dao;
		
	public List<PostDto> getPostList(){
		return dao.selectPostListAll();
	}
	
	public List<PostDto> getPostListByBoard(int board_no, Search search){
		search.calcPage(dao.selectPostCntByKeyword(board_no, search.getKeyword()));
		int offset = search.getOffset();
		int cnt = search.getRecordSize();
		String keyword = search.getKeyword();
		return dao.selectPostListByKeyword(board_no, offset, cnt, keyword);
	}
	
	public List<PostDto> getPostListByKeyword(int board_no, Search page) {
		page.calcPage(dao.selectPostCntByKeyword(board_no, page.getKeyword()));
		int offset = page.getOffset();
		int cnt = page.getRecordSize();
		String keyword = page.getKeyword();
		return dao.selectPostListByKeyword(board_no, offset, cnt, keyword);
	}
	
	public PostDto putPost(PostDto dto) {
		dao.insertPost(dto);
		return dao.selectPostByPostNo(dto.getPost_no());
	}
	
	public PostDto getPost(int post_no) {
		return dao.selectPostByPostNo(post_no);
	}
	
	public void cntHitCnt(int post_no) {
		dao.updateHitCnt(post_no);
	}
	
	public void delPost(int post_no) {
		dao.deletePost(post_no);
	}
	
	public PostDto editPost(PostDto dto) {
		dao.updatePost(dto);
		return dao.selectPostByPostNo(dto.getPost_no());
	}
	
	public void putReply(ReplyDto dto) {
		dao.insertReply(dto);
	}
	
	public List<ReplyDto> getReply(int post_no){
		return dao.selectReply(post_no);
	}

	public List<BoardDto> getBoardMenu(){
		return dao.selectBoardList();
	}
	
	public BoardDto getBoard(int board_no) {
		return dao.selectBoard(board_no);
	}
	
	public int getBoardNo(int post_no) {
		return dao.selectBoardNoByPostNo(post_no);
	}
	
	public void editBoard(BoardDto dto) {
		dao.updateBoard(dto);
	}
	
	public void deleteBoard(int board_no) {
		dao.deleteBoard(board_no);
	}
	
	public void addBoard(BoardDto dto) {
		dao.insertBoard(dto);
	}
	
}