package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import patient.events.PatientEvent;

import java.util.function.Consumer;

//@Configuration
public class PatientCreatedEventReceiver {


    private static final Logger log = LoggerFactory.getLogger(PatientCreatedEventReceiver.class);

    //@Bean(name = "patientCreatedEventReceiverMethod")
    public Consumer<byte[]> patientCreatedEventReceiverMethod() {
        return eventBytes -> {
            try {
                PatientEvent patientEvent = PatientEvent.parseFrom(eventBytes);
                // ... perform any business related to analytics here
                //System.out.println("Received Patient Event: [PatientId=" + patientEvent.getPatientId() + ",PatientName=" + patientEvent.getName() + ",PatientEmail=" + patientEvent.getEmail() + "]");

                log.info("Received Patient Event: [PatientId={},PatientName={},PatientEmail={}]",
                        patientEvent.getPatientId(),
                        patientEvent.getName(),
                        patientEvent.getEmail());

            } catch (InvalidProtocolBufferException e) {
                log.error("Error deserializing event from Protobuf bytes: {}", e.getMessage(), e);
            } catch (Exception e) {
                // Catch any other unexpected exceptions during processing
                log.error("An unexpected error occurred while processing patient event: {}", e.getMessage(), e);
            }
        };

    }
}
