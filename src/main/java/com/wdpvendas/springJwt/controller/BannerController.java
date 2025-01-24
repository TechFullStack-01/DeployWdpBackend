package com.wdpvendas.springJwt.controller;

import com.wdpvendas.springJwt.entity.Banner;
import com.wdpvendas.springJwt.entity.Produto;
import com.wdpvendas.springJwt.repository.BannerRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/banners")
@CrossOrigin
public class BannerController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${server.host}")
    private String serverHost;

    private final BannerRepository bannerRepository;

    public BannerController(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Banner>> listarTodos() {
        return ResponseEntity.ok(bannerRepository.findAll());
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<List<Banner>> buscarPorCategoria(@PathVariable(value = "categoria") String categoria){
        return ResponseEntity.ok(bannerRepository.findByCategoria(categoria));
    }

    @PostMapping
    public ResponseEntity<?> createBanner(
            @RequestParam("categoria") String categoria,
            @RequestParam("imagemDesktop") MultipartFile imagemDesktop,
            @RequestParam("imagemMobile") MultipartFile imagemMobile) {
        try {
            // Verificar se os arquivos não estão vazios
            if (imagemDesktop.isEmpty() || imagemMobile.isEmpty()) {
                return ResponseEntity.badRequest().body("Os arquivos de imagem não podem estar vazios.");
            }

            // Salvar os arquivos no sistema
            String desktopUrl = saveFile(imagemDesktop);
            String mobileUrl = saveFile(imagemMobile);

            // Criar e salvar o banner
            Banner banner = new Banner();
            banner.setCategoria(categoria);
            banner.setImagemDesktop(desktopUrl);
            banner.setImagemMobile(mobileUrl);

            bannerRepository.save(banner);

            return ResponseEntity.ok(banner);

        } catch (IOException e) {
            e.printStackTrace(); // Log do erro
            return ResponseEntity.status(500).body("Erro ao salvar os arquivos: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro
            return ResponseEntity.status(500).body("Erro inesperado: " + e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        // Gerar um nome único para o arquivo
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Criar o diretório, se necessário
        Files.createDirectories(filePath.getParent());

        // Transferir o arquivo para o diretório especificado
        file.transferTo(filePath.toFile());

        // Retornar a URL pública
        return serverHost + "/uploads/" + fileName;
    }
}