package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();

    public List<UserModel> getAllUser () {
        return userRepository.findAll();
    }

    public List<RoleModel> getAllRoles() {
        return roleRepository.findAllRoles();
    }

    public boolean insertUser(String fullName, String email, String password, int roleId) {
        return userRepository.insertUser(fullName, email, password, roleId);
    }
}
