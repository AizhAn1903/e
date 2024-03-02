package com.jtbc.exam5.service.impl;

import com.jtbc.exam5.dto.*;
import com.jtbc.exam5.entity.Users;
import com.jtbc.exam5.repo.UserRepo;
import com.jtbc.exam5.service.ParkingService;
import com.jtbc.exam5.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UserRepo userRepo;
    private final ParkingService parkingService;
    @Override
    public CreateUserDTO create(CreateUserDTO userDTO) {
        Users user = Users.builder()
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .build();
        try {
            userRepo.save(user);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return userDTO;
    }

    @Override
    public List<UsersDTOWithId> getAll() {
        List<Users> users = userRepo.findAll();
        List<UsersDTOWithId> usersDTOS = new ArrayList<>();
        for (Users users1 : users) {
            UsersDTOWithId usersDTO = UsersDTOWithId.builder()
                    .id(users1.getId())
                    .fullName(users1.getFullName())
                    .email(users1.getEmail())
                    .build();
            usersDTOS.add(usersDTO);
        }
        return usersDTOS;
    }


    @Override
    public UsersDTOWithId getById(Long id) {
        Users user = userRepo.findById(id).get();
        UsersDTOWithId usersDTO = UsersDTOWithId.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();

        return usersDTO;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<Users> usersOptional = userRepo.findById(id);
        if (usersOptional.isPresent()) {
            userRepo.delete(usersOptional.get());
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public CreateUserDTO update(Long id, CreateUserDTO createUserDTO) {
        Users users = userRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + id));
        users.setFullName(createUserDTO.getFullName());
        users.setEmail(createUserDTO.getEmail());


        users = userRepo.save(users);

        CreateUserDTO updateUser = CreateUserDTO.builder()
                .fullName(users.getFullName())
                .email(users.getEmail())
                .build();

        return updateUser;
    }
    @Override
public Optional<Users> getUserById(Long id){
        return userRepo.findById(id);
}
    }



