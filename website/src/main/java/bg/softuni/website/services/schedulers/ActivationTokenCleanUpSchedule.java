package bg.softuni.website.services.schedulers;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.repositories.ActivationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ActivationTokenCleanUpSchedule {
    private ActivationTokenRepository activationTokenRepository;
    
    @Autowired
    public ActivationTokenCleanUpSchedule(ActivationTokenRepository activationTokenRepository) {
        this.activationTokenRepository = activationTokenRepository;
    }
    
    //    @Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000 * 60 * 60 * 24)
    @Scheduled(cron = "00 00 02 * * ?", zone = "Europe/Sofia") // It will be executed at 2 a.m every day
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
      this.activationTokenRepository.deleteExpiredTokens(now);
    }
}
