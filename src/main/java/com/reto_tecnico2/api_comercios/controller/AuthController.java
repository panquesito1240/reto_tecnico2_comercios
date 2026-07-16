package com.reto_tecnico2.api_comercios.controller;

import com.reto_tecnico2.api_comercios.dto.ApiResponse;
import com.reto_tecnico2.api_comercios.dto.response.AuthResponseDTO;
import com.reto_tecnico2.api_comercios.dto.request.LoginRequestDTO;
import com.reto_tecnico2.api_comercios.dto.request.UsuarioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.response.UsuarioResponseDTO;
import com.reto_tecnico2.api_comercios.security.JwtUtils;
import com.reto_tecnico2.api_comercios.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        AuthResponseDTO responseDTO = AuthResponseDTO.builder().token(jwt).build();
        return ResponseEntity.ok(new ApiResponse<>(true, "Login exitoso", responseDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> register(@Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO nuevoUsuario = usuarioService.registrarUsuario(request);
        return new ResponseEntity<>(new ApiResponse<>(true, "Usuario registrado exitosamente", nuevoUsuario), HttpStatus.CREATED);
    }
}
