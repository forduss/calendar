package com.myweb.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import com.myweb.diary.dto.EventDto;

@Mapper
public interface EventDao {

    @Select("SELECT * FROM events WHERE userid = #{userid}")
    List<EventDto> getEventsByUserId(@Param("userid") String userid) throws DataAccessException;

    @Select("SELECT * FROM events WHERE id = #{id}")
    EventDto getEventById(@Param("id") int id) throws DataAccessException;

    @Insert("INSERT INTO events (userid, title, start, end, color, content) VALUES (#{userid}, #{title}, #{start}, #{end}, #{color}, #{content})")
    void insertEvent(EventDto event) throws DataAccessException;

    @Update("UPDATE events SET title = #{title}, start = #{start}, end = #{end}, color = #{color}, content = #{content} WHERE id = #{id}")
    void updateEvent(EventDto event) throws DataAccessException;

    @Delete("DELETE FROM events WHERE id = #{id}")
    void deleteEvent(@Param("id") int id) throws DataAccessException;
}
