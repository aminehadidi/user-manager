package fr.app.usermanager.controller;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.app.usermanager.dto.UserDto;
import fr.app.usermanager.entity.User;
import fr.app.usermanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
/**
 * UserController
 *
 * @author Amine HADIDI
 */
@RestController
@RequestMapping("/user-management")
@Tag(name = "UserController", description = "UserController")
@Log4j2
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@Operation(summary = "Rechercher l'utilisateur ayant l'adresse mail {email} spécifiée.")
	@GetMapping(path = "users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable @NotNull @Email String email) {
		log.info("Email est  : {}", email);
		User user = userService.findByEmail(email);
		UserDto userDto = null;
		if (user != null) {
			userDto = modelMapper.map(user, UserDto.class);
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Operation(summary = "Rechercher la liste d'utilisateurs ")
	@GetMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getListUsers() {
		List<User> users = userService.getAll();
		List<UserDto> result = null;
		if (users != null && !users.isEmpty()) {
			Type listType = new TypeToken<List<UserDto>>() {
			}.getType();
			result = modelMapper.map(users, listType);

		} else {
			log.error("Aucun utilisateur trouvé");
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Insérer un nouvel utilisateur ")
	@PostMapping(path = "users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody @NotNull @Valid UserDto user) {
		user.setId(null);
		try {
			userService.save(modelMapper.map(user, User.class));
		} catch (Exception e) {
			return new ResponseEntity<>("KO", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("OK", HttpStatus.CREATED);
	}

	@Operation(summary = "Mettre à jour l'utilisateur ayant l'id {id} spécifié")
	@PutMapping(path = "users/{id}")
	public ResponseEntity<String> updateUser(@PathVariable @Min(value = 1) BigInteger id,
			@RequestBody @NotNull @Valid UserDto user) {
		log.info("mise à jour de l'utilisateur ayant l'id   : {}", id);
		try {
			userService.updateUser(id, user.getFirstName(), user.getLastName());
		} catch (Exception e) {
			return new ResponseEntity<>("KO", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@Operation(summary = "Supprimer un utilisateur en fonction de son identifiant")
	@DeleteMapping(path = "users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable @Min(value = 1) BigInteger id) {
		log.info("Suppression de l'utilisateur ayant l'id   : {}", id);
		try {
			userService.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("KO", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
