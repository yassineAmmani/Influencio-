package com.influencio.server.dto;

public class InfluencerListRequest {
    private boolean addParentData;
    private boolean enhanceUserSearchWithFacebookPage;
    private boolean isUserTaggedFeedURL;
    private int resultsLimit;
    private String resultsType;
    private String search;
    private int searchLimit;
    private String searchType;
    private String username;  // Add this field

    // Add getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and setters

    public boolean isAddParentData() {
        return addParentData;
    }

    public void setAddParentData(boolean addParentData) {
        this.addParentData = addParentData;
    }

    public boolean isEnhanceUserSearchWithFacebookPage() {
        return enhanceUserSearchWithFacebookPage;
    }

    public void setEnhanceUserSearchWithFacebookPage(boolean enhanceUserSearchWithFacebookPage) {
        this.enhanceUserSearchWithFacebookPage = enhanceUserSearchWithFacebookPage;
    }

    public boolean isUserTaggedFeedURL() {
        return isUserTaggedFeedURL;
    }

    public void setUserTaggedFeedURL(boolean userTaggedFeedURL) {
        isUserTaggedFeedURL = userTaggedFeedURL;
    }

    public int getResultsLimit() {
        return resultsLimit;
    }

    public void setResultsLimit(int resultsLimit) {
        this.resultsLimit = resultsLimit;
    }

    public String getResultsType() {
        return resultsType;
    }

    public void setResultsType(String resultsType) {
        this.resultsType = resultsType;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getSearchLimit() {
        return searchLimit;
    }

    public void setSearchLimit(int searchLimit) {
        this.searchLimit = searchLimit;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}