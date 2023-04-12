package br.com.alura.app.marca.controller;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    //lista
    @GetMapping("/listarmarcas")
    public List<Marca> listar() {
        return marcaService.listar();
    }

    //exclui
    @DeleteMapping("excluirmarca/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        marcaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    //cadastra
    @PostMapping("/cadastrarmarca")
    public ResponseEntity<Marca> cadastrar(@RequestBody Marca marca) {
        Marca marcaSalva = marcaService.salvar(marca);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marcaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(marcaSalva);
    }

    //atualiza
    @PutMapping("atualizarmarca/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @RequestBody Marca marca) {
        Marca marcaAtualizada = marcaService.atualizar(id, marca);
        return ResponseEntity.ok(marcaAtualizada);
    }

}

