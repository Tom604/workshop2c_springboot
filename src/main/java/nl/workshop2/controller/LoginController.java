package nl.workshop2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import nl.workshop2.data.AccountRepository;
import nl.workshop2.domain.Account;

@Component
@RequestMapping("/login")
/*
 * Een sessieattribuut kan ook gezet worden met (oude, geijkte methode):
 * 
 * HttpSession session = new HttpSession();
 * session.setAttribute(name, value);
 * 
 * Volgens doc bij @SessionAttributes handig bij user authentication - is permanent
 */
@SessionAttributes("loginAccount")
public class LoginController {

	/*
	 * In deze controller nog state vasthouden in sessie (statische velden?):
	 * gebruikersnaam, accounttype en wachtwoord (zoals in eerdere consoleapplicatie). - Gebeurt nu met sessionattributes.
	 */
	
	private final AccountRepository accountRepo;
	
	@Autowired
	public LoginController (AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
	
	/**
	 * Deze methode initialiseert het sessieattribuut.
	 * 
	 * @return
	 */
	@ModelAttribute("loginAccount")
	private Account loginAccount() {
		return new Account();
	}
	
	@GetMapping
	private String loginForm(SessionStatus session, Model model) { //Gebruik sessionstatus om loginaccount te discarden
		session.setComplete();
		System.out.println(model.toString());
		return "login";
	}
	
	@PostMapping
	private String loginValidation(Account account, Model model) {
		
		Account retrievedAccount = accountRepo.findByUsername(account.getUsername());
		
		System.out.println(retrievedAccount.toString());
		
		if (retrievedAccount != null && account.getWachtwoord().equals(retrievedAccount.getWachtwoord())) {
			model.addAttribute("loginAccount", retrievedAccount);
			return "main";
		} else {
			model.addAttribute("failedLogin", "Beter je best doen");
			return "redirect:/login";
		}
	}
}
