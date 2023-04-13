package br.com.alura.app.veiculo.controller;

import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Veiculo> listar() {
        return veiculoService.listarVeiculosAVenda();
    }

    @DeleteMapping("excluirporid/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        veiculoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrarveiculo")
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoService.save(veiculo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(veiculoSalvo);
    }
    
    @PutMapping("alterarveiculo/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }

}

