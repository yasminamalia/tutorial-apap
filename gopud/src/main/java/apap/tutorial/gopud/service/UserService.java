package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    public boolean isMatch(String password, String checkedPassword);
    UserModel changePassword(UserModel user);
    UserModel findByUsername(String username);
}
