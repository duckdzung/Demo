package main.java.net.fujinet.cs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class T001Controller {

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("T001");
		modelAndView.addObject("message", "Hello World từ Spring MVC!");
		return modelAndView;
	}

	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView("T002");
		return modelAndView;
	}

	@Value("${app.name}")
	private String appName;

	@Value("${app.version}")
	private String appVersion;

	@Value("${app.author}")
	private String appAuthor;

	@GetMapping("/info")
	public String showAppInfo(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("appVersion", appVersion);
		model.addAttribute("appAuthor", appAuthor);
		return "app-info";
	}

}