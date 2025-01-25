package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Marca;
import com.wdpvendas.springJwt.service.MarcaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/marca")
@CrossOrigin
public class MarcaController {

    @Autowired
    private MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Marca> buscarTodos(){
        return service.buscaTodos();
    }


    // @PostMapping
    // public ResponseEntity<Marca> criarMarca(@RequestParam String nome, @RequestParam String categoria, @RequestParam MultipartFile logo) {
    //     try {
    //         Marca marca = service.salvarMarca(nome, categoria, logo);
    //         return ResponseEntity.ok(marca);
    //     } catch (IOException e) {
    //         return ResponseEntity.internalServerError().build();
    //     }
    // }

    @PostMapping("/")
    public ResponseEntity<String> cadastrarMarca(
            @Valid @ModelAttribute Marca marca,
            @RequestParam("imagens") MultipartFile[] imagens) {
        try {
            service.salvarMarca(marca, imagens);
            return ResponseEntity.status(HttpStatus.CREATED).body("Marca cadastrado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagens.");
        }
    }

    @PutMapping("/alterar")
    public Marca alterar(@RequestBody Marca obj){
        return service.alterar(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

}

