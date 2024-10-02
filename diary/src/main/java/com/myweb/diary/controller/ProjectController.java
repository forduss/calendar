package com.myweb.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.diary.dto.ProjectDto;
import com.myweb.diary.service.ProjectService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project")
    public String projectPage(Model model) {
        return "project";
    }

    @PostMapping("/project")
    @ResponseBody
    public ProjectDto addProject(@RequestBody ProjectDto dto) {
    	System.out.println("일정 추가");
    	ProjectDto result = projectService.createProject(dto);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Project could not be created");
        }
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteEvent(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        System.out.println("일정 삭제");
        return "redirect:/calendar";
    }

    @GetMapping("/calendar/project")
    @ResponseBody
    public List<ProjectDto> getAllProjects() {
        System.out.println("일정 불러오기");
        return projectService.getAllProjects();
    }
    
    @PutMapping("/updateProject/{id}")
    @ResponseBody
    public ProjectDto updateProject(@PathVariable("id") int id, @RequestBody ProjectDto dto) {
    	System.out.println("일정 수정");
        dto.setId(id);
        System.out.println(dto.getId());
        projectService.updateProject(dto);
        return dto;
    }
}

