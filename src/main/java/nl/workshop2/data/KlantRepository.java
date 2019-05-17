package nl.workshop2.data;

import org.springframework.data.repository.CrudRepository;

import nl.workshop2.domain.Klant;

public interface KlantRepository extends CrudRepository<Klant, Long> {

}
