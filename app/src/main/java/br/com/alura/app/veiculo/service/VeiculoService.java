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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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

            Marca marca = marcaRepository.findById(veiculoDTO.getMarca().getId())
                    .orElseThrow(() -> new Exception("Marca não encontrada"));

            veiculo.setMarca(marca);

            return veiculoRepository.save(veiculo);
        } catch (Exception salvarVeiculoException) {
            throw new Exception("Erro ao salvar veículo: " + salvarVeiculoException.getMessage());
        }
    }


    public Veiculo atualizarVeiculo(Long id, VeiculoDTO veiculoDTO) throws Exception {
        try {
            Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
            if (optionalVeiculo.isPresent()) {
                Veiculo veiculo = optionalVeiculo.get();
                veiculo.setQuilometragem(veiculoDTO.getQuilometragem());
                veiculo.setAno(veiculoDTO.getAno());
                veiculo.setValor(veiculoDTO.getValor());
                veiculo.setFotoDoCarro(veiculoDTO.getFotoDoCarro());
                veiculo.setTipoCombustivel(veiculoDTO.getTipoCombustivel());
                veiculo.setModelo(veiculoDTO.getModelo());
                veiculo.setCor(veiculoDTO.getCor());
                veiculo.setDescricao(veiculoDTO.getDescricao());
                veiculo.setCambio(veiculoDTO.getCambio());

                Marca marca = marcaRepository.findById(veiculoDTO.getMarca().getId())
                        .orElseThrow(() -> new Exception("Marca não encontrada"));

                veiculo.setMarca(marca);

                return veiculoRepository.save(veiculo);
            } else {
                throw new Exception("Veículo não encontrado");
            }
        } catch (Exception atualizaVeiculoException) {
            throw new Exception("Erro ao atualizar veículo: " + atualizaVeiculoException.getMessage());
        }
    }

//    public List<Veiculo> listarVeiculosPorValor(BigDecimal valorMaximo) {
//        List<Veiculo> veiculos;
//        switch (valorMaximo.intValue()) {
//            case 10000:
//                veiculos = veiculoRepository.findByValorLessThanEqual(BigDecimal.valueOf(10000));
//                break;
//            case 20000:
//                veiculos = veiculoRepository.findByValorLessThanEqual(BigDecimal.valueOf(20000));
//                break;
//            case 30000:
//                veiculos = veiculoRepository.findByValorLessThanEqual(BigDecimal.valueOf(30000));
//                break;
//            case 50000:
//                veiculos = veiculoRepository.findByValorLessThanEqual(BigDecimal.valueOf(50000));
//                break;
//            default:
//                veiculos = veiculoRepository.findAll();
//                break;
//        }
//        return veiculos;
//    }

    public Page<Veiculo> veiculosFiltradosPorValor(Pageable pageable, BigDecimal valorMin, BigDecimal valorMax) {
        return veiculoRepository.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }
    public Page<Veiculo> veiculosFiltradosPorMarca(Pageable pageable, String nomeDaMarca) {
        return veiculoRepository.veiculosFiltradosPorMarca(pageable, nomeDaMarca);
    }

}
