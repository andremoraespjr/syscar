package br.com.inoveiculos.syscar.controller;

import br.com.inoveiculos.syscar.model.Veiculo;
import br.com.inoveiculos.syscar.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículo", description = "Cadastro de Veículos da SysCar - Inoveiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Lista todos os veículos", description = "Retorna uma lista de todos os veículos cadastrados")
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um veículo por ID", description = "Retorna os detalhes de um veículo")
    public ResponseEntity<Veiculo> obterVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.obterVeiculo(id);
        return veiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo veículo", description = "Cadastra um novo veículo")
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um veículo existente", description = "Atualiza as informações de um veículo")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um veículo", description = "Exclui um veículo")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nao-vendidos")
    @Operation(summary = "Conta veículos não vendidos", description = "Retorna a quantidade de veículos que não foram vendidos")
    public ResponseEntity<Long> contarVeiculosNaoVendidos() {
        return ResponseEntity.ok(veiculoService.contarVeiculosNaoVendidos());
    }

    @GetMapping("/por-marca")
    @Operation(summary = "Conta veículos por marca", description = "Retorna a quantidade de veículos por marca")
    public ResponseEntity<Long> contarVeiculosPorMarca(@RequestParam String marca) {
        return ResponseEntity.ok(veiculoService.contarVeiculosPorMarca(marca));
    }

    @GetMapping("/por-data")
    @Operation(summary = "Obtém veículos por data", description = "Retorna uma lista de veículos registrados entre duas datas")
    public ResponseEntity<List<Veiculo>> veiculosPorData(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(veiculoService.veiculosPorData(startDate, endDate));
    }
}
