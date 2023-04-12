package br.com.alura.app.relatorio.controller;

import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.relatorio.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioDto>> gerarRelatorio() {
        List<RelatorioDto> relatorio = relatorioService.gerarRelatorio();
        return ResponseEntity.ok(relatorio);
    }

}
