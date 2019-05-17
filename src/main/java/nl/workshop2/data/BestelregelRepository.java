package nl.workshop2.data;

import org.springframework.data.repository.CrudRepository;

import nl.workshop2.domain.Bestelregel;

public interface BestelregelRepository extends CrudRepository<Bestelregel, Long> {

}
