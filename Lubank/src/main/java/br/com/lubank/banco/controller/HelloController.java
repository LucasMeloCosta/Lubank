package br.com.lubank.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@SuppressWarnings("static-access")
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}

}
