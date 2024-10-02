package com.myweb.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.diary.dao.EventDao;
import com.myweb.diary.dto.EventDto;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public List<EventDto> getUserEvents(String userId) {
        return eventDao.getEventsByUserId(userId);
    }

    public EventDto getEvent(int id) {
        return eventDao.getEventById(id);
    }

    public EventDto createEvent(EventDto event) {
        eventDao.insertEvent(event);
        return event;
    }

    public void updateEvent(EventDto event) {
        eventDao.updateEvent(event);
    }

    public void deleteEvent(int id) {
        eventDao.deleteEvent(id);
    }
}