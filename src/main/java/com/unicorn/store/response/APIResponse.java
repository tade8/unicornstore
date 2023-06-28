package com.unicorn.store.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class APIResponse {
    private boolean isSuccessful;
    private Object data;
    private HttpStatus status;
}
