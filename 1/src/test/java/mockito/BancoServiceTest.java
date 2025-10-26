package mockito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BancoServiceTest {

    @Test
    public void testConsultarSaldo() {
        // Criando mock do repositório
        ContaRepository mockRepo = mock(ContaRepository.class);

        // Criando conta simulada
        Conta contaSimulada = new Conta("123", 100.0);

        // Definindo comportamento do mock
        when(mockRepo.buscarConta("123")).thenReturn(contaSimulada);

        // Criando serviço com o mock
        BancoService bancoService = new BancoService(mockRepo);

        // Testando consulta de saldo
        double saldo = bancoService.consultarSaldo("123");

        // Verificando se o saldo está correto
        assertEquals(100.0, saldo);

        // Verificando se o método buscarConta foi chamado
        verify(mockRepo).buscarConta("123");
    }

    @Test
    public void testDepositar() {
        // Criando mock do repositório
        ContaRepository mockRepo = mock(ContaRepository.class);

        // Criando conta simulada
        Conta contaSimulada = new Conta("456", 200.0);

        // Definindo comportamento do mock
        when(mockRepo.buscarConta("456")).thenReturn(contaSimulada);

        // Criando serviço com o mock
        BancoService bancoService = new BancoService(mockRepo);

        // Executando depósito
        bancoService.depositar("456", 50.0);

        // Verificando se o saldo foi atualizado
        assertEquals(250.0, contaSimulada.getSaldo());

        // Verificando se os métodos foram chamados corretamente
        verify(mockRepo).buscarConta("456");
        verify(mockRepo).salvar(contaSimulada);
    }
}
