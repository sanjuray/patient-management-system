package com.patientmanagementsystem.patientservice.exceptions;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String message){
        super(message);
    }

}
