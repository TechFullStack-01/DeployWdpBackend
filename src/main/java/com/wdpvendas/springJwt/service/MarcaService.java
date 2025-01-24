package com.wdpvendas.springJwt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wdpvendas.springJwt.entity.Marca;
import com.wdpvendas.springJwt.repository.ImagemProdutoRepository;
import com.wdpvendas.springJwt.repository.MarcaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MarcaService {

    @Value("${custom.server-url}")
    private String serverUrl;

    @Autowired
    private MarcaRepository repo;
    
    private final String uploadDir;

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    public MarcaService(MarcaRepository repo) {
        this.repo = repo;
        // Define o diretório como "uploads" na raiz do projeto
        this.uploadDir = Paths.get(System.getProperty("user.dir"), "uploads").toString();
        criarDiretorioUpload();

    }

    public Marca salvarMarca(Marca marca, MultipartFile[] imagens) throws IOException {
        List<String> caminhosLinks = new ArrayList<>();

        for (MultipartFile imagem : imagens) {
            if (!imagem.isEmpty()) {
                String linkImagem = salvarImagemComLink(imagem);
                caminhosLinks.add(linkImagem);
            }
        }

        marca.setImagensPath(caminhosLinks);
        return repo.save(marca);
    }

    private String salvarImagemComLink(MultipartFile imagem) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Salva a imagem no diretório
        Files.write(filePath, imagem.getBytes());

        // Retorna o link HTTP para acessar a imagem
        return serverUrl + "/imagens/" + fileName;
    }

    

    // public Marca inserir(String nome, String categoria, MultipartFile logo) throws IOException {
    //     String logoFilename = logo.getOriginalFilename();
    //     Path logoPath = logoDirectory.resolve(logoFilename);

    //     Files.copy(logo.getInputStream(), logoPath);

    //     Marca marca = new Marca();
    //     marca.setNome(nome);
    //     marca.setCategoria(categoria);
    //     marca.setLogoPath(logoPath.toString());

    //     return repo.save(marca);
    // }

    public List<Marca> buscaTodos() {
        return repo.findAll();
    }

    public Marca alterar(Marca obj) {
        obj.setDataAtualizacao(new Date());
        return repo.saveAndFlush(obj);

    }

    public void excluir(Long id) {
        Marca obj = repo.findById(id).get();
        repo.delete(obj);
    }

    private void criarDiretorioUpload() {
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar o diretório de upload", e);
            }
        }
    }

}
