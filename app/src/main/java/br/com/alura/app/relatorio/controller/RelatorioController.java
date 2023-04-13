package br.com.alura.app.relatorio.controller;

import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.relatorio.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/relatorios")
public class RelatorioController {
    private RelatorioService relatorioService;
    @GetMapping
    public ResponseEntity<Page<RelatorioDto>> gerarRelatorio(@PageableDefault(size = 30) Pageable pageable) {
        Page<RelatorioDto> relatorio = relatorioService.gerarRelatorio(pageable);
        return ResponseEntity.ok(relatorio);
    }

}
