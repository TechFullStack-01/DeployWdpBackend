package com.wdpvendas.springJwt.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/imagens")
@CrossOrigin
public class ImagemController {

    private final String uploadDir;

    public ImagemController() {
        this.uploadDir = Paths.get(System.getProperty("user.dir"), "uploads").toString();
    }

    @GetMapping("/{nomeImagem}")
    public ResponseEntity<Resource> obterImagem(@PathVariable String nomeImagem) {
        try {
            Path caminhoImagem = Paths.get(uploadDir).resolve(nomeImagem);
            Resource recurso = new UrlResource(caminhoImagem.toUri());

            if (recurso.exists() || recurso.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                        .body(recurso);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
