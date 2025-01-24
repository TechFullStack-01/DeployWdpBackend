package com.wdpvendas.springJwt.service;

import com.wdpvendas.springJwt.entity.Endereco;
import com.wdpvendas.springJwt.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Long usuarioId) {
        return enderecoRepository.findByUsuarioId(usuarioId);
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}
