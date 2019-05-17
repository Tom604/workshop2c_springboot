package nl.workshop2.data;

import org.springframework.data.repository.CrudRepository;

import nl.workshop2.domain.Artikel;

public interface ArtikelRepository extends CrudRepository<Artikel, Long> {

}
