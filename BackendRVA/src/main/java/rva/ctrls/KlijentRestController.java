package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Klijent;
import rva.reps.KlijentRepository;

@RestController
public class KlijentRestController {
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@GetMapping("/klijent")
	public Collection<Klijent> getKlijente() {
		return klijentRepository.findAll();
	}
	
	@GetMapping("klijent/{id}")
	public Klijent getKlijenta(@PathVariable Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@GetMapping("/klijentIme/{ime}")
	public Collection<Klijent> findByIme(@PathVariable String ime) {
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@GetMapping("/klijentPrezime/{prezime}")
	public Collection<Klijent> findByPrezime(@PathVariable String prezime) {
		return klijentRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	//delete
	@Transactional
	@DeleteMapping("klijent/{id}")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable Integer id) {
		if (!klijentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//insert
	@PostMapping("klijent")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent) {
		klijentRepository.save(klijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@PutMapping("klijent")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent) {
		if (!klijentRepository.existsById(klijent.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.save(klijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
