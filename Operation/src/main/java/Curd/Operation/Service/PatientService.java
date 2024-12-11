package Curd.Operation.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Curd.Operation.Model.Patient;
import Curd.Operation.Repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
	}
	
	public Optional<Patient> getPatientByID(Long id) {
		return patientRepository.findById(id);
	}
	
	public Patient updatePatient(Long id, Patient updatedPatient) {
		 return patientRepository.findById(id).map(patient -> {
	            patient.setName(updatedPatient.getName());  // Update the patient's name
	            patient.setAge(updatedPatient.getAge());    // Update the patient's age
	            patient.setAddress(updatedPatient.getAddress());  // Update address
	            patient.setDiagnosis(updatedPatient.getDiagnosis());  // Update diagnosis
	            return patientRepository.save(patient);  // Save the updated patient back to the repository
	        }).orElseThrow(() -> new RuntimeException("Patient not found!"));  // Throw exception if patient doesn't exist
    }
	
	public void deletePatient(long id) {
		patientRepository.deleteById(id);
	}
	
	
	
	
}
