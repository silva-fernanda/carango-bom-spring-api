package br.com.alura.app.veiculo.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import br.com.alura.app.veiculo.dto.VeiculoDTO;
import br.com.alura.app.veiculo.dto.VeiculoListDTO;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoService {
    private VeiculoRepository veiculoRepository;
    private MarcaRepository marcaRepository;


    public Page<VeiculoListDTO> listarVeiculosAVenda(Pageable pageable) {
        Page<Veiculo> veiculos = veiculoRepository.findAll(pageable);

        return veiculos.map(veiculo -> {
            VeiculoListDTO veiculoListDTO = new VeiculoListDTO();
            veiculoListDTO.setId(veiculo.getId());
            veiculoListDTO.setNome(veiculo.getNome());
            veiculoListDTO.setQuilometragem(veiculo.getQuilometragem());
            veiculoListDTO.setAno(veiculo.getAno());
            veiculoListDTO.setValor(veiculo.getValor());
            veiculoListDTO.setFotoDoCarro(veiculo.getFotoDoCarro());
            veiculoListDTO.setTipoCombustivel(veiculo.getTipoCombustivel());
            veiculoListDTO.setModelo(veiculo.getModelo());
            veiculoListDTO.setCor(veiculo.getCor());
            veiculoListDTO.setDescricao(veiculo.getDescricao());
            veiculoListDTO.setCambio(veiculo.getCambio());
            veiculoListDTO.setStatus(veiculo.getStatus());
            veiculoListDTO.setNomeDaMarca(veiculo.getMarca().getNome());

            return veiculoListDTO;
        });
    }

    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo salvarVeiculo(VeiculoDTO veiculoDTO)  {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome(veiculoDTO.getNome());
        veiculo.setQuilometragem(veiculoDTO.getQuilometragem());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setValor(veiculoDTO.getValor());
        veiculo.setFotoDoCarro(veiculoDTO.getFotoDoCarro());
        veiculo.setTipoCombustivel(veiculoDTO.getTipoCombustivel());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setDescricao(veiculoDTO.getDescricao());
        veiculo.setCambio(veiculoDTO.getCambio());

        Marca marca = marcaRepository.findById(veiculoDTO.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        veiculo.setMarca(marca);

        return veiculoRepository.save(veiculo);

//        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

//        VeiculoDTO veiculoDTOSalvo = new VeiculoDTO();
//        veiculoDTOSalvo.setNome(veiculoSalvo.getNome());
//        veiculoDTOSalvo.setQuilometragem(veiculoSalvo.getQuilometragem());
//        veiculoDTOSalvo.setAno(veiculoSalvo.getAno());
//        veiculoDTOSalvo.setValor(veiculoSalvo.getValor());
//        veiculoDTOSalvo.setFotoDoCarro(veiculoSalvo.getFotoDoCarro());
//        veiculoDTOSalvo.setTipoCombustivel(veiculoSalvo.getTipoCombustivel());
//        veiculoDTOSalvo.setModelo(veiculoSalvo.getModelo());
//        veiculoDTOSalvo.setCor(veiculoSalvo.getCor());
//        veiculoDTOSalvo.setDescricao(veiculoSalvo.getDescricao());
//        veiculoDTOSalvo.setCambio(veiculoSalvo.getCambio());
//        veiculoDTOSalvo.setMarca(marca);
//
//        return veiculoDTOSalvo;

    }

    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDTOAtualizado) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);

        if (veiculoOptional.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado com id: " + id);
        }

        Veiculo veiculoExistente = veiculoOptional.get();

        Optional<Marca> marcaOptional = marcaRepository.findById(veiculoDTOAtualizado.getMarcaId());

        if (marcaOptional.isEmpty()) {
            throw new IllegalArgumentException("A marca informada não existe");
        }

        Marca marca = marcaOptional.get();

        veiculoExistente.setMarca(marca);
        veiculoExistente.setModelo(veiculoDTOAtualizado.getModelo());
        veiculoExistente.setAno(veiculoDTOAtualizado.getAno());
        veiculoExistente.setValor(veiculoDTOAtualizado.getValor());

        Veiculo veiculoAtualizado = veiculoRepository.save(veiculoExistente);

        VeiculoDTO veiculoDTOSalvo = new VeiculoDTO();
        veiculoDTOSalvo.setNome(veiculoAtualizado.getNome());
        veiculoDTOSalvo.setQuilometragem(veiculoAtualizado.getQuilometragem());
        veiculoDTOSalvo.setAno(veiculoAtualizado.getAno());
        veiculoDTOSalvo.setValor(veiculoAtualizado.getValor());
        veiculoDTOSalvo.setFotoDoCarro(veiculoAtualizado.getFotoDoCarro());
        veiculoDTOSalvo.setTipoCombustivel(veiculoAtualizado.getTipoCombustivel());
        veiculoDTOSalvo.setModelo(veiculoAtualizado.getModelo());
        veiculoDTOSalvo.setCor(veiculoAtualizado.getCor());
        veiculoDTOSalvo.setDescricao(veiculoAtualizado.getDescricao());
        veiculoDTOSalvo.setCambio(veiculoAtualizado.getCambio());
        veiculoDTOSalvo.setMarca(marca);

        return veiculoDTOSalvo;
    }

}
