package com.acc.SpringDataJPA.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acc.SpringDataJPA.config.JwtTokenUtil;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/token")
public class AuthenticationController {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody HashMap<String, String> map) throws AuthenticationException {
       // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        
 
        String username ="";
        for (String key : map.keySet()) {

			if (key.equals("username")) {
			username =map.get(key);
			}
        }
        System.out.println("---------------------"+username);
        final String token = jwtTokenUtil.generateToken(username);
        return   new ResponseEntity<>(token, HttpStatus.OK);
    
        }


}
