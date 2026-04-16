package com.company.flowassistant.agent;

import com.company.flowassistant.model.ChatRequest;
import com.company.flowassistant.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
public class AgentOrchestrator {

    private final ExpenseProcessAgent expenseProcessAgent;
    private final ApprovalSuggestAgent approvalSuggestAgent;

    public AgentOrchestrator(ExpenseProcessAgent expenseProcessAgent,
                             ApprovalSuggestAgent approvalSuggestAgent) {
        this.expenseProcessAgent = expenseProcessAgent;
        this.approvalSuggestAgent = approvalSuggestAgent;
    }

    public ChatResponse route(ChatRequest request) {
        String text = request.getMessage();

        if (containsAny(text, "报销", "差旅", "发票", "费用")) {
            return expenseProcessAgent.handle(request);
        }

        if (containsAny(text, "审批建议", "是否通过", "风险", "要不要批")) {
            return approvalSuggestAgent.handle(request);
        }

        return ChatResponse.fail("暂时无法识别该流程意图，建议转人工或走兜底规则");
    }

    private boolean containsAny(String text, String... keywords) {
        if (text == null) {
            return false;
        }
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}