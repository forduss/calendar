package com.myweb.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.diary.dao.ProjectDao;
import com.myweb.diary.dto.ProjectDto;


@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<ProjectDto> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public ProjectDto getProject(int id) {
        return projectDao.getProjectById(id);
    }

    public ProjectDto createProject(ProjectDto project) {
    	projectDao.insertProject(project);
        return project;
    }

    public void updateProject(ProjectDto project) {
    	projectDao.updateProject(project);
    }

    public void deleteProject(int id) {
    	projectDao.deleteProject(id);
    }
}