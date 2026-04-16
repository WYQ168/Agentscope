package com.company.flowassistant.service.impl;

import com.company.flowassistant.model.AttachmentMeta;
import com.company.flowassistant.service.AttachmentService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MockAttachmentService implements AttachmentService {

    @Override
    public byte[] loadBytes(String attachmentId) {
        // Demo：模拟从存储系统读取
        String fakeContent = """
            发票代码: INV-20260301
            金额: 1280.50
            日期: 2026-03-01
            商户: 杭州某酒店
            类型: 差旅
        """;
        return fakeContent.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public AttachmentMeta getMeta(String attachmentId) {
        AttachmentMeta meta = new AttachmentMeta();
        meta.setFileName("invoice.pdf");
        meta.setContentType("application/pdf");
        meta.setSize(1024);
        return meta;
    }
}