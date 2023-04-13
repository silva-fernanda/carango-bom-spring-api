package br.com.alura.app.veiculo.dto;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.veiculo.enums.StatusVeiculoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VeiculoDTO {
    private int quilometragem;
    private int ano;
    private BigDecimal valor;
    private String fotoDoCarro;
    private String tipoCombustivel;
    private String modelo;
    private String cor;
    private String descricao;
    private String cambio;
    private StatusVeiculoEnum status;
    private Marca marca;

}
