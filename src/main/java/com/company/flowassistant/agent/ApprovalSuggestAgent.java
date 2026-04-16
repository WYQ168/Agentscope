package com.company.flowassistant.agent;

import com.company.flowassistant.model.ChatRequest;
import com.company.flowassistant.model.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApprovalSuggestAgent {

    public ChatResponse handle(ChatRequest request) {
        // Demo版：用规则模拟
        String text = request.getMessage();
        String suggestion;
        String riskLevel;

        if (text != null && (text.contains("超预算") || text.contains("异常"))) {
            suggestion = "建议转人工复核，不建议自动通过";
            riskLevel = "HIGH";
        } else {
            suggestion = "建议通过";
            riskLevel = "LOW";
        }

        ChatResponse response = ChatResponse.success("审批建议生成成功");
        response.setPayload(List.of(
                "识别审批意图",
                "分析风险等级=" + riskLevel,
                "生成建议=" + suggestion
        ));
        return response;
    }
}