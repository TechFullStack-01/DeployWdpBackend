package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.ImagemProduto;
import com.wdpvendas.springJwt.entity.Produto;
import com.wdpvendas.springJwt.repository.ImagemProdutoRepository;
import com.wdpvendas.springJwt.repository.ProdutoRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Value("${custom.server-url}")
    private String serverUrl;

    @Autowired
    private ProdutoRepository produtoRepository;

    private final String uploadDir;

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;

        // Define o diretório como "uploads" na raiz do projeto
        this.uploadDir = Paths.get(System.getProperty("user.dir"), "uploads/imagens_produtos").toString();
        criarDiretorioUpload();

    }

    public Produto salvarProduto(Produto produto, MultipartFile[] imagens) throws IOException {
        produto.setDataCriacao(new Date());
        produto.setDataAtualizacao(new Date());
        List<String> caminhosLinks = new ArrayList<>();

        for (MultipartFile imagem : imagens) {
            if (!imagem.isEmpty()) {
                String linkImagem = salvarImagemComLink(imagem);
                caminhosLinks.add(linkImagem);
            }
        }

        produto.setImagensPath(caminhosLinks);
        return produtoRepository.save(produto);
    }

    private String salvarImagemComLink(MultipartFile imagem) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Salva a imagem no diretório
        Files.write(filePath, imagem.getBytes());

        // Retorna o link HTTP para acessar a imagem
        return serverUrl + "/" + fileName;
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

//    public Produto salvarProduto(Produto produto) {
//        return produtoRepository.save(produto);
//    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> buscarPorCategoria(String categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<ImagemProduto> listarImagensPorProduto(Long produtoId) {
        return imagemProdutoRepository.findAll()
                .stream()
                .filter(imagem -> imagem.getProduto().getId().equals(produtoId))
                .toList();
    }
}
