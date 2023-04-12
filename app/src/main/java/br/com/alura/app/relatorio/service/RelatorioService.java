package br.com.alura.app.relatorio.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<RelatorioDto> gerarRelatorio() {
        List<RelatorioDto> relatorio = new ArrayList<>();
        List<Marca> marcas = marcaRepository.findAll();
        for (Marca marca : marcas) {
            List<Veiculo> veiculos = veiculoRepository.findByMarca(marca);
            if (!veiculos.isEmpty()) {
                BigDecimal totalValor = BigDecimal.ZERO;
                for (Veiculo veiculo : veiculos) {
                    totalValor = totalValor.add(BigDecimal.valueOf(veiculo.getValor()));
                }
                RelatorioDto relatorioDto = new RelatorioDto(marca.getNome(), veiculos.size(), totalValor);
                relatorio.add(relatorioDto);
            }
        }
        return relatorio;
    }
}
