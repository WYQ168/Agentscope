package com.company.flowassistant.agent;

import com.company.flowassistant.model.*;
import com.company.flowassistant.tool.FileParseTool;
import com.company.flowassistant.tool.OaClawTool;
import com.company.flowassistant.tool.ProcessTemplateTool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseProcessAgent {

    private final FileParseTool fileParseTool;
    private final ProcessTemplateTool processTemplateTool;
    private final OaClawTool oaClawTool;

    public ExpenseProcessAgent(FileParseTool fileParseTool,
                               ProcessTemplateTool processTemplateTool,
                               OaClawTool oaClawTool) {
        this.fileParseTool = fileParseTool;
        this.processTemplateTool = processTemplateTool;
        this.oaClawTool = oaClawTool;
    }

    public ChatResponse handle(ChatRequest request) {
        AgentContext ctx = new AgentContext();
        ctx.setUserId(request.getUserId());
        ctx.setSessionId(request.getSessionId());
        ctx.setUserInput(request.getMessage());
        ctx.setTrace(new ArrayList<>());

        try {
            ctx.addTrace("Step1: 识别用户意图为报销类流程");

            ParsedInvoice parsedInvoice = null;
            if (request.getAttachmentIds() != null && !request.getAttachmentIds().isEmpty()) {
                ctx.addTrace("Step2: 调用文件解析工具");
                parsedInvoice = fileParseTool.parseInvoice(request.getAttachmentIds().get(0));
                ctx.setParsedInvoice(parsedInvoice);
            }

            ctx.addTrace("Step3: 匹配流程模板");
            String processType = processTemplateTool.detectProcessType(request.getMessage(), parsedInvoice);

            ctx.addTrace("Step4: 组装表单");
            ProcessForm form = processTemplateTool.buildForm(processType, request.getMessage(), parsedInvoice);

            ctx.addTrace("Step5: 调用OA Claw执行页面自动化");
            ToolResult result = oaClawTool.createProcess(processType, form, ctx);

            if (!result.isSuccess()) {
                return ChatResponse.fail("流程提交失败：" + result.getMessage());
            }

            ChatResponse resp = ChatResponse.success("已为你发起流程：" + processType);
            resp.setProcessType(processType);
            resp.setProcessInstanceId(result.getBizId());
            resp.setPayload(ctx.getTrace());
            return resp;

        } catch (Exception e) {
            return ChatResponse.fail("Agent执行异常：" + e.getMessage());
        }
    }
}