package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Tamanho;
import com.wdpvendas.springJwt.service.TamanhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tamanhos")
@CrossOrigin
public class TamanhoController {

    private final TamanhoService tamanhoService;

    public TamanhoController(TamanhoService tamanhoService) {
        this.tamanhoService = tamanhoService;
    }

    @GetMapping
    public ResponseEntity<List<Tamanho>> listarTodos() {
        return ResponseEntity.ok(tamanhoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Tamanho> salvar(@RequestBody Tamanho tamanho) {
        return ResponseEntity.ok(tamanhoService.salvar(tamanho));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tamanho> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tamanhoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tamanhoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
