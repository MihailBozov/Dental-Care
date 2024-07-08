package bg.softuni.website.services;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ActivationTokenService {
    private ActivationTokenRepository activationTokenRepository;
    private ScheduledExecutorService scheduledExecutorService;
    
    @Autowired
    public ActivationTokenService(ActivationTokenRepository activationTokenRepository, ScheduledExecutorService scheduledExecutorService) {
        this.activationTokenRepository = activationTokenRepository;
        this.scheduledExecutorService = scheduledExecutorService;
    }
    
    public ActivationToken createActivationToken(UserEntity user) {
        ActivationToken activationToken = new ActivationToken();
        activationToken.setUser(user);
        this.activationTokenRepository.saveAndFlush(activationToken);
        scheduledExecutorService.schedule(() -> deleteActivationToken(activationToken.getId()), 24, TimeUnit.HOURS);
        return activationToken;
    }
    
    public void deleteActivationToken(Long id) {
        Optional<ActivationToken> activationToken = this.activationTokenRepository.findById(id);
        if (activationToken.isPresent()) {
            this.activationTokenRepository.delete(activationToken.get());
        }
    }
    
    
}