package org.proyecto.primerproyecto.controllers;


import org.proyecto.primerproyecto.models.Usuario;
import org.proyecto.primerproyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
@GetMapping
    public ResponseEntity <List<Usuario>> findAllUsuarios() {
        return ResponseEntity.ok(this.usuarioService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> usuario = this.usuarioService.getUserById(id);

        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public void createUser(@RequestBody Usuario usuario) {
    this.usuarioService.createUser(usuario);
    }


    @GetMapping("/delete/{id}")
    public  void deleteUser(@PathVariable Long id) {
    this.usuarioService.delete(id);

    }

    @GetMapping ("/status")
    public Map<String, String> checkCtatus(){
    Map<String, String> response = new HashMap<>();
    response.put("message", "conexion establecida");
        return response;
    }
    @PostMapping("/login")
    public  ResponseEntity<Map<String, Object>> login(@RequestBody  Map <String, String> credenciales) {
    
    String username = credenciales.get("username");
    String password = credenciales.get("password");
    
    boolean isAuth = this.usuarioService.authenticate(username, password);
    Map<String, Object> respuesta = new HashMap<>();
    
    if (isAuth) {
        respuesta.put("message", "Login exitoso");
        respuesta.put("login", true);
        return ResponseEntity.ok(respuesta);
    } 
    else {
        respuesta.put("message", "Datos incorrecto");
        respuesta.put("login", false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }
    }

    @PostMapping("/login/v2")
    public  ResponseEntity<Map<String, Object>> loginv2(@RequestBody  Map <String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");

        String result = this.usuarioService.authWithPassword(username, password);
        Map<String, Object> respuesta = new HashMap<>();
        if (result.equals("Usuario existe")) {
            respuesta.put("message", result);
            respuesta.put("login", true);
            return ResponseEntity.ok(respuesta);
        } else if (result.equals(" Contraseña Incorrecta ")) {
            respuesta.put("message", result);
            respuesta.put("login", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }else {
            respuesta.put("message", result);
            respuesta.put("login", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);

        }
    }

    }
