package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import rva.jpa.Klijent;
import rva.jpa.Racun;
import rva.reps.KlijentRepository;
import rva.reps.RacunRepository;

@Api(tags = {"Racun CRUD operacije"})
@CrossOrigin
@RestController
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@ApiOperation(value = "Vraća sve racune iz baze podataka")
	@GetMapping("racun")
	public Collection<Racun> getRacune(){
		return racunRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća racun iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("racun/{id}")
	public ResponseEntity<Racun> getRacun(@PathVariable Integer id){
		Racun racun = racunRepository.getOne(id);
		return new ResponseEntity<Racun>(racun, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Vraća racun iz baze podataka čiji je id klijenta prosleđena kao path varijabla")
	@GetMapping("/racunZaKlijenta/{id}")
	public Collection<Racun> racunPoKlijentuId(@PathVariable int id){
		Klijent k = klijentRepository.getOne(id);
		return racunRepository.findByKlijentBean(k);
	}
	
	@ApiOperation(value = "Vraća racun iz baze podataka čiji je naziv prosleđena kao path varijabla")
	@GetMapping("/racunNaziv/{naziv}")
	public Collection<Racun> getNazivRacuna(@PathVariable String naziv){
		return racunRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	//delete
	@ApiOperation(value = "Brise racun iz baze podataka")
	@DeleteMapping ("racun/{id}")
	public ResponseEntity<Racun> deleteRacun(@PathVariable Integer id){
		if(!racunRepository.existsById(id))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
	
	//insert
	@ApiOperation(value = "Dodaje novi racun u bazu podataka")
	@PostMapping("racun")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		racunRepository.save(racun);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@ApiOperation(value = "Azurira vec postojeci racun")
	@PutMapping("racun")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
