package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Cor;
import com.wdpvendas.springJwt.service.CorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cores")
@CrossOrigin
public class CorController {

    private final CorService corService;

    public CorController(CorService corService) {
        this.corService = corService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cor>> listarTodas() {
        return ResponseEntity.ok(corService.listarTodas());
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cor> salvar(@RequestBody Cor cor) {
        return ResponseEntity.ok(corService.salvar(cor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        corService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
