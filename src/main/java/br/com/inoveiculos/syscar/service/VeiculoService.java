package br.com.inoveiculos.syscar.service;

import br.com.inoveiculos.syscar.model.Veiculo;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VeiculoService {
    public List<Veiculo> listarVeiculos();
    Optional<Veiculo> obterVeiculo(Long id);
    Veiculo criarVeiculo(Veiculo veiculo);
    Veiculo atualizarVeiculo(Long id, Veiculo veiculo);
    void excluirVeiculo(Long id);
    Long contarVeiculosNaoVendidos();
    Long contarVeiculosPorMarca(String marca);
    List<Veiculo> veiculosPorData(LocalDate startDate, LocalDate endDate);
    void validarMarca(String marca);
}
