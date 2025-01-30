package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.ImagemProduto;
import com.wdpvendas.springJwt.entity.Produto;
import com.wdpvendas.springJwt.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<String> cadastrarProduto(
            @Valid @ModelAttribute Produto produto,
            @RequestParam("imagens") MultipartFile[] imagens) {
        try {
            produtoService.salvarProduto(produto, imagens);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagens.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodosProdutos());
    }

    @GetMapping("/{categoria:.*\\D.*}") //regex para evitar ambiguidade
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable(value = "categoria") String categoria){
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/{id:\\d+}") //regex para evitar ambiguidade
    public ResponseEntity<Produto> buscarPorId(@PathVariable(value = "id") Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/imagens")
    public ResponseEntity<List<ImagemProduto>> listarImagensPorProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.listarImagensPorProduto(id));
    }
}
