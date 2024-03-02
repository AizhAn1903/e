package com.jtbc.exam5.service;

import com.jtbc.exam5.dto.CreateParkingDTO;
import com.jtbc.exam5.dto.CreateUserDTO;
import com.jtbc.exam5.dto.UsersDTOWithId;
import com.jtbc.exam5.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    CreateUserDTO create (CreateUserDTO userDTO);

    List<UsersDTOWithId> getAll();

    Optional<Users> getUserById(Long id);

    UsersDTOWithId getById(Long id);

    void deleteUser(Long id);
    CreateUserDTO update(Long id,CreateUserDTO createUserDTO);


}
