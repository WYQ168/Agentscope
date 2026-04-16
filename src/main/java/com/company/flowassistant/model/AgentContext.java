package com.company.flowassistant.model;

import java.util.ArrayList;
import java.util.List;

public class AgentContext {
    private String userId;
    private String sessionId;
    private String userInput;
    private ParsedInvoice parsedInvoice;
    private List<String> trace = new ArrayList<>();

    public void addTrace(String log) {
        this.trace.add(log);
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getUserInput() { return userInput; }
    public void setUserInput(String userInput) { this.userInput = userInput; }

    public ParsedInvoice getParsedInvoice() { return parsedInvoice; }
    public void setParsedInvoice(ParsedInvoice parsedInvoice) { this.parsedInvoice = parsedInvoice; }

    public List<String> getTrace() { return trace; }
    public void setTrace(List<String> trace) { this.trace = trace; }
}