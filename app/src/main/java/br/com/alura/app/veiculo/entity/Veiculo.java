package br.com.alura.app.veiculo.entity;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.veiculo.enums.StatusVeiculoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quilometragem;
    @NotNull
    @Positive(message = "Ano deve ser um número inteiro positivo.")
    @Digits(integer = 4, fraction = 0, message = "Ano deve ter exatamente 4 dígitos.")
    private int ano;
    @NotNull
    private BigDecimal valor;
    private String tipoCombustivel;
    @NotNull
    @Size(min = 2, message = "Modelo deve ter no mínimo 2 caracteres.")
    private String modelo;
    private String cor;
    private String descricao;
    private String cambio;
    @Enumerated(EnumType.STRING)
    private StatusVeiculoEnum status = StatusVeiculoEnum.DISPONIVEL;
    @ManyToOne
    private Marca marca;

}