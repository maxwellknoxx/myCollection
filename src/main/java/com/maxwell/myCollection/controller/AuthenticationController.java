package com.maxwell.myCollection.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.Role;
import com.maxwell.myCollection.entity.User;
import com.maxwell.myCollection.enums.RoleName;
import com.maxwell.myCollection.model.UserModelDTO;
import com.maxwell.myCollection.repository.RoleRepository;
import com.maxwell.myCollection.repository.UserRepository;
import com.maxwell.myCollection.request.LoginForm;
import com.maxwell.myCollection.request.SignUpForm;
import com.maxwell.myCollection.response.JwtResponse;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.security.jwt.JwtProvider;
import com.maxwell.myCollection.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserServiceImpl service;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	/**
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping(path = "/api/v1/auth/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		Response<UserModelDTO> response = new Response<UserModelDTO>();
		String jwt = "";

		UserModelDTO user;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		jwt = jwtProvider.generateJwtToken(authentication);

		user = service.findByUsername(loginRequest.getUsername());
		if (Objects.isNull(user)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		response.setJwt(new JwtResponse(jwt));
		response.setData(user);
		response.setMessage(user.getRole());

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/api/v1/auth/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.OK);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.OK);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), signUpRequest.getQuestion(), signUpRequest.getAnswer(),
				signUpRequest.getLocation(),signUpRequest.getMemberSince(), 0L);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		user = userRepository.save(user);

		return ResponseEntity.ok().body("Sucess");
	}

}
