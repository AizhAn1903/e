package com.jtbc.exam5.controller;

import com.jtbc.exam5.dto.*;
import com.jtbc.exam5.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;
    @PostMapping("/create")
    public ResponseEntity<CreateUserDTO> createParking(@RequestBody CreateUserDTO userDTO) {
        try {
            CreateUserDTO createUser = usersService.create(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getBy/{id}")
    public ResponseEntity<UsersDTOWithId> getById(@PathVariable Long id) {
        try {
            UsersDTOWithId usersDTO = usersService.getById(id);
            if (usersDTO != null)
                return new ResponseEntity<>(usersDTO, HttpStatus.OK);
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UsersDTOWithId>> getAllManufacturers() {
        try {
            List<UsersDTOWithId> usersDTO= usersService.getAll();
            return new ResponseEntity<>(usersDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CreateUserDTO> update(@PathVariable Long id, @RequestBody CreateUserDTO userDTO) {
        try {
            CreateUserDTO user = usersService.update(id, userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usersService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


