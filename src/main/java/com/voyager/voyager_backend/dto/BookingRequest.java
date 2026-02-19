package com.voyager.voyager_backend.dto;

public class BookingRequest {

    private String destinationSlug;
    private String startDate;
    private String endDate;
    private int travelers;

    public String getDestinationSlug() { return destinationSlug; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public int getTravelers() { return travelers; }
}
