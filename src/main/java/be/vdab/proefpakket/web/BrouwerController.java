package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("brouwers")
class BrouwerController {
	private static final String BROUWER_VIEW = "/brouwers/brouwer";
	private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
	private static final String ONDERNEMINGS_NR_VIEW = "brouwers/ondernemingsnr";
	private static final String REDIRECT_NA_ONDERNEMINGSNR  = "redirect:/brouwers/{id}";
	private final BrouwerService brouwerService;
	
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@GetMapping("{brouwer}")
	ModelAndView brouwer(@PathVariable Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW).addObject(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	@GetMapping("{brouwer}/ondernemingsnr")
	ModelAndView ondernemingsNr(@PathVariable Optional<Brouwer>brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			OndernemingsNrForm form = new OndernemingsNrForm();
			form.setOndernemingsNr(brouwer.get().getOndernemingsNr());
			return new ModelAndView(ONDERNEMINGS_NR_VIEW).addObject(brouwer.get()).addObject(form);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	@PostMapping("{brouwer}/ondernemingsnr")
	ModelAndView ondernemingsNr(@PathVariable Optional<Brouwer> brouwer, @Valid OndernemingsNrForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(ONDERNEMINGS_NR_VIEW).addObject(brouwer.get());
			}
			brouwer.get().setOndernemingsNr(form.getOndernemingsNr());
			brouwerService.update(brouwer.get());
			redirectAttributes.addAttribute("id", brouwer.get().getId());
			return new ModelAndView(REDIRECT_NA_ONDERNEMINGSNR);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
}
