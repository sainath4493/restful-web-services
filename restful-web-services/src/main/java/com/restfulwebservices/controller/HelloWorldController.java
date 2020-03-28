package com.restfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World Bean - " + name);
	}

	/*
	 * @GetMapping(path = "/hello-world-internationalized") public String
	 * helloWorldInternationalized(
	 * 
	 * @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
	 * // null is for parameter return
	 * messageSource.getMessage("good.morning.message", null, locale); }
	 */
	// to simplified
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		// null is for parameter
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
