package com.company.flowassistant.model;

public class ChatResponse {
    private boolean success;
    private String message;
    private String processInstanceId;
    private String processType;
    private Object payload;

    public static ChatResponse success(String message) {
        ChatResponse resp = new ChatResponse();
        resp.success = true;
        resp.message = message;
        return resp;
    }

    public static ChatResponse fail(String message) {
        ChatResponse resp = new ChatResponse();
        resp.success = false;
        resp.message = message;
        return resp;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getProcessInstanceId() { return processInstanceId; }
    public void setProcessInstanceId(String processInstanceId) { this.processInstanceId = processInstanceId; }
    public String getProcessType() { return processType; }
    public void setProcessType(String processType) { this.processType = processType; }
    public Object getPayload() { return payload; }
    public void setPayload(Object payload) { this.payload = payload; }
}