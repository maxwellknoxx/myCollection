package com.maxwell.myCollection.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.ProfileEntity;
import com.maxwell.myCollection.entity.RoleEntity;
import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.enums.RoleName;
import com.maxwell.myCollection.repository.RoleRepository;
import com.maxwell.myCollection.repository.UserRepository;
import com.maxwell.myCollection.request.LoginForm;
import com.maxwell.myCollection.request.SignUpForm;
import com.maxwell.myCollection.response.JwtResponse;
import com.maxwell.myCollection.security.jwt.JwtProvider;
import com.maxwell.myCollection.service.impl.ProfileServiceImpl;
import com.maxwell.myCollection.utils.DateUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	private final static Logger LOGGER = Logger.getLogger(AuthRestAPIs.class.getName());

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ProfileServiceImpl service;

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
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		String jwt = "";

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			jwt = jwtProvider.generateJwtToken(authentication);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong: { POST /signin } ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /signin } completed");
		}
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	/**
	 * 
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		UserEntity user = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getQuestion(), signUpRequest.getAnswer());

		Set<String> strRoles = signUpRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			default:
				RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		user = userRepository.save(user);

		ProfileEntity profile = new ProfileEntity(signUpRequest.getName(), signUpRequest.getEmail(), 0,
				DateUtils.getAtualDate(), signUpRequest.getLocation(), user);

		service.addProfile(profile);

		return ResponseEntity.ok().body("User registered successfully!");
	}

}
