package com.company.flowassistant.service;

import com.company.flowassistant.model.ProcessForm;
import com.company.flowassistant.model.ToolResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OaClawClient {

    public ToolResult createProcess(String processType, ProcessForm form) {
        // 真实情况：
        // 1. 调你们的 OA Claw API
        // 2. 下发页面操作指令
        // 3. 返回执行结果

        ToolResult result = new ToolResult();
        result.setSuccess(true);
        result.setBizId(UUID.randomUUID().toString());
        result.setMessage("OA页面填充并提交成功");
        result.setPayload(form.getFields());
        return result;
    }
}