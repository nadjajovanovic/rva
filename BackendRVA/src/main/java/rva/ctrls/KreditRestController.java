package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Kredit;
import rva.reps.KreditRepository;

@RestController
public class KreditRestController {
	@Autowired
	private KreditRepository kreditRepository;
	
	@GetMapping("kredit")
	public Collection<Kredit> getKredite() {
		return kreditRepository.findAll();
	}
	
	@GetMapping("kredit/{id}")
	public Kredit getKredit(@PathVariable Integer id) {
		return kreditRepository.getOne(id);
	}
	
	@GetMapping("/kreditNaziv/{naziv}")
	public Collection<Kredit> findByNaziv(@PathVariable String naziv) {
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@GetMapping("/kreditOznaka/{oznaka}")
	public Collection<Kredit> findByOznaka(@PathVariable String oznaka) {
		return kreditRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	//delete
	@DeleteMapping("/kredit/{id}")
	public ResponseEntity<HttpStatus> deleteKredit(@PathVariable Integer id) {
		if (kreditRepository.existsById(id)) {
			kreditRepository.deleteById(id);


			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//insert
	@PostMapping("/kredit")
	public ResponseEntity<HttpStatus> insertKredit(@RequestBody Kredit kredit) {
		kreditRepository.save(kredit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@PutMapping("/kredit")
	public ResponseEntity<HttpStatus> updateKredit(@RequestBody Kredit kredit) {
		if (kreditRepository.existsById(kredit.getId()))
			kreditRepository.save(kredit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}