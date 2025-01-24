package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Subcategoria;
import com.wdpvendas.springJwt.service.SubcategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
@CrossOrigin
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    public SubcategoriaController(SubcategoriaService subcategoriaService) {
        this.subcategoriaService = subcategoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Subcategoria>> listarTodas() {
        return ResponseEntity.ok(subcategoriaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Subcategoria> salvar(@RequestBody Subcategoria subcategoria) {
        return ResponseEntity.ok(subcategoriaService.salvar(subcategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(subcategoriaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        subcategoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
