package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.Tamanho;
import com.wdpvendas.springJwt.repository.TamanhoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TamanhoService {

    private final TamanhoRepository tamanhoRepository;

    public TamanhoService(TamanhoRepository tamanhoRepository) {
        this.tamanhoRepository = tamanhoRepository;
    }

    public Tamanho salvar(Tamanho tamanho) {

        return tamanhoRepository.save(tamanho);
    }

    public List<Tamanho> listarTodos() {
        return tamanhoRepository.findAll();
    }

    public Tamanho buscarPorId(Long id) {
        return tamanhoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tamanho não encontrado."));
    }

    public void deletar(Long id) {
        tamanhoRepository.deleteById(id);
    }
}
