package com.example.profile_management.records;

import lombok.Builder;

@Builder
public record ErrorResponse(int errorCode,String reason) {
}
