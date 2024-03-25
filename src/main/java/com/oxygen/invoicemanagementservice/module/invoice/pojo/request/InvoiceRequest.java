package com.oxygen.invoicemanagementservice.module.invoice.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@GroupSequence({InvoiceRequest.class, InvoiceRequest.InvoiceRequestExtendedValidation.class})
public class InvoiceRequest implements Serializable {

    @NotNull
    private LocalDate dueDate;

    @NotBlank
    private String customerName;

    @Email
    @NotBlank
    private String customerEmail;

    @Valid
    @NotEmpty
    private Set<InvoiceItemRequest> items;





    @JsonIgnore
    @AssertTrue(message = "Due date cannot be in the past.",
            groups = InvoiceRequest.InvoiceRequestExtendedValidation.class)
    public boolean isDueDateAfterNow() {
        return dueDate.isAfter(LocalDate.now());
    }



    public interface InvoiceRequestExtendedValidation {}

}
