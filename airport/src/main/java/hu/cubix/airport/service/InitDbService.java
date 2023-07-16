package hu.cubix.airport.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.cubix.airport.model.AirportUser;
import hu.cubix.airport.repository.AirportUserRepository;

@Service
public class InitDbService {

	AirportUserRepository userRepository;
	PasswordEncoder passwordEncoder;

	public InitDbService(AirportUserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Transactional
	public void createUsersIfNeeded() {
		
		if(!userRepository.existsById("admin")) {
			userRepository.save(new AirportUser("admin", passwordEncoder.encode("pass"), Set.of("admin", "user")));
		}
		
		if(!userRepository.existsById("user")) {
			userRepository.save(new AirportUser("user", passwordEncoder.encode("pass"), Set.of("user")));
		}
	}
}
