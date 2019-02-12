package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final BrouwerService brouwerService;
	
	IndexController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	ModelAndView index() {
		return new ModelAndView(VIEW, "alfabet", ALFABET);
	}
}
