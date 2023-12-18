package com.integrador.controllers;

import com.integrador.models.UserModel;
import com.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> getUsuarios() {
        List<UserModel> usuarios = userRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UserModel> getUsuario(@PathVariable Long id) {
        Optional<UserModel> usuario = userRepository.findById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUsuario(@RequestBody UserModel userModel) {
        if (userModel.getCodigo() == null || userModel.getUsuario() == null || userModel.getEmail() == null
                || userModel.getPassword() == null || userModel.getDepartment_id() == null) {
            return new ResponseEntity<>("Bad Request. Please fill all fields.", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(userModel);
        return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        Optional<UserModel> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            UserModel userToUpdate = existingUser.get();
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPassword(updatedUser.getPassword());
            userRepository.save(userToUpdate);
            return new ResponseEntity<>("El usuario se actualizó correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> delUsuario(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

