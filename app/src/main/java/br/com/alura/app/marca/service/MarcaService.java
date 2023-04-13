package br.com.alura.app.marca.service;

import br.com.alura.app.marca.dto.MarcaDTO;
import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MarcaService {

    private MarcaRepository marcaRepository;

    public Page<MarcaDTO> listarTodasAsMarcas(Pageable pageable) {
         Page<Marca> marcas = marcaRepository.findAll(pageable);

         return marcas.map(marca -> {
            MarcaDTO marcaDTO = new MarcaDTO();
            marcaDTO.setNome(marca.getNome());
            marcaDTO.setLogotipo(marca.getLogotipo());
            return marcaDTO;
         });
    }

    public void excluir(Long id) {
        marcaRepository.deleteById(id);
    }

    public Marca salvar(MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setNome(marcaDTO.getNome());
        marca.setLogotipo(marcaDTO.getLogotipo());
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
