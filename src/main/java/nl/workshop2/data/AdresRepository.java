package nl.workshop2.data;

import org.springframework.data.repository.CrudRepository;

import nl.workshop2.domain.Adres;

public interface AdresRepository extends CrudRepository<Adres, Long> {

}
