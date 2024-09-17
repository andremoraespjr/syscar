package br.com.inoveiculos.syscar;

import br.com.inoveiculos.syscar.model.Veiculo;
import br.com.inoveiculos.syscar.repository.VeiculoRepository;
import br.com.inoveiculos.syscar.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


    @SpringBootTest
    public class VeiculoServiceTest {

        @InjectMocks
        private VeiculoService veiculoService;

        @Mock
        private VeiculoRepository veiculoRepository;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCriarVeiculo() {
            Veiculo veiculo = new Veiculo();
            veiculo.setMarca("Ford");
            veiculo.setAno(2020);

            when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

            Veiculo resultado = veiculoService.criarVeiculo(veiculo);

            assertNotNull(resultado);
            assertEquals("Ford", resultado.getMarca());
            verify(veiculoRepository, times(1)).save(veiculo);
        }

        @Test
        public void testValidarMarcaInvalida() {
            Veiculo veiculo = new Veiculo();
            veiculo.setMarca("MarcaInvalida");

            assertThrows(IllegalArgumentException.class, () -> veiculoService.criarVeiculo(veiculo));
        }

        @Test
        public void testListarVeiculos() {
            Veiculo veiculo1 = new Veiculo();
            veiculo1.setMarca("Ford");

            Veiculo veiculo2 = new Veiculo();
            veiculo2.setMarca("Honda");

            when(veiculoRepository.findAll()).thenReturn(List.of(veiculo1, veiculo2));

            List<Veiculo> veiculos = veiculoService.listarVeiculos();

            assertNotNull(veiculos);
            assertEquals(2, veiculos.size());
            assertTrue(veiculos.stream().anyMatch(v -> v.getMarca().equals("Ford")));
            assertTrue(veiculos.stream().anyMatch(v -> v.getMarca().equals("Honda")));
        }
    }

