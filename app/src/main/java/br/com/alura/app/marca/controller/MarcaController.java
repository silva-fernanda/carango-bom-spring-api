package br.com.alura.app.marca.controller;

import br.com.alura.app.marca.dto.MarcaDTO;
import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.service.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/marcas")
public class MarcaController {

    private MarcaService marcaService;

    @GetMapping("/listarmarcas")
    public List<Marca> listar() {
        return marcaService.listar();
    }

    @DeleteMapping("listarmarcasporid/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        marcaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrarmarca")
    public ResponseEntity<Marca> cadastrar(@RequestBody MarcaDTO marcaDTO) {
        Marca marcaSalva = marcaService.salvar(marcaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marcaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(marcaSalva);
    }

    @PutMapping("atualizarmarca/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @RequestBody Marca marca) {
        Marca marcaAtualizada = marcaService.atualizar(id, marca);
        return ResponseEntity.ok(marcaAtualizada);
    }

}

