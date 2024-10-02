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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.diary.dto.EventDto;
import com.myweb.diary.service.EventService;

@Controller
public class CalendarController {

    @Autowired
    private EventService eventService;

    @GetMapping("/calendar")
    public String calendarPage(Model model) {
        return "calendar";
    }

    @PostMapping("/calendar")
    @ResponseBody
    public EventDto addEvent(@RequestBody EventDto dto) {
    	System.out.println("일정 추가");
        EventDto result = eventService.createEvent(dto);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Event could not be created");
        }
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        System.out.println("일정 삭제");
        return "redirect:/calendar";
    }

    @GetMapping("/calendar/events")
    @ResponseBody
    public List<EventDto> getAllEvents(@RequestParam("userid") String userid) {
    	System.out.println("일정 불러오기");
        return eventService.getUserEvents(userid); // 현재 사용자
    }
    
    @PutMapping("/updateEvent/{id}")
    @ResponseBody
    public EventDto updateEvent(@PathVariable("id") int id, @RequestBody EventDto dto) {
    	System.out.println("일정 수정");
        dto.setId(id);
        System.out.println(dto.getId());
        eventService.updateEvent(dto);
        return dto;
    }
}