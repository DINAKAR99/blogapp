package cgg.blogapp.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cgg.blogapp.blogapp.entities.User;
import cgg.blogapp.blogapp.entities.UserDTO;
import cgg.blogapp.blogapp.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogapp.repos.UserRepo;
import cgg.blogapp.blogapp.services.UserService;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private ModelMapper mm;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOtoUser(userDTO);
        ///// conv userdto to user

        // process logic
        User save = userrepo.save(user);

        // process logic

        ///// again parse into userdto

        return usertoUserDTO(save);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int userid) {

        User updatedUser = userrepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userid));
        updatedUser.setName(userDTO.getName());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setPassword(userDTO.getPassword());

        userrepo.save(updatedUser);

        return usertoUserDTO(updatedUser);

    }

    @Override
    public boolean deleteUser(int userid) {
        userrepo.deleteById(userid);

        return true;

    }

    @Override
    public UserDTO getUserById(int userid) {
        User updatedUser = userrepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userid));

        return usertoUserDTO(updatedUser);

    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> all = userrepo.findAll();

        List<UserDTO> userdtos = all.stream().map(user -> usertoUserDTO(user)).collect(Collectors.toList());
        return userdtos;

    }

    public User userDTOtoUser(UserDTO u1) {

        // User u2 = new User();
        // u2.setId(u1.getId());
        // u2.setName(u1.getName());
        // u2.setEmail(u1.getEmail());
        // u2.setPassword(u1.getPassword());
        // u2.setAbout(u1.getAbout());
        return this.mm.map(u1, User.class);

    }

    public UserDTO usertoUserDTO(User u1) {

        // UserDTO u2 = new UserDTO();
        // u2.setId(u1.getId());
        // u2.setName(u1.getName());
        // u2.setEmail(u1.getEmail());
        // u2.setPassword(u1.getPassword());
        // u2.setAbout(u1.getAbout());

        // return u2;
        return this.mm.map(u1, UserDTO.class);
    }
}
