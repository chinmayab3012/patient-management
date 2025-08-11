package com.pm.doctorservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PagedDoctorResponseDto {
    private final List<DoctorResponseDTO> doctors;
    private final int totalPages;
    private final int totalElements;
    private final int pageNumber;
    private final int pageSize;

    public List<DoctorResponseDTO> getDoctors() {
        return doctors;
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

    @JsonCreator
    private PagedDoctorResponseDto(@JsonProperty("doctors") List<DoctorResponseDTO> doctors,
                                   @JsonProperty("totalPages") int totalPages,
                                   @JsonProperty("totalElements") int totalElements,
                                   @JsonProperty("pageNumber") int pageNumber,
                                   @JsonProperty("pageSize") int pageSize) {
        this.doctors = doctors;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<DoctorResponseDTO> doctors;
        private int totalPages;
        private int totalElements;
        private int pageNumber;
        private int pageSize;

        public Builder doctors(List<DoctorResponseDTO> doctors) {
            this.doctors = doctors;
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

        public PagedDoctorResponseDto build() {
            return new PagedDoctorResponseDto(doctors, totalPages, totalElements, pageNumber, pageSize);
        }


    }
}