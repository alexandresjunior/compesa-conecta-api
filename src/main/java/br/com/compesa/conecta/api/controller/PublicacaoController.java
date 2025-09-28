package br.com.compesa.conecta.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compesa.conecta.api.dto.PublicacaoDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    // @Autowired do Service

    @GetMapping
    public ResponseEntity<List<PublicacaoDTO>> listar() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<PublicacaoDTO> criar(@RequestBody @Valid PublicacaoDTO dados) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
