package br.com.alura.app.veiculo.controller;

import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.veiculo.dto.VeiculoDTO;
import br.com.alura.app.veiculo.dto.VeiculoListDTO;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/veiculos")
public class VeiculoController {
    private VeiculoService veiculoService;

    @GetMapping("/listarveiculos")
    public ResponseEntity<Page<VeiculoListDTO>> listar(@PageableDefault(size = 30) Pageable pageable) {
        Page<VeiculoListDTO> veiculos = veiculoService.listarVeiculosAVenda(pageable);
        return ResponseEntity.ok(veiculos);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculoSalvo = veiculoService.salvarVeiculo(veiculoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(veiculoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        VeiculoDTO veiculoDTOSalvo = veiculoService.atualizarVeiculo(id, veiculoDTO);
        return ResponseEntity.ok(veiculoDTOSalvo);
    }


}

