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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.TipRacuna;
import rva.reps.TipRacunaRepository;

@Api(tags = {"Tip racuna CRUD operacije"})
@RestController
public class TipRacunaRestController {
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@ApiOperation(value = "Vraća tipove raacuna iz baze podataka")
	@GetMapping("/tipRacuna")
	public Collection<TipRacuna> getTipoveRacuna() {
		return tipRacunaRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("/tipRacuna/{id}")
	public TipRacuna getTipRacuna(@PathVariable Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čiji je naziv prosleđena kao path varijabla")
	@GetMapping("/tipRacunaNaziv/{naziv}")
	public Collection<TipRacuna> findByNaziv(@PathVariable String naziv) {
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čija je oznaka prosleđena kao path varijabla")
	@GetMapping("/tipRacunaOznaka/{oznaka}")
	public Collection<TipRacuna> findByOznaka(@PathVariable String oznaka) {
		return tipRacunaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	//delete
	@ApiOperation(value = "Brise tip racuna iz baze podataka")
	@DeleteMapping("/tipRacuna/{id}")
	public ResponseEntity<HttpStatus> deleteTipRacuna(@PathVariable Integer id) {
		if (tipRacunaRepository.existsById(id)) {
			tipRacunaRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//insert
	@ApiOperation(value = "Dodaje novi tip racuna u bazu")
	@PostMapping("/tipRacuna")
	public ResponseEntity<HttpStatus> insertTipRacuna(@RequestBody TipRacuna tipRacuna) {
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@ApiOperation(value = "Azurira vec postojeci tip racuna")
	@PutMapping("/tipRacuna")
	public ResponseEntity<HttpStatus> updateTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if (tipRacunaRepository.existsById(tipRacuna.getId()))
			tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
