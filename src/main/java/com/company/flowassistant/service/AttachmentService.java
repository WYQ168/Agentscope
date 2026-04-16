package com.company.flowassistant.service;

import com.company.flowassistant.model.AttachmentMeta;

public interface AttachmentService {

    /**
     * 根据附件ID获取文件二进制内容
     */
    byte[] loadBytes(String attachmentId);

    /**
     * 获取文件元信息（可选）
     */
    AttachmentMeta getMeta(String attachmentId);
}