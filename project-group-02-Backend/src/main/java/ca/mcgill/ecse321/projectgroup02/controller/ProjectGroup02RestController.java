package ca.mcgill.ecse321.projectgroup02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup02.dto.ApplicationUserDTO;
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.service.ProjectGroup02Service;

@CrossOrigin(origins = "*")
@RestController
public class ProjectGroup02RestController {
	
	@Autowired
	private ProjectGroup02Service service;
	
	@GetMapping(value = { "/user", "/user/" })
	public List<PersonDto> getAllPersons() {
		return service.getAllPersons().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/persons/{name}", "/persons/{name}/" })
	public PersonDto createPerson(@PathVariable("name") String name) throws IllegalArgumentException {
		Person person = service.createPerson(name);
		return convertToDto(person);
	}
	
	
	private ApplicationUserDTO convertToDto(ApplicationUser u) {
		if (u == null) {
			throw new IllegalArgumentException("There is no such Application User!");
		}
		ApplicationUserDTO userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(), u.getPhoneNumber(), null);
		userDto.setEvents(createEventDtosForPerson(u));
		return userDto;
	}
	
	
}
