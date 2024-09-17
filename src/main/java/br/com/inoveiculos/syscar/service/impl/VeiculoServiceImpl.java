package br.com.inoveiculos.syscar.service.impl;

import br.com.inoveiculos.syscar.model.Veiculo;
import br.com.inoveiculos.syscar.repository.VeiculoRepository;
import br.com.inoveiculos.syscar.service.VeiculoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    private static final List<String> MARCAS_VALIDAS = List.of("Ford", "Honda", "Chevrolet", "Toyota");

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> obterVeiculo(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        validarMarca(veiculo.getMarca());
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        validarMarca(veiculo.getMarca());
        veiculo.setUpdated(LocalDateTime.now());
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Long contarVeiculosNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    public Long contarVeiculosPorMarca(String marca) {
        return veiculoRepository.countByMarca(marca);
    }

    public List<Veiculo> veiculosPorData(LocalDate startDate, LocalDate endDate) {
        return veiculoRepository.findByCreatedBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    public void validarMarca(String marca) {
        if (!MARCAS_VALIDAS.contains(marca)) {
            throw new IllegalArgumentException("Marca inválida: " + marca);
        }
    }
}