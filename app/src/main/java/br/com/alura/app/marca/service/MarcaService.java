package br.com.alura.app.marca.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public void excluir(Long id) {
        marcaRepository.deleteById(id);
    }

    public Marca salvar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca atualizar(Long id, Marca marca) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isEmpty()) {
            throw new IllegalArgumentException("A marca informada não existe");
        }

        marca.setId(id);

        return marcaRepository.save(marca);
    }
}
