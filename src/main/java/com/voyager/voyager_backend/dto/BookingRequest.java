package com.voyager.voyager_backend.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private String destinationSlug;
    private String startDate;
    private String endDate;
    private int travelers;
}
