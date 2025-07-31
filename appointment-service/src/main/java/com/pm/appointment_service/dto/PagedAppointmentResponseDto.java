package com.pm.appointment_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PagedAppointmentResponseDto implements Serializable {
    private final List<AppointmentResponseDto> appointmentResponseDtos;
    private final int totalPages;
    private final int totalElements;
    private final int pageNumber;
    private final int pageSize;

    public PagedAppointmentResponseDto(  @JsonProperty("appointmentResponseDtos") List<AppointmentResponseDto> appointmentResponseDtos,
                                         @JsonProperty("totalPages") int totalPages,
                                         @JsonProperty("totalElements") int totalElements,
                                         @JsonProperty("pageNumber") int pageNumber,
                                         @JsonProperty("pageSize") int pageSize) {
        this.appointmentResponseDtos = appointmentResponseDtos;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public List<AppointmentResponseDto> getAppointmentResponseDtos() {
        return appointmentResponseDtos;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private List<AppointmentResponseDto> appointmentResponseDtos;
        private int totalPages;
        private int totalElements;
        private int pageNumber;
        private int pageSize;

        public Builder appointmentResponseDtos(List<AppointmentResponseDto> appointmentResponseDtos) {
            this.appointmentResponseDtos = appointmentResponseDtos;
            return this;
        }

        public Builder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder totalElements(int totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PagedAppointmentResponseDto build() {
            return new PagedAppointmentResponseDto(appointmentResponseDtos, totalPages, totalElements, pageNumber, pageSize);
        }
    }

}
