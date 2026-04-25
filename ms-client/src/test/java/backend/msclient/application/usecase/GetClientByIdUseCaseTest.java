package backend.msclient.application.usecase;

import backend.msclient.domain.exception.ClientNotFoundException;
import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetClientByIdUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private GetClientByIdUseCase getClientByIdUseCase;

    @Test
    void shouldCreateClientSuccessfully() {

        Client client = Client.builder()
                .name("Victor")
                .status(true)
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = getClientByIdUseCase.execute(1L);

        assertNotNull(result);
        assertEquals("Victor", result.getName());

        verify(clientRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {

        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            getClientByIdUseCase.execute(1L);
        });

        verify(clientRepository).findById(1L);
    }

}