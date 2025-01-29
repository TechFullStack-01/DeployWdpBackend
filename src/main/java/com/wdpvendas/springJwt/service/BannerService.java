package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.Banner;
import com.wdpvendas.springJwt.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BannerService {

    private final BannerRepository bannerRepository;
    private final String UPLOAD_DIR = "uploads/baners/";

    @Value("${server.port}")
    private String serverPort;

    @Value("${custom.server-url}")
    private String serverUrl;

    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    public Banner salvarBanner(String categoria, MultipartFile imagemDesktop, MultipartFile imagemMobile) throws IOException {
        Banner banner = new Banner();
        banner.setCategoria(categoria);
        banner.setDataCriacao(new Date());
        banner.setDataAtualizacao(new Date());

        // Criar diretório se não existir
        if (!Files.exists(Paths.get(UPLOAD_DIR))) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        }

        String desktopFileName = System.currentTimeMillis() + "_desktop_" + imagemDesktop.getOriginalFilename();
        String mobileFileName = System.currentTimeMillis() + "_mobile_" + imagemMobile.getOriginalFilename();

        Path desktopPath = Paths.get(UPLOAD_DIR, desktopFileName);
        Path mobilePath = Paths.get(UPLOAD_DIR, mobileFileName);

        Files.write(desktopPath, imagemDesktop.getBytes());
        Files.write(mobilePath, imagemMobile.getBytes());

        // Gerando URL acessível para as imagens
        String baseUrl = serverUrl + "/" + UPLOAD_DIR;

        banner.setImagemDesktop(baseUrl + desktopFileName);
        banner.setImagemMobile(baseUrl + mobileFileName);

        return bannerRepository.save(banner);
    }

    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    public List<Banner> getBannersByCategoria(String categoria) {
        return bannerRepository.findByCategoriaIgnoreCase(categoria);
    }

    public boolean deleteBanner(Long id) {
        Optional<Banner> bannerOpt = bannerRepository.findById(id);
        if (bannerOpt.isPresent()) {
            Banner banner = bannerOpt.get();

            // Excluir arquivos das imagens do sistema
            try {
                Files.deleteIfExists(Paths.get(banner.getImagemDesktop()));
                Files.deleteIfExists(Paths.get(banner.getImagemMobile()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            bannerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
