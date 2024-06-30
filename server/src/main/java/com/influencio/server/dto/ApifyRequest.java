package com.influencio.server.dto;

import java.util.List;

public class ApifyRequest {
    private int resultsLimit;
    private List<String> username;

    // Getters and setters

    public int getResultsLimit() {
        return resultsLimit;
    }

    public void setResultsLimit(int resultsLimit) {
        this.resultsLimit = resultsLimit;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    // ...
}