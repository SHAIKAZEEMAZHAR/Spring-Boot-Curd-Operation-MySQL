package Curd.Operation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Curd.Operation.Model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	
}
