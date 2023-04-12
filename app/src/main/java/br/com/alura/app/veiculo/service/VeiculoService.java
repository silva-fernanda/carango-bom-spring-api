package br.com.alura.app.veiculo.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Veiculo> listarVeiculosAVenda() {
        return veiculoRepository.findAll();
    }


    public void excluir(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo save(Veiculo veiculo) {
        Optional<Marca> marcaOptional = marcaRepository.findById(veiculo.getMarca().getId());

        if (marcaOptional.isEmpty()) {
            throw new IllegalArgumentException("A marca informada não existe");
        }

        veiculo.setMarca(marcaOptional.get());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado com id: " + id));

        veiculoExistente.setMarca(veiculoAtualizado.getMarca());
        veiculoExistente.setModelo(veiculoAtualizado.getModelo());
        veiculoExistente.setAno(veiculoAtualizado.getAno());
        veiculoExistente.setValor(veiculoAtualizado.getValor());

        return veiculoRepository.save(veiculoExistente);
    }
}
