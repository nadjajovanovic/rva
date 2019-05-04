package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.TipRacuna;

public interface TipRacunaRepository extends JpaRepository<TipRacuna, Integer>{
	Collection<TipRacuna> findByNazivContainingIgnoreCase(String naziv);
	Collection<TipRacuna> findByOznakaContainingIgnoreCase(String oznaka);
}
