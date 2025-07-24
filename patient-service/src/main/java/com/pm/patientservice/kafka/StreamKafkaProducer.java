/*
package com.pm.patientservice.kafka;

import com.google.common.net.MediaType;
import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class StreamKafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(StreamKafkaProducer.class);
    private static final String EVENT_TYPE_PATIENT_CREATED = "PATIENT_CREATED";

    private final StreamBridge streamBridge;

    public StreamKafkaProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    private PatientEvent buildPatientEvent(Patient patient, String eventType) {
        return PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType(eventType)
                .build();
    }

    public void sendPatientCreatedEvent(Patient patient) {
        try {
            PatientEvent patientEvent = buildPatientEvent(patient, EVENT_TYPE_PATIENT_CREATED);

            log.info("Sending patient created event for patient object : {}", patientEvent);

            byte[] byteArray = patientEvent.toByteArray();

            log.info("Is the byte array null? {}", byteArray == null);

            Message<byte[]> message = null;
            if (byteArray != null) {
                message = MessageBuilder
                         .withPayload(byteArray)
                        .setHeader("contentType", MediaType.PROTOBUF)
                        .build();

                boolean send = streamBridge.send("sendPatientCreatedEvent-out-0", message);

                if(send) {
                    log.info("Patient created event sent successfully");
                }

            }


        } catch (Exception e) {
            log.error("Error sending patient created event: {}", e.getMessage() ,e);
        }
    }


}
*/