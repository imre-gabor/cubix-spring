package hu.cubix.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.cubix.airport.model.AirportUser;
import hu.cubix.airport.repository.AirportUserRepository;

@Service
public class AirportUserDetailsService implements UserDetailsService {
	
	@Autowired
	AirportUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AirportUser user = userRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException(username));
		
		return new User(username, user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
	}

}
