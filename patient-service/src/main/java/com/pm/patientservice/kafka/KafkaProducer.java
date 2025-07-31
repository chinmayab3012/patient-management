package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String EVENT_TYPE_PATIENT_CREATED = "PATIENT_CREATED";
    private static final String EVENT_TYPE_PATIENT_UPDATED = "PATIENT_UPDATED";
    public static final String BILLING_ACCOUNT_CREATE_REQUESTED = "BILLING_ACCOUNT_CREATE_REQUESTED";
    /**
     * A KafkaTemplate instance used for sending messages to a Kafka topic.
     * It is parameterized with a String key and byte[] value type.
     * The template provides methods to send data to Kafka, handling serialization and communication details.
     * This instance is final, ensuring it is immutable after initialization.
     */
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    @Value("${kafka.topic.patient.create:patient.created}")
    String patientEventsTopic;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private PatientEvent buildPatientEvent(Patient patient, String eventType) {
        return PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType(eventType)
                .build();
    }


    public void sendPatientCreatedEvent
            (Patient patient) {
        try {
            PatientEvent patientEvent = buildPatientEvent(patient, EVENT_TYPE_PATIENT_CREATED);

            kafkaTemplate.send(patientEventsTopic, patientEvent.toByteArray());
        } catch (KafkaException ke) {
            log.error("Kafka error while sending patient created event for patient ID: {}",
                    patient.getId(), ke);
        } catch (Exception e) {
            log.error("Unexpected error while sending patient created event for patient ID: {}",
                    patient.getId(), e);
        }
    }

    public void sendPatientUpdatedEvent
            (Patient patient) {
        try {
            PatientEvent patientEvent = buildPatientEvent(patient, EVENT_TYPE_PATIENT_UPDATED);

            kafkaTemplate.send("patient.updated", patientEvent.toByteArray());
        } catch (KafkaException ke) {
            log.error("Kafka error while sending patient created event for patient ID: {}",
                    patient.getId(), ke);
        } catch (Exception e) {
            log.error("Unexpected error while sending patient created event for patient ID: {}",
                    patient.getId(), e);
        }
    }

    public void sendBillingAccountEvent(String patientId,String name,String email){
        billing.events.BillingAccountEvent billingAccountEvent =
                billing.events.BillingAccountEvent.newBuilder()
                        .setPatientId(patientId)
                        .setEmail(email)
                        .setName(name)
                        .setEventType(BILLING_ACCOUNT_CREATE_REQUESTED)
                        .build();

        try{
            kafkaTemplate.send("billing-account",billingAccountEvent.toByteArray());
        }catch (KafkaException ke) {
            log.error("Kafka error while sending billing account event for patient ID: {}",
                    patientId, ke);
        } catch (Exception e) {
            log.error("Unexpected error while sending billing account event for patient ID: {}",
                   patientId, e);
        }
    }
}
