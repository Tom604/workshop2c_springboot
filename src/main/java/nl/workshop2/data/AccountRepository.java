package nl.workshop2.data;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workshop2.domain.Account;

//JpaRepository implementeren? Volgens Henk handiger.
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByUsername(String username);
}
