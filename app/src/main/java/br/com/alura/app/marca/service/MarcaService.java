package br.com.alura.app.marca.service;

import br.com.alura.app.marca.dto.MarcaDTO;
import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MarcaService {

    private MarcaRepository marcaRepository;

    public Page<MarcaDTO> listarTodasAsMarcas(Pageable pageable) {
        try {
            Page<Marca> marcas = marcaRepository.findAll(pageable);

            if (marcas.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma marca encontrada.");
            }

            return marcas.map(marca -> {
                MarcaDTO marcaDTO = new MarcaDTO();
                marcaDTO.setId(marca.getId());
                marcaDTO.setNome(marca.getNome());
                marcaDTO.setLogotipo(marca.getLogotipo());
                return marcaDTO;
            });
        } catch (Exception listarTodasAsMarcasException) {
            System.out.println("O erro ocorrido foi: " + listarTodasAsMarcasException.getMessage());
            throw listarTodasAsMarcasException;
        }
    }



    public void excluirMarca(Long id) {
        try {
            marcaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException excluirMarca) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de marca não encontrada: " + id, excluirMarca);
        }
    }


    public Marca salvarMarca(MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setNome(marcaDTO.getNome());
        marca.setLogotipo(marcaDTO.getLogotipo());

        try {
            if (marcaRepository.findByNome(marca.getNome()).isPresent()) {
                throw new Exception("Já existe uma marca com o mesmo nome.");
            }

            if (marcaRepository.findByLogotipo(marca.getLogotipo()).isPresent()) {
                throw new Exception("Já existe uma marca com o mesmo logotipo.");
            }

            return marcaRepository.save(marca);
        } catch (Exception salvarMarcaException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, salvarMarcaException.getMessage(), salvarMarcaException);
        }
    }

    public MarcaDTO atualizarMarca(Long id, MarcaDTO marcaDTO) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isEmpty()) {
            throw new IllegalArgumentException("A marca informada não existe");
        }

        Marca marca = marcaOptional.get();
        marca.setNome(marcaDTO.getNome());
        marca.setLogotipo(marcaDTO.getLogotipo());

        try {
            if (marcaRepository.findByNome(marca.getNome()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma marca com o mesmo nome.");
            }

            if (marcaRepository.findByLogotipo(marca.getLogotipo()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma marca com o mesmo logotipo.");
            }

            marcaRepository.save(marca);

            return marcaDTO;
        } catch (Exception atualizarMarcaException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar marca: " + atualizarMarcaException.getMessage());
        }
    }


}
