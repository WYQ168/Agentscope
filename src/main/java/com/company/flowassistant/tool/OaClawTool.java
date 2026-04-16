package com.company.flowassistant.tool;

import com.company.flowassistant.model.AgentContext;
import com.company.flowassistant.model.ProcessForm;
import com.company.flowassistant.model.ToolResult;
import com.company.flowassistant.service.OaClawClient;
import org.springframework.stereotype.Component;

@Component
public class OaClawTool {

    private final OaClawClient oaClawClient;

    public OaClawTool(OaClawClient oaClawClient) {
        this.oaClawClient = oaClawClient;
    }

    public ToolResult createProcess(String processType, ProcessForm form, AgentContext ctx) {
        ctx.addTrace("OA Claw Tool: 准备调用自动化执行");
        return oaClawClient.createProcess(processType, form);
    }
}