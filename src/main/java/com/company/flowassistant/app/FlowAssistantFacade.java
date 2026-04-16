package com.company.flowassistant.app;

import com.company.flowassistant.agent.AgentOrchestrator;
import com.company.flowassistant.model.ChatRequest;
import com.company.flowassistant.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class FlowAssistantFacade {

    private final AgentOrchestrator agentOrchestrator;

    public FlowAssistantFacade(AgentOrchestrator agentOrchestrator) {
        this.agentOrchestrator = agentOrchestrator;
    }

    public ChatResponse handle(ChatRequest request) {
        if (request == null || request.getMessage() == null || request.getMessage().isBlank()) {
            return ChatResponse.fail("用户输入不能为空");
        }
        return agentOrchestrator.route(request);
    }
}