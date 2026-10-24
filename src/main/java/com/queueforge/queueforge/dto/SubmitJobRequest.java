package com.queueforge.queueforge.dto;

import jakarta.validation.constraints.NotBlank;

public record SubmitJobRequest(@NotBlank String type ,@NotBlank String payload) {
    
}
