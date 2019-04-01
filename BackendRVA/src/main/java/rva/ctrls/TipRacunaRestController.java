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

import rva.jpa.TipRacuna;
import rva.reps.TipRacunaRepository;

@RestController
public class TipRacunaRestController {
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@GetMapping("/tipRacuna")
	public Collection<TipRacuna> getTipoveRacuna() {
		return tipRacunaRepository.findAll();
	}
	
	@GetMapping("/tipRacuna/{id}")
	public TipRacuna getTipRacuna(@PathVariable Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@GetMapping("/tipRacunaNaziv/{naziv}")
	public Collection<TipRacuna> findByNaziv(@PathVariable String naziv) {
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@GetMapping("/tipRacunaOznaka/{oznaka}")
	public Collection<TipRacuna> findByOznaka(@PathVariable String oznaka) {
		return tipRacunaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	//delete
	@DeleteMapping("/tipRacuna/{id}")
	public ResponseEntity<HttpStatus> deleteTipRacuna(@PathVariable Integer id) {
		if (tipRacunaRepository.existsById(id)) {
			tipRacunaRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//insert
	@PostMapping("/tipRacuna")
	public ResponseEntity<HttpStatus> insertTipRacuna(@RequestBody TipRacuna tipRacuna) {
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@PutMapping("/tipRacuna")
	public ResponseEntity<HttpStatus> updateTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if (tipRacunaRepository.existsById(tipRacuna.getId()))
			tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
