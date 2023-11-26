package co.edu.cue.hotelvillegas.services.impl;

import co.edu.cue.hotelvillegas.constraints.UserConstraint;
import co.edu.cue.hotelvillegas.domain.entities.User;
import co.edu.cue.hotelvillegas.domain.enums.Role;
import co.edu.cue.hotelvillegas.mapping.dto.UserDto;
import co.edu.cue.hotelvillegas.mapping.mappers.UserMapper;
import co.edu.cue.hotelvillegas.repositories.UserRepository;
import co.edu.cue.hotelvillegas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConstraint userConstraint;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserConstraint userConstraint) {
        this.repository = repository;
        this.userConstraint = userConstraint;
    }


    @Override
    public List<UserDto> list() {
        return UserMapper.mapFrom((List<User>) repository.findAll());
    }

    @Override
    public UserDto getUserById(int id) {
        return UserMapper.mapFrom(
                repository.findById(id).orElseThrow());
    }

    @Override
    public UserDto save(UserDto user) {
        userConstraint.isUserNameAvailable(user.userName());

        User userToSave = UserMapper.mapFrom(user);
        userToSave.setPassword(passwordEncoder.encode(user.password()));
        userToSave.setRole(list().size() > 0 ? Role.CLIENT : Role.ADMIN);

         return UserMapper.mapFrom(repository.save(userToSave));
    }

    @Override
    public UserDto update(UserDto user) {
        userConstraint.isUserNameAvailable(user.userName());
        return UserMapper.mapFrom(
                repository.save(UserMapper.mapFrom(user)));
    }

    @Override
    public Optional<UserDto> getUserByUserName(String userName) {
        return repository.findByUserName(userName).map(UserMapper::mapFrom);
    }
}
