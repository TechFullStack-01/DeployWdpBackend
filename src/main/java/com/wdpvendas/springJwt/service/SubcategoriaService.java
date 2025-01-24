package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.Subcategoria;
import com.wdpvendas.springJwt.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;

    public SubcategoriaService(SubcategoriaRepository subcategoriaRepository) {
        this.subcategoriaRepository = subcategoriaRepository;
    }

    public Subcategoria salvar(Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }

    public List<Subcategoria> listarTodas() {
        return subcategoriaRepository.findAll();
    }

    public Subcategoria buscarPorId(Long id) {
        return subcategoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Subcategoria não encontrada."));
    }

    public void deletar(Long id) {
        subcategoriaRepository.deleteById(id);
    }
}
