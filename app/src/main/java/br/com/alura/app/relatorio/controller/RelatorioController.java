package br.com.alura.app.relatorio.controller;

import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.relatorio.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/relatorios")
public class RelatorioController {
    private RelatorioService relatorioService;
    @GetMapping
    public ResponseEntity<Page<RelatorioDto>> gerarRelatorio(@PageableDefault(size = 30) Pageable pageable) {
        try {
            Page<RelatorioDto> relatorio = relatorioService.gerarRelatorio(pageable);
            return ResponseEntity.ok(relatorio);
        } catch (Exception gerarRelatorioException) {
            System.out.println("O erro ocorrido foi: " + gerarRelatorioException.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
