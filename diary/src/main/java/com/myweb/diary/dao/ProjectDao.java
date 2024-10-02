package com.myweb.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import com.myweb.diary.dto.ProjectDto;

@Mapper
public interface ProjectDao {

    @Select("select * from project")
    List<ProjectDto> getAllProjects() throws DataAccessException;

    @Select("SELECT * from project where id = #{id}")
    ProjectDto getProjectById(@Param("id") int id) throws DataAccessException;

    @Insert("INSERT INTO project (id, title, start, end, color) values (null,#{title},#{start},#{end},#{color})")
    void insertProject(ProjectDto project) throws DataAccessException;
    
    @Update("UPDATE project SET title = #{title}, start = #{start}, end = #{end} WHERE id = #{id}")
    void updateProject(ProjectDto project) throws DataAccessException;
    
    @Delete("DELETE FROM project WHERE id = #{id}")
    void deleteProject(@Param("id") int id) throws DataAccessException;
}
