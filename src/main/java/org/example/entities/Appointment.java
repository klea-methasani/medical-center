package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.static_data.Status;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;
    @Column(name = "patient_name")
    private String patientName;
    private String note;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "begins_at")
    private LocalDateTime beginsAt;
    @Column(name = "ends_at")
    private LocalDateTime endsAt;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToOne(mappedBy = "appointment")
    private Report report;
}
