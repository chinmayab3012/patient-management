CREATE TABLE IF NOT EXISTS doctor (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255) NOT NULL UNIQUE,
    speciality VARCHAR(255) NOT NULL,
    years_of_experience INT NOT NULL,
    created_date TIMESTAMP NOT NULL
);

TRUNCATE TABLE doctor;

INSERT INTO doctor (id, full_name, email, phone, speciality, years_of_experience, created_date) VALUES
(gen_random_uuid(), 'Dr. Alex Smith', 'alex.smith@example.com', '1234567890', 'Cardiology', 15, NOW()),
(gen_random_uuid(), 'Dr. Maria Garcia', 'maria.garcia@example.com', '0987654321', 'Pediatrics', 10, NOW()),
(gen_random_uuid(), 'Dr. John Doe', 'john.doe@example.com', '1122334455', 'Dermatology', 20, NOW());