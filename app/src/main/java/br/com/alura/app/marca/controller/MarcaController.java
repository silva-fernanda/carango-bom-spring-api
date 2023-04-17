package br.com.alura.app.marca.controller;

import br.com.alura.app.marca.dto.MarcaDTO;
import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.service.MarcaService;
import br.com.alura.app.veiculo.entity.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/marcas")
public class MarcaController {

    private MarcaService marcaService;

    @GetMapping
    public Page<MarcaDTO> listarTodos(@PageableDefault(size = 30) Pageable pageable) {
        try {
            Page<MarcaDTO> marcas = marcaService.listarTodasAsMarcas(pageable);
            return marcas;
        } catch (Exception listarTodasAsMarcasException) {
            System.out.println("O erro ocorrido foi: " + listarTodasAsMarcasException.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, listarTodasAsMarcasException.getMessage(), listarTodasAsMarcasException);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMarca(@PathVariable Long id) {
        try {
            marcaService.excluirMarca(id);
            return ResponseEntity.noContent().build();
        } catch (Exception excluirMarcaException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID de marca não encontrada: " + id, excluirMarcaException);
        }
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody MarcaDTO marcaDTO) {
        try {
            Marca marcaSalva = marcaService.salvarMarca(marcaDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marcaSalva.getId()).toUri();
            return ResponseEntity.created(uri).body(marcaSalva);
        } catch (Exception cadastrarMarcaException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cadastrarMarcaException.getMessage(), cadastrarMarcaException);
        }
    }

    @PutMapping("/{id}")
    public MarcaDTO atualizarMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        try {
            return marcaService.atualizarMarca(id, marcaDTO);
        } catch (IllegalArgumentException atualizarMarcaExceptionArgument) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar marca: " + atualizarMarcaExceptionArgument.getMessage());
        } catch (ResponseStatusException atualizarMarcaResponseException) {
            throw atualizarMarcaResponseException;
        } catch (Exception atualizarMarcaException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar marca: " + atualizarMarcaException.getMessage());
        }
    }

}

