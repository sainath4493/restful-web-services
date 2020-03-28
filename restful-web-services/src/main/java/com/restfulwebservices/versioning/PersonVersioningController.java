package com.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfulwebservices.bean.Name;
import com.restfulwebservices.bean.PersonV1;
import com.restfulwebservices.bean.PersonV2;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Sainath Patil");
	}

	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Sainath", "Patil"));
	}

	/********************* By Using Params *******************************/

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Sainath Patil");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Sainath", "Patil"));
	}

	/********************* By Using Headers *******************************/

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Sainath Patil");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Sainath", "Patil"));
	}

	/********************* By Using Produces *******************************/

	@GetMapping(value = "/person/produces", produces = "application/demo-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Sainath Patil");
	}

	@GetMapping(value = "/person/produces", produces = "application/demo-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Sainath", "Patil"));
	}
}
