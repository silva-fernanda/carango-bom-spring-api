package br.com.alura.app.veiculo.controller;

import br.com.alura.app.veiculo.dto.VeiculoDTO;
import br.com.alura.app.veiculo.dto.VeiculoListDTO;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/veiculos")
public class VeiculoController {
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<Page<VeiculoListDTO>> listarTodosVeiculos(@PageableDefault(size = 30) Pageable pageable) {
        try {
            Page<VeiculoListDTO> veiculos = veiculoService.listarVeiculosAVenda(pageable);
            return ResponseEntity.ok(veiculos);
        } catch (Exception listarTodosOsVeiculosException) {
            System.out.println("O erro ocorrido foi: " + listarTodosOsVeiculosException.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum veículo encontrado", listarTodosOsVeiculosException);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        try {
            veiculoService.excluirVeiculo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception excluirVeiculoException) {
            System.out.println("O erro ocorrido foi: " + excluirVeiculoException.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado com o ID: " + id, excluirVeiculoException);
        }
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        try {
            Veiculo veiculoSalvo = veiculoService.salvarVeiculo(veiculoDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(veiculoSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(veiculoSalvo);
        } catch (Exception cadastrarVeiculoException) {
            System.out.println("O erro ocorrido foi: " + cadastrarVeiculoException.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculoDTO);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (Exception atualizarVeiculoException) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("filtrarValor/{valorMin}-{valorMax}")
    public Page<Veiculo> listarFiltradoPorValor(@PageableDefault(size = 30) Pageable pageable, @PathVariable BigDecimal valorMin, @PathVariable BigDecimal valorMax) {
        return veiculoService.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }



}