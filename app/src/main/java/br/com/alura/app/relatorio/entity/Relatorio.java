package br.com.alura.app.relatorio.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "relatorios")
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private int quantidade;

    private BigDecimal valorTotal;
}
