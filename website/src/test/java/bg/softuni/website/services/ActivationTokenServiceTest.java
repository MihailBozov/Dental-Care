package bg.softuni.website.services;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivationTokenServiceTest {
    
    private ActivationTokenService activationTokenService;
    
    @Mock
    ActivationTokenRepository activationTokenRepository;
    
    @Mock
    ScheduledExecutorService scheduledExecutorService;
    
    @BeforeEach
    void setUp() {
        activationTokenService = new ActivationTokenService(activationTokenRepository, scheduledExecutorService);
    }
    
    
    @Test
    void createActivationTokenTest() {
        UserEntity user = new UserEntity();
        ActivationToken activationToken = new ActivationToken();
        activationToken.setUser(user);

        when(activationTokenRepository.saveAndFlush(any(ActivationToken.class)))
                .thenReturn(activationToken);

        ActivationToken token = activationTokenService.createActivationToken(user);

        Assertions.assertNotNull(token);
        Assertions.assertEquals(user, token.getUser());
    }
    
    @Test
    void deleteActivationTokenTest() {
        ActivationToken activationToken = new ActivationToken();
        activationToken.setId(1L);

        when(activationTokenRepository.findById(1L))
                .thenReturn(Optional.of(activationToken));
        activationTokenService.deleteActivationToken(1L);

        verify(activationTokenRepository, times(1)).findById(1L);
        verify(activationTokenRepository, times(1)).delete(activationToken);
    }

    @Test
    void deleteActivationTokenNotFoundTest() {
        when(activationTokenRepository.findById(1L)).thenReturn(Optional.empty());

        activationTokenService.deleteActivationToken(1L);

        verify(activationTokenRepository, times(1)).findById(1L);
        verify(activationTokenRepository, never()).delete(any(ActivationToken.class));
    }
}