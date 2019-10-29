package application.controller;

import application.config.JwtTokenUtil;
import application.dto.JwtRequest;
import application.dto.JwtResponse;
import application.dto.UserDto;
import application.exception.GeneralException;
import application.model.Role;
import application.model.User;
import application.service.JwtUserDetailsService;
import application.service.UserService;
import application.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userService.findByUserEmail(authenticationRequest.getEmail());
        List<Role> roles = new ArrayList<>(user.getRoles());
        return ResponseEntity.ok(new JwtResponse(token, roles.get(0).getName()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto accountDto) throws EmailExistsException {
        User user = userDetailsService.registerNewUserAccount(accountDto);
        return ResponseEntity.ok(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> addAdmin(@Valid @RequestBody UserDto accountDto) throws EmailExistsException {
        User user = userDetailsService.addAdmin(accountDto);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/disableEmployee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> disableEmployee(@PathVariable Long id) {
        User user = userDetailsService.disable(id);
        if (user == null) {
            throw new GeneralException("User id " + id + " not found!");
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/enableEmployee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> enableEmployee(@PathVariable Long id) {
        User user = userDetailsService.enable(id);
        if (user == null) {
            throw new GeneralException("User id " + id + " not found!");
        }
        return ResponseEntity.ok(user);
    }
}
