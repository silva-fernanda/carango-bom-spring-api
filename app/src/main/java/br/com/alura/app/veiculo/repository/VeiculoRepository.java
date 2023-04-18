package br.com.alura.app.veiculo.repository;

import br.com.alura.app.marca.entity.Marca;
import br.com.alura.app.veiculo.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    List<Veiculo> findByMarca(Marca marca);

    @Query("SELECT v FROM Veiculo v WHERE v.valor BETWEEN :valorMin AND :valorMax")
    Page<Veiculo> veiculosFiltradosPorValor(Pageable pageable, @Param("valorMin")BigDecimal valorMin, @Param("valorMax") BigDecimal valorMax);
}
