package com.pm.doctorservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class DoctorRequestDTO {
    @NotBlank(message = "Full Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^(\\+91|0)?[6-9]\\d{9}$", message = "invalid mobile number")
    @JsonProperty("mobile")
    private String phone;

    @NotBlank(message = "speciality is required")
    private String speciality;

    @NotNull(message = "year of experience is required")
    private int yearOfExperience;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }
}
