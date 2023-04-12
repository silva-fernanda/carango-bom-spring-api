package br.com.alura.app.relatorio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioDto {
    private String marca;
    private int quantidade;
    private BigDecimal valorTotal;
}
