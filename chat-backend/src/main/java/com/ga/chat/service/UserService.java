package com.ga.chat.service;

import com.ga.chat.exception.InformationExistsException;
import com.ga.chat.model.User;
import com.ga.chat.model.request.LoginRequest;
import com.ga.chat.model.response.LoginResponse;
import com.ga.chat.repository.UserRepository;
import com.ga.chat.security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public User createUser(User user){
       if(!userRepository.existsByUserName(user.getUserName())) {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
       } else {
           throw new InformationExistsException("User with user name " + user.getUserName() 
           + " already exists");
       }
   }

   public ResponseEntity<?> loginUser(LoginRequest loginRequest){
       authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
    final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
    final String JWT = jwtUtils.generateToken(userDetails);
    return ResponseEntity.ok(new LoginResponse(JWT));
   }

   public User findByUserName(String userName){
       return userRepository.findByUserName(userName);
   }

}
