package cgg.blogapp.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.hibernate.map/ping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.blogapp.blogapp.entities.UserDTO;
import cgg.blogapp.blogapp.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "din_scheme")
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder b1;

    public UserService userserv;

    public UserController(UserService u1) {

        this.userserv = u1;

    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {

        String encode = b1.encode(userDTO.getPassword());

        userDTO.setPassword(encode);
        UserDTO user = userserv.createUser(userDTO);

        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") int id) {

        UserDTO updateUser = userserv.updateUser(userDTO, id);

        return ResponseEntity.ok(updateUser);

    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userid") int id) {

        UserDTO gotUser = userserv.getUserById(id);

        return ResponseEntity.ok(gotUser);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAll() {

        List<UserDTO> allUsers = userserv.getAllUsers();

        return ResponseEntity.ok(allUsers);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProblemDetail> deleteUserByid(@PathVariable("id") int id) {

        userserv.deleteUser(id);

        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.OK, "usrr deleted succes")).build();

    }
}
