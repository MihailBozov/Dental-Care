package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.DangerLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangerLevelRepository extends JpaRepository<DangerLevel, Long> {
}
