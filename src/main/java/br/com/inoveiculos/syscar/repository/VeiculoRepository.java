package br.com.inoveiculos.syscar.repository;

import br.com.inoveiculos.syscar.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByMarca(String marca);
    List<Veiculo> findByAno(Integer ano);
    Long countByVendidoFalse();
    Long countByMarca(String marca);
    List<Veiculo> findByCreatedBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
