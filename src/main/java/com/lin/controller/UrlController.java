package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UrlController {

	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}

}
