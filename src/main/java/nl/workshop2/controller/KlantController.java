package nl.workshop2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nl.workshop2.data.KlantRepository;
import nl.workshop2.domain.Klant;

@Component
@RequestMapping("/customer")
public class KlantController {

	private KlantRepository klantRepo;
	
	@Autowired
	public KlantController(KlantRepository klantRepo) {
		this.klantRepo = klantRepo;
	}
	
	// Deze GetMapping geeft een lijst van klanten.
	@GetMapping
	private String getKlantList(/*@SessionAttribute Account account,*/ Model model) {
		
		//TODO hier een if voor wanneer het sessieattribuut null is - niet ingelogd is terug naar home
		/*
		 * TODO alle gets dienen deze if te ondersteunen, behalve die van het klant aanmelden proces (insert-customer,
		 * insert-customer-account en insert-customer-address).
		 * 
		 * if (sessieattribuut == null) {
		 *	 return "home";
		 * }
		 * 
		 */
		
		List<Klant> klanten = new ArrayList<Klant>();
		klantRepo.findAll().forEach(e -> klanten.add(e));
		model.addAttribute("klanten", klanten);
		
		return "customer";
	}
	
	@GetMapping("/insert-customer")
	private String insertKlant(Klant klant) {
		
		return "insert-customer";
	}
	
	/*
	 * Accounttype en klant worden toegevoegd aan account, maar nog niet naar database weggeschreven (username en wachtwoord zijn nog nodig)
	 * Gebruik ik een addflashattribute of een addattribute hier? Werkt nu met flashattribute.
	 */
	@PostMapping("/insert-customer")
	private String insertKlantAction(@Valid Klant klant, /*Account account,*/ BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "account";
		}
		
//		account.setAccounttype("klant");
//		account.setKlant(klantRepo.save(klant));
		redirectAttributes.addFlashAttribute("nieuweKlant", klant);
		
		System.out.println(redirectAttributes.getFlashAttributes());
		
		return "redirect:/account/insert-customer-account";
	}
	
	//TODO bij het deleten van een klant ook de account deleten - in die volgorde
}
