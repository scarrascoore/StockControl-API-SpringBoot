package com.portfolio.stockcontrol_api_springboot.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequest(
        @NotBlank(message = "The category name is required")
        String name,

        @Size(max = 100, message = "The description must have a maximum of 300 characters")
        String description
) {
}
