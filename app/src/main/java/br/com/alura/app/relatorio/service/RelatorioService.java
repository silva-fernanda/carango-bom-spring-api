package br.com.alura.app.relatorio.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import br.com.alura.app.relatorio.dto.RelatorioDto;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class RelatorioService {
    private MarcaRepository marcaRepository;
    private VeiculoRepository veiculoRepository;

    public Page<RelatorioDto> gerarRelatorio(Pageable pageable) throws Exception {
        try {
            List<RelatorioDto> relatorio = new ArrayList<>();
            List<Marca> marcas = marcaRepository.findAll();
            for (Marca marca : marcas) {
                List<Veiculo> veiculos = veiculoRepository.findByMarca(marca);
                if (!veiculos.isEmpty()) {
                    BigDecimal totalValor = BigDecimal.ZERO;
                    for (Veiculo veiculo : veiculos) {
                        totalValor = totalValor.add(veiculo.getValor());
                    }
                    RelatorioDto relatorioDto = new RelatorioDto(marca.getNome(), veiculos.size(), totalValor);
                    relatorio.add(relatorioDto);
                }
            }
            if (relatorio.isEmpty()) {
                throw new Exception("Não há informações suficientes para gerar o relatório.");
            }
            return new PageImpl<>(relatorio, pageable, relatorio.size());
        } catch (Exception gerarRelatorioException) {
            throw new Exception("Erro ao gerar o relatório: " + gerarRelatorioException.getMessage());
        }
    }

}
