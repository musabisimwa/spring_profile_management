package com.example.profile_management.records;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRecord(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String userName,
        @NotNull String phoneNumber
    ){} 
