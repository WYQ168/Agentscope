package com.company.flowassistant.tool;

import com.company.flowassistant.model.ParsedInvoice;
import com.company.flowassistant.model.ProcessForm;
import org.springframework.stereotype.Component;

@Component
public class ProcessTemplateTool {

    public String detectProcessType(String userInput, ParsedInvoice parsedInvoice) {
        if ((userInput != null && userInput.contains("报销"))
                || (parsedInvoice != null && "差旅".equals(parsedInvoice.getExpenseType()))) {
            return "TRAVEL_EXPENSE";
        }
        return "GENERAL_EXPENSE";
    }

    public ProcessForm buildForm(String processType, String userInput, ParsedInvoice invoice) {
        ProcessForm form = new ProcessForm();
        form.setProcessType(processType);

        if (invoice != null) {
            form.put("amount", invoice.getAmount());
            form.put("invoiceDate", invoice.getInvoiceDate());
            form.put("vendor", invoice.getVendor());
            form.put("expenseType", invoice.getExpenseType());
        }

        form.put("reason", extractReason(userInput));
        form.put("title", "AI自动发起-" + ("TRAVEL_EXPENSE".equals(processType) ? "差旅报销" : "费用报销"));
        return form;
    }

    private String extractReason(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return "AI自动补全";
        }
        return userInput;
    }
}