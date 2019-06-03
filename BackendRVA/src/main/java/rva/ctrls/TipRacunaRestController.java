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
import rva.jpa.TipRacuna;
import rva.reps.TipRacunaRepository;

@Api(tags = {"Tip racuna CRUD operacije"})
@CrossOrigin
@RestController
public class TipRacunaRestController {
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća tipove racuna iz baze podataka")
	@GetMapping("/tip-racuna")
	public Collection<TipRacuna> getTipoveRacuna() {
		return tipRacunaRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("/tip-racuna/{id}")
	public TipRacuna getTipRacuna(@PathVariable Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čiji je naziv prosleđena kao path varijabla")
	@GetMapping("/tip-racunaNaziv/{naziv}")
	public Collection<TipRacuna> findByNaziv(@PathVariable String naziv) {
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Vraća tip racuna iz baze podataka čija je oznaka prosleđena kao path varijabla")
	@GetMapping("/tip-racunaOznaka/{oznaka}")
	public Collection<TipRacuna> findByOznaka(@PathVariable String oznaka) {
		return tipRacunaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	//delete
	@ApiOperation(value = "Brise tip racuna iz baze podataka")
	@DeleteMapping("/tip-racuna/{id}")
	public ResponseEntity<HttpStatus> deleteTipRacuna(@PathVariable Integer id) {
		if (tipRacunaRepository.existsById(id)) {
			tipRacunaRepository.deleteById(id);

			if (id == -100)
				jdbcTemplate.execute("INSERT INTO \"tip_racuna\"(\"id\", \"naziv\", \"oznaka\", \"opis\")\r\n" + 
						"VALUES(-100, 'Naziv TEST', 'Oznaka TEST', 'Opis TEST');");
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//insert
	@ApiOperation(value = "Dodaje novi tip racuna u bazu")
	@PostMapping("/tip-racuna")
	public ResponseEntity<HttpStatus> insertTipRacuna(@RequestBody TipRacuna tipRacuna) {
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@ApiOperation(value = "Azurira vec postojeci tip racuna")
	@PutMapping("/tip-racuna")
	public ResponseEntity<HttpStatus> updateTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if (tipRacunaRepository.existsById(tipRacuna.getId()))
			tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
