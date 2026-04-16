package com.company.flowassistant.tool;

import com.company.flowassistant.model.AttachmentMeta;
import com.company.flowassistant.model.ParsedInvoice;
import com.company.flowassistant.service.AttachmentService;
import org.springframework.stereotype.Component;

@Component
public class FileParseTool {

    private final AttachmentService attachmentService;

    public FileParseTool(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public ParsedInvoice parseInvoice(String attachmentId) {

        // 1. 获取文件
        byte[] fileBytes = attachmentService.loadBytes(attachmentId);

        // 2. 获取文件信息（可选）
        AttachmentMeta meta = attachmentService.getMeta(attachmentId);

        // 3. 根据类型选择解析方式
        if (meta.getContentType().equals("application/pdf")) {
            return parsePdf(fileBytes);
        }

        // fallback
        return parseText(fileBytes);
    }

    private ParsedInvoice parsePdf(byte[] bytes) {
        // 真实情况：调用OCR或大模型
        ParsedInvoice invoice = new ParsedInvoice();
        invoice.setAmount(1280.50);
        invoice.setExpenseType("差旅");
        invoice.setVendor("杭州某酒店");
        invoice.setInvoiceDate("2026-03-01");
        return invoice;
    }

    private ParsedInvoice parseText(byte[] bytes) {
        // Demo解析
        String content = new String(bytes);
        ParsedInvoice invoice = new ParsedInvoice();
        invoice.setAmount(1280.50);
        invoice.setExpenseType("差旅");
        return invoice;
    }
}