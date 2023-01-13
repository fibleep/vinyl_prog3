package com.musicdatabase.service.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionHistory {
    private final List<PageVisit> pageVisits;

    public SessionHistory() {
        this.pageVisits = new ArrayList<>();
    }

    public void addPageVisit(PageVisit pageVisit) {
        pageVisits.add(pageVisit);
    }

    public List<PageVisit> getPageVisits() {
        return pageVisits;
    }
}
