package br.com.alura.app.veiculo.entity;

import br.com.alura.app.marca.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Veiculo {
    private String modelo;
    private int ano;
    private double valor;
    @ManyToOne
    private Marca marca;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {

    }
}