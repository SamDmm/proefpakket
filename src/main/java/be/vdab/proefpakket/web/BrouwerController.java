package be.vdab.proefpakket.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Brouwer;

@Controller
@RequestMapping("brouwers")
class BrouwerController {
	private static final String BROUWER_VIEW = "/brouwers/brouwer";
	private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
	@GetMapping("{brouwer}")
	ModelAndView brouwer(@PathVariable Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW).addObject(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
}
