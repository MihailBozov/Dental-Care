package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByToken(String token);
    Optional<ActivationToken> findAllByUser(UserEntity user);
}
