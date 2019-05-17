package nl.workshop2.data;

import org.springframework.data.repository.CrudRepository;

import nl.workshop2.domain.Bestelling;

public interface BestellingRepository extends CrudRepository<Bestelling, Long> {

}
