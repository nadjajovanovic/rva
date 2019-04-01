package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Klijent;
import rva.jpa.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer>{
	Collection<Racun> findByKlijentBean(Klijent k);
	Collection<Racun> findByNazivContainingIgnoreCase(String naziv);
}
