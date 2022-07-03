package com.snow.demo.model;

public class SubmittedSource {
    private String username;
    private String lang;
    private String source;
    private Long problemId;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
    public Long getProblemId() {
        return problemId;
    }
}
