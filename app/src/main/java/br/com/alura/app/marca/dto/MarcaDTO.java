package br.com.alura.app.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarcaDTO {
    private Long id;
    private String nome;
    private String logotipo;
}
