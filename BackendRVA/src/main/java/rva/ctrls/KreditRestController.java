package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Kredit;
import rva.reps.KreditRepository;

@Api(tags = {"Kredit CRUD operacije"})
@CrossOrigin
@RestController
public class KreditRestController {
	@Autowired
	private KreditRepository kreditRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća sve kredite iz baze podataka")
	@GetMapping("kredit")
	public Collection<Kredit> getKredite() {
		return kreditRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća kredit iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("/kredit/{id}")
	public Kredit getKredit(@PathVariable Integer id) {
		return kreditRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kredit iz baze podataka čiji je naziv prosleđen kao path varijabla")
	@GetMapping("/kreditNaziv/{naziv}")
	public Collection<Kredit> findByNaziv(@PathVariable String naziv) {
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Vraća kredit iz baze podataka čija je oznaka prosleđena kao path varijabla")
	@GetMapping("/kreditOznaka/{oznaka}")
	public Collection<Kredit> findByOznaka(@PathVariable String oznaka) {
		return kreditRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	//delete
	@ApiOperation(value = "Brise kredit iz baze podataka")
	@DeleteMapping("/kredit/{id}")
	public ResponseEntity<HttpStatus> deleteKredit(@PathVariable Integer id) {
		if (kreditRepository.existsById(id)) {
			kreditRepository.deleteById(id);
			
			if (id == -100)
				jdbcTemplate.execute("INSERT INTO \"kredit\"(\"id\", \"naziv\", \"oznaka\", \"opis\")\r\n" + 
						"VALUES(-100, 'Naziv TEST', 'Oznaka TEST', 'Opis TEST');");

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//insert
	@ApiOperation(value = "Dodaje novi kredit u bazu podataka")
	@PostMapping("/kredit")
	public ResponseEntity<HttpStatus> insertKredit(@RequestBody Kredit kredit) {
		kreditRepository.save(kredit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@ApiOperation(value = "Azurira vec postojeci kredit")
	@PutMapping("/kredit")
	public ResponseEntity<HttpStatus> updateKredit(@RequestBody Kredit kredit) {
		if (kreditRepository.existsById(kredit.getId()))
			kreditRepository.save(kredit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}