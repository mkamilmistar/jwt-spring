/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 23/11/2021
 */

package com.jwt.userservice.configuration.security.service;

import com.jwt.userservice.model.entity.User;
import com.jwt.userservice.repository.RoleRepository;
import com.jwt.userservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;

  public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    AtomicReference<User> user = new AtomicReference<>();
    userRepository.findById(userId).ifPresentOrElse(user::set, () -> {
      throw new UsernameNotFoundException("User Not Found");
    });

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.get().getRoles().forEach(role -> {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
    });

    authorities.add(new SimpleGrantedAuthority("nbds-customer"));

    return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
  }
}
