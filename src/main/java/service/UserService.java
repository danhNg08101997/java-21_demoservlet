package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

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

    public void insertUser(String fullName, String email, String password, int roleId) {
        userRepository.insertUser(fullName, email, password, roleId);
    }

    public boolean deleteUser(int id){
        return userRepository.deleteById(id);
    }

    public UserModel getUser(int id) {
        return userRepository.findById(id);
    }

    public void updateUser(UserModel userModel){
         userRepository.updateById(userModel);
    }

}
