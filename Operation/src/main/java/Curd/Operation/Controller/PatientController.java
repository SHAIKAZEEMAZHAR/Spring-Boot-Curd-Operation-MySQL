package Curd.Operation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Curd.Operation.Model.Patient;
import Curd.Operation.Service.PatientService;

@RestController
@RequestMapping("api/patients")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	@PostMapping
	public Patient addPatient(@RequestBody Patient patient) {
		return service.addPatient(patient);
	}
	
	@GetMapping
	public List<Patient> getAllPatient() {
		return service.getAllPatient();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientByID(@PathVariable long id) {
		return service.getPatientByID(id)
						.map(ResponseEntity::ok)
						.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient upPatient) {
		
		try {
			
			return ResponseEntity.ok(service.updatePatient(id, upPatient));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable Long id) {
		if(service.getPatientByID(id).isPresent()) {
			service.deletePatient(id);
			return ResponseEntity.ok("Patient With Id " + id + " Has Been Successfully Deleted");
		}else {
			return ResponseEntity.status(404).body("Patient With Id"  + id + " Not Found");
		}
	}
	
}
