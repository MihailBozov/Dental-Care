package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    
    
}
