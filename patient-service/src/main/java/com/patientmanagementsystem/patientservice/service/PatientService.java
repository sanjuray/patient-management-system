package com.patientmanagementsystem.patientservice.service;

import com.patientmanagementsystem.patientservice.dto.PatientRequestDTO;
import com.patientmanagementsystem.patientservice.dto.PatientResponseDTO;
import com.patientmanagementsystem.patientservice.exceptions.EmailAlreadyExistsException;
import com.patientmanagementsystem.patientservice.exceptions.PatientNotFoundException;
import com.patientmanagementsystem.patientservice.grpc.BillingServiceGrpcClient;
import com.patientmanagementsystem.patientservice.kafka.KafkaProducer;
import com.patientmanagementsystem.patientservice.mapper.PatientMapper;
import com.patientmanagementsystem.patientservice.model.Patient;
import com.patientmanagementsystem.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer){
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return patientList
                .stream().map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists, "+patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getName(), patient.getEmail());

        kafkaProducer.sendEvent(patient);

        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient is not found with the given id, "+id));

//        if(!patient.getEmail().equals(patientRequestDTO.getEmail()) && patientRepository.existsByEmail(patientRequestDTO.getEmail())){
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
            throw new EmailAlreadyExistsException("A patient with this email already exists, "+patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return PatientMapper.toDTO(patientRepository.save(patient));

    }

    public void deletePatient(UUID id){
        patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient is not found with the given id, "+id));

        patientRepository.deleteById(id);
    }

}
