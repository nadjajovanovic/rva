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

import rva.jpa.Klijent;
import rva.jpa.Racun;
import rva.reps.KlijentRepository;
import rva.reps.RacunRepository;

@RestController
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@GetMapping("racun")
	public Collection<Racun> getRacune(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	public ResponseEntity<Racun> getRacun(@PathVariable Integer id){
		Racun racun = racunRepository.getOne(id);
		return new ResponseEntity<Racun>(racun, HttpStatus.OK);
	}
	
	@GetMapping("/racunZaKlijenta/{id}")
	public Collection<Racun> racunPoKlijentuId(@PathVariable int id){
		Klijent k = klijentRepository.getOne(id);
		return racunRepository.findByKlijentBean(k);
	}
	
	@GetMapping("/racunNaziv/{naziv}")
	public Collection<Racun> getNazivRacuna(@PathVariable String naziv){
		return racunRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	//delete
	@DeleteMapping ("racun/{id}")
	public ResponseEntity<Racun> deleteRacun(@PathVariable Integer id){
		if(!racunRepository.existsById(id))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
	
	//insert
	@PostMapping("racun")
	public ResponseEntity<Void> insertRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		racunRepository.save(racun);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//update
	@PutMapping("racun")
	public ResponseEntity<Void> updateRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
