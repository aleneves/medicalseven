package com.med.seven.api.controller;

import com.med.seven.api.domain.usuario.Usuario;
import com.med.seven.api.infra.security.DadosTokenJwt;
import com.med.seven.api.infra.security.TokenService;
import com.med.seven.api.usuario.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao entrada) {
        var authToken = new UsernamePasswordAuthenticationToken(entrada.login(), entrada.senha());
        var authResult = authenticationManager.authenticate(authToken);
        var tokenJwt = tokenService.gerarToken((Usuario) authResult.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }
}
