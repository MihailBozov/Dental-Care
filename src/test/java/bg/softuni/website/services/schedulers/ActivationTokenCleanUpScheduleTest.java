package bg.softuni.website.services.schedulers;

import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.services.ActivationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivationTokenCleanUpScheduleTest {
    
    @Mock
    private ActivationTokenRepository activationTokenRepository;
    
    @InjectMocks
    private ActivationTokenCleanUpSchedule activationTokenCleanUpSchedule;
    
    @Test
    void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        activationTokenCleanUpSchedule.deleteExpiredTokens();
        verify(activationTokenRepository, times(1))
                .deleteExpiredTokens(now);
    }
}