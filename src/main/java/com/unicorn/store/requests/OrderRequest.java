package com.unicorn.store.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String amount;
    @NotBlank
    private String description;
    private String redirectUrl;
    private boolean collectPhone;
}
