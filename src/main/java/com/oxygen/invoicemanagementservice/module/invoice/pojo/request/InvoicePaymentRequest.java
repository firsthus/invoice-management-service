package com.oxygen.invoicemanagementservice.module.invoice.pojo.request;

import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InvoicePaymentRequest implements Serializable {

    @NotBlank
    private String invoiceUuid;

    @NotNull
    private PaymentGateway paymentGateway;

}
