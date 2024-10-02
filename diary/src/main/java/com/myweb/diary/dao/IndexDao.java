package com.myweb.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.myweb.diary.dto.BoardDto;


@Mapper
public interface IndexDao {
	@Select("select * from board")
	public List<BoardDto> selectBoard() throws DataAccessException;
}
