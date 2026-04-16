package com.company.flowassistant.model;

import java.util.List;

public class ChatRequest {
    private String userId;
    private String sessionId;
    private String message;
    private List<String> attachmentIds;
    private String pageScene; // add/edit/view/all

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getAttachmentIds() { return attachmentIds; }
    public void setAttachmentIds(List<String> attachmentIds) { this.attachmentIds = attachmentIds; }

    public String getPageScene() { return pageScene; }
    public void setPageScene(String pageScene) { this.pageScene = pageScene; }
}