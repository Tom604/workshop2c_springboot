package nl.workshop2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("account")
public class LoginController {

	/*
	 * In deze controller nog state vasthouden in sessie (statische velden?):
	 * gebruikersnaam, accounttype en wachtwoord (zoals in eerdere consoleapplicatie).
	 */
	
	private final AccountRepository accountRepo;
	
	@Autowired
	public LoginController (AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
	
//	@ModelAttribute("account")
//	private Account account() {
//		return new Account();
//	}
	
	//Onderstaande modelattribute parameter is hetzelfde als bovenstaande methode - geen instantiëring via de methode nodig dus
	@GetMapping
	private String loginForm(@ModelAttribute("account") Account account) {
		return "login";
	}
	
	@PostMapping
	private String loginValidation(Account account, Model model) {
		
		Account retrievedAccount = accountRepo.findByUsername(account.getUsername());
		
		if (retrievedAccount != null && account.getWachtwoord().equals(retrievedAccount.getWachtwoord())) {
			return "main";
		} else {
			model.addAttribute("failedLogin", "Beter je best doen");
			return "redirect:/login";
		}
	}
}
