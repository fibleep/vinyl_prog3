package com.musicdatabase.service.controller;

import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.model.session.SessionHistory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/history")
public class HistoryController {
    private final SessionHistory sessionHistory;

    public HistoryController(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    public void addPageVisit(PageVisit pageVisit) {
        sessionHistory.addPageVisit(pageVisit);
    }

    @GetMapping
    public ModelAndView showHistory(HttpServletRequest request) {
        addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("history")
                .addObject("pageVisits", sessionHistory.getPageVisits());
    }

}
