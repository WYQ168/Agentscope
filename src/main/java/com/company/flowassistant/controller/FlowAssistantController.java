package com.company.flowassistant.controller;

import com.company.flowassistant.app.FlowAssistantFacade;
import com.company.flowassistant.model.ChatRequest;
import com.company.flowassistant.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flow-assistant")
public class FlowAssistantController {

    private final FlowAssistantFacade facade;

    public FlowAssistantController(FlowAssistantFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return facade.handle(request);
    }
}