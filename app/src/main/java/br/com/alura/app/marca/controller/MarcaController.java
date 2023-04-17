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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        Page<MarcaDTO> marcas = marcaService.listarTodasAsMarcas(pageable);
        return marcas;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMarca(@PathVariable Long id) {
        marcaService.excluirMarca(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody MarcaDTO marcaDTO) {
        Marca marcaSalva = marcaService.salvarMarca(marcaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marcaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(marcaSalva);
    }

    @PutMapping("/{id}")
    public MarcaDTO atualizarMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        return marcaService.atualizarMarca(id, marcaDTO);
    }

}

