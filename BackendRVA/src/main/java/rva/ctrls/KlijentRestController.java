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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Klijent;
import rva.reps.KlijentRepository;

@Api(tags = {"Klijent CRUD operacije"})
@RestController
public class KlijentRestController {
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@ApiOperation(value = "Vraća sve klijente iz baze podataka")
	@GetMapping("/klijent")
	public Collection<Klijent> getKlijente() {
		return klijentRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća klijenta iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("klijent/{id}")
	public Klijent getKlijenta(@PathVariable Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća klijenta iz baze podataka čije je ime prosleđeno kao path varijabla")
	@GetMapping("/klijentIme/{ime}")
	public Collection<Klijent> findByIme(@PathVariable String ime) {
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@ApiOperation(value = "Vraća klijenta iz baze podataka čije je prezime prosleđena kao path varijabla")
	@GetMapping("/klijentPrezime/{prezime}")
	public Collection<Klijent> findByPrezime(@PathVariable String prezime) {
		return klijentRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	//delete
	@Transactional
	@ApiOperation(value = "Brise klijenta iz baze podataka")
	@DeleteMapping("klijent/{id}")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable Integer id) {
		if (!klijentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//insert
	@ApiOperation(value = "Dodaje novog klijenta u bazu podataka")
	@PostMapping("klijent")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent) {
		klijentRepository.save(klijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@ApiOperation(value = "Azurira vec postojeceg klijenta")
	@PutMapping("klijent")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent) {
		if (!klijentRepository.existsById(klijent.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.save(klijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
