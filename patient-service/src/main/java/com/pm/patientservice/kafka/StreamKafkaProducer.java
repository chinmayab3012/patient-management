
package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
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
         /*   Message<byte[]> message = MessageBuilder
                    .withPayload(patientEvent.toByteArray())
                    .setHeader("contentType", "application/x-protobuf") // Or whatever your consumer expects
                    .build();

            boolean send = streamBridge.send("sendPatientCreatedEvent-out-0", message);
*/
            boolean send = streamBridge.send("sendPatientCreatedEvent-out-0", patientEvent.toByteArray());
            if(send) {
                log.info("Patient created event sent successfully");
            }
        } catch (Exception e) {
            log.error("Error sending patient created event: {}", e.getMessage());
        }
    }


}
