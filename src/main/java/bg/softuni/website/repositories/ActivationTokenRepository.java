package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByToken(String token);
    Optional<ActivationToken> findByUserEmail(String email);
    
    List<ActivationToken> findAllByExpiryDateBefore(LocalDateTime now);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM ActivationToken t WHERE t.expiryDate < :now")
    void deleteExpiredTokens(LocalDateTime now);
}
