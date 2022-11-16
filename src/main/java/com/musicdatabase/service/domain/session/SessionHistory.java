package com.musicdatabase.service.domain.session;

import java.util.ArrayList;
import java.util.List;

public class SessionHistory {
    private final List<PageVisit> pageVisits;
    public SessionHistory() {
        this.pageVisits = new ArrayList<>();
    }
}
