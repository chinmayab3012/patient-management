package com.pm.doctorservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentResponseDto implements Serializable {

    private final UUID id; // Made final as good practice for DTOs built with a builder
    private final UUID patientId; // Made final
    private final String patientName; // Made final
    private final LocalDateTime startTime; // Made final
    private final LocalDateTime endTime; // Made final
    private final String reason; // Made final
    private final Long version; // ➡️ Added version for optimistic locking // Made final

    @JsonCreator
    private AppointmentResponseDto(@JsonProperty("id") UUID id,
                                   @JsonProperty("patientId") UUID patientId,
                                   @JsonProperty("patientName") String patientName,
                                   @JsonProperty("startTime") LocalDateTime startTime,
                                   @JsonProperty("endTime") LocalDateTime endTime,
                                   @JsonProperty("reason") String reason,
                                   @JsonProperty("version") Long version) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.version = version;
    }

    // --- Getters ---
    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getReason() {
        return reason;
    }

    public Long getVersion() {
        return version;
    }

    // --- Builder Static Factory Method ---
    public static Builder builder() {
        return new Builder();
    }

    // --- Static Nested Builder Class ---
    public static class Builder {
        private UUID id;
        private UUID patientId;
        private String patientName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String reason;
        private Long version; // ➡️ Added version for optimistic locking

        // Private constructor to enforce usage of AppointmentResponseDto.builder()
        private Builder() {}

        public Builder id(UUID id) {
            this.id = id;
            return this; // Return 'this' to allow method chaining
        }

        public Builder patientId(UUID patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder patientName(String patientName) {
            this.patientName = patientName;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder version(Long version) {
            this.version = version;
            return this;
        }

        /**
         * Builds and returns an instance of AppointmentResponseDto
         * using the values set in the builder.
         *
         * @return A new AppointmentResponseDto instance.
         */
        public AppointmentResponseDto build() {
            return new AppointmentResponseDto(
                    this.id,
                    this.patientId,
                    this.patientName,
                    this.startTime,
                    this.endTime,
                    this.reason,
                    this.version
            );
        }
    }
}