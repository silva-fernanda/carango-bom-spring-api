package br.com.alura.app.veiculo.service;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.marca.repository.MarcaRepository;
import br.com.alura.app.veiculo.dto.VeiculoDTO;
import br.com.alura.app.veiculo.dto.VeiculoListDTO;
import br.com.alura.app.veiculo.entity.Veiculo;
import br.com.alura.app.veiculo.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoService {
    private VeiculoRepository veiculoRepository;
    private MarcaRepository marcaRepository;

    public Page<VeiculoListDTO> listarVeiculosAVenda(Pageable pageable) throws Exception {
        try {
            Page<Veiculo> veiculos = veiculoRepository.findAll(pageable);

            if (veiculos.isEmpty()) {
                throw new RuntimeException("Nenhum veículo encontrado");
            }

            return veiculos.map(veiculo -> {
                VeiculoListDTO veiculoListDTO = new VeiculoListDTO();
                veiculoListDTO.setId(veiculo.getId());
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
        } catch (Exception listarVeiculosRuntimeException) {
            throw new Exception("Nenhum veículo encontrado" + listarVeiculosRuntimeException.getMessage());
        }
    }

    public void excluirVeiculo(Long id) throws Exception {
        try {
            veiculoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException excluirVeiculoException) {
            System.out.println("O erro ocorrido foi: " + excluirVeiculoException.getMessage());
            throw new Exception("Veículo não encontrado com o ID: " + id, excluirVeiculoException);
        }
    }


    public Veiculo salvarVeiculo(VeiculoDTO veiculoDTO) throws Exception {
        try {
            Veiculo veiculo = new Veiculo();
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
                    .orElseThrow(() -> new Exception("Marca não encontrada"));

            veiculo.setMarca(marca);

            return veiculoRepository.save(veiculo);
        } catch (Exception salvarVeiculoException) {
            throw new Exception("Erro ao salvar veículo: " + salvarVeiculoException.getMessage());
        }
    }


    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDTOAtualizado) throws Exception {
        try {
            Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);

            if (veiculoOptional.isEmpty()) {
                throw new Exception("Veículo não encontrado com id: " + id);
            }

            Veiculo veiculoExistente = veiculoOptional.get();

            Optional<Marca> marcaOptional = marcaRepository.findById(veiculoDTOAtualizado.getMarcaId());

            if (marcaOptional.isEmpty()) {
                throw new Exception("A marca informada não existe");
            }

            Marca marca = marcaOptional.get();

            veiculoExistente.setMarca(marca);
            veiculoExistente.setModelo(veiculoDTOAtualizado.getModelo());
            veiculoExistente.setAno(veiculoDTOAtualizado.getAno());
            veiculoExistente.setValor(veiculoDTOAtualizado.getValor());

            Veiculo veiculoAtualizado = veiculoRepository.save(veiculoExistente);

            VeiculoDTO veiculoDTOSalvo = new VeiculoDTO();
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
        } catch (Exception atualizarVeiculoException) {
            throw new Exception("Erro ao atualizar o veículo: " + atualizarVeiculoException.getMessage());
        }
    }


}
