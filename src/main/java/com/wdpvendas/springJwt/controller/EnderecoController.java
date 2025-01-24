package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Endereco;
import com.wdpvendas.springJwt.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
@CrossOrigin
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.salvar(endereco));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Endereco>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(enderecoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
