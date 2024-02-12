package cgg.blogapp.blogapp.services;

import java.util.List;

import cgg.blogapp.blogapp.entities.UserDTO;

public interface UserService {
    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(UserDTO userDTO, int userid);

    public boolean deleteUser(int userid);

    public UserDTO getUserById(int userid);

    public List<UserDTO> getAllUsers();

}
