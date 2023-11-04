package com.iamkhs.fooddelivery.configuration;

import com.iamkhs.fooddelivery.entity.User;
import com.iamkhs.fooddelivery.exceptions.UserNotFoundException;
import com.iamkhs.fooddelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).
                orElseThrow(() -> new UserNotFoundException("User with this email : " + username + " not Found"));

        return new UserDetailsImpl(user);
    }
}
