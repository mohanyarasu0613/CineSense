package com.spring.CineSense.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.CineSense.Model.User;
import com.spring.CineSense.service.LogInService;
import com.spring.CineSense.service.MovieAPIService;
import com.spring.CineSense.service.SignUpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Basic {
	
	@Autowired
	SignUpService signupservice;
	
	@Autowired
	LogInService loginservice;
	
	@Autowired
	MovieAPIService movieapiservice;

	@GetMapping(value = "/")
	public String name() {
		return "welcome";
	}

	@GetMapping(value = "/get-started")
	public String getStarted() {
		return "auth";
	}

	@GetMapping(value = "/signup")
	public String getstarted2signup() {
		return "signup";
	}

	@PostMapping(value = "/signup")
	public String signup(@RequestParam("fullname") String fullName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("confirm_password") String confirmPassword,
			RedirectAttributes redirectAttributes) {
		String error = signupservice.signup(fullName, email, password, confirmPassword);
		if (error != null) {
			redirectAttributes.addAttribute("error", error);
			return "redirect:/signup"; // Assuming signup.jsp is at /signup
		} else {
			redirectAttributes.addAttribute("message", "Signup successful! Please login.");
			return "redirect:/login"; // Assuming login.jsp is at /login
		}
	}
	
	@GetMapping(value = "/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping(value = "/logout")
	public String GetLogout() {
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String postLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = loginservice.login(email, password);

        if (user == null) {
            session.setAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }

        // store user in session
        session.setAttribute("loggedInUser", user);

        return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String GetHome(Model model) {
	    model.addAttribute("movies", movieapiservice.searchMovies("Avengers"));
	    return "home";
	}
	
	@GetMapping(value = "/searchMovie")
	public String searchMovie(@RequestParam("movieName") String moviename, Model model) {
		model.addAttribute("movies", movieapiservice.searchMovies(moviename));
		return "home";
	}
	
	@GetMapping(value = "/account")
	public String getAccount(Model model) {
		return "account";
	}
	
	@PostMapping(value = "/updateAccount")
	public String updateAccount(@RequestParam String fullName,
	        @RequestParam String email,
	        @RequestParam String password,
	        HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");

	    user.setFullName(fullName);
	    user.setEmail(email);
	    user.setPasswordHash(password);

	    // Save back to DB via your service
	    signupservice.updateUser(user);
	    
	    session.setAttribute("success", "Account updated successfully!");

	    session.setAttribute("loggedInUser", user);
				
	    return "redirect:/account";		
	}

}
