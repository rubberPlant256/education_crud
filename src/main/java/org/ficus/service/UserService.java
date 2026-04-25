package org.ficus.service;

import org.ficus.auth.exceptions.data.ExistingUserWithThatUsernameException;
import org.ficus.auth.exceptions.data.UserNotFoundException;
import org.ficus.data.entity.Role;
import org.ficus.data.entity.Users;
import org.ficus.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ParentService parentService;

    private Users save(Users user) {
        return userRepository.save(user);
    }

    public Users createUser(String login, String password, Role role) {

        if (userRepository.findUserByLogin(login) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if (role.equals(Role.PARENT)){
            parentService.createEmptyParent(user);
        }

        return save(user);
    }

    public Users findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public Users findUserByLogin(String login) {
        Users findUser = userRepository.findUserByLogin(login);
        if (findUser == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findUserByLogin(login);
    }
}
