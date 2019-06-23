package nl.workshop2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import nl.workshop2.data.AccountRepository;
import nl.workshop2.domain.Account;
import nl.workshop2.domain.Klant;

/*
 * TODO in deze klasse: alleen accounts kunnen aanpassen en deleten van medewerkers (en admins?)
 */

@Component
@RequestMapping("/account")
public class AccountController {

	private AccountRepository accountRepo;
	
	@Autowired
	public AccountController(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
	
	/*
	 * Deze GetMapping geeft een lijst van accounts.
	 */
	@GetMapping
	private String getAccountList(@SessionAttribute Account loginAccount, Model model) {
		
		//TODO hier een if voor wanneer het sessieattribuut null is - niet ingelogd is terug naar home
		//TODO geeft nu al een foutmelding als je direct naar /account wilt surfen (missend sessieattribuut), dus if is wellicht overbodig
		/*
		 * TODO alle gets dienen deze if te ondersteunen, behalve die van het klant aanmelden proces (insert-customer,
		 * insert-customer-account en insert-customer-address).
		 */
		if (loginAccount == null) {
			return "home";
		}
		
		System.out.println(loginAccount.toString() + " JAJA");
		
		List<Account> accounts = new ArrayList<Account>();
		accountRepo.findAll().forEach(e -> accounts.add(e));
		model.addAttribute("accounts", accounts);
		
		return "account";
	}
	
	/*
	 * Het account object uit de insertAccount parameter is een ModelAttribute by default; geen noodzaak deze te annoteren.
	 */
	@GetMapping("/insert-account")
	private String insertAccount(Account account) {
		
		return "insert-account";
	}
	
	//TODO getmapping voor account specifiek voor klant (andere layout in html - vrij kaal, alleen header, footer en form)
	@GetMapping("/insert-customer-account")
	private String insertCustomerAccount(Account account, @ModelAttribute("nieuweKlant") Klant nieuweKlant) {
		
		account.setAccounttype("klant");
		account.setKlant(nieuweKlant);
		
		System.out.println(nieuweKlant);
		
		return "insert-customer-account";
	}
	
	/*
	 * Deze PostMapping voegt een nieuwe account toe (admin of medewerker).
	 */
	@PostMapping("/insert-account")
	private String insertAccountAction(@Valid Account account, BindingResult result) {
		
		if (result.hasErrors()) {
			return "account";
		}
		
		/*Account savedAccount =*/ accountRepo.save(account); 
		
		/* Eerst klant aanmaken, dan pas account
		//TODO if maken voor accounttype
		if (savedAccount.getAccounttype() == "klant") {
			return "customer/insert-customer"; //redirect? en redirectattribute? - iig een manier vinden om data van nieuwe account mee te sturen
		}
		*/
		
//		model.addAttribute("selectAddAccount", account);
		
		return "redirect:/account";
	}
	
	//TODO postmapping voor account specifiek voor klant (andere layout in html - vrij kaal, alleen header, footer en form)
	@PostMapping("/insert-customer-account")
	private String insertCustomerAccountAction(@Valid Account account, @Valid Klant klant, BindingResult result) {
		
		if (result.hasErrors()) {
			return "home";
		}
		
//		//Hoe koppel ik de klant (met name de klant_id) aan de account? Moet dit niet al in de getmapping gebeuren?
//		account.setKlant(klant);
//		account.setAccounttype("klant");
		accountRepo.save(account);
		
		return "redirect:/insert-customer-address";
	}
	
	
	//TODO hier een postmapping 
	@PostMapping("/delete-account")
	private String deleteAccountAction(@Valid Account account, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "account";
		}
		
		accountRepo.delete(account);
		
		return "redirect:/account";
	}
}
