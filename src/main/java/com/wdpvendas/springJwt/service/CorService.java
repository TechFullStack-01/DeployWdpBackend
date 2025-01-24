package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.Cor;
import com.wdpvendas.springJwt.repository.CorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorService {

    private final CorRepository corRepository;

    public CorService(CorRepository corRepository) {
        this.corRepository = corRepository;
    }

    public Cor salvar(Cor cor) {
        return corRepository.save(cor);
    }

    public List<Cor> listarTodas() {
        return corRepository.findAll();
    }

    public Cor buscarPorId(Long id) {
        return corRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cor não encontrada."));
    }

    public void deletar(Long id) {
        corRepository.deleteById(id);
    }
}
