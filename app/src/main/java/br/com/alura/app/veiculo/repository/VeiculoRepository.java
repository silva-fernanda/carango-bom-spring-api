package br.com.alura.app.veiculo.repository;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.veiculo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

    List<Veiculo> findByMarca(Marca marca);
}
