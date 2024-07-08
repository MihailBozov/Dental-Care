package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialsRepository extends JpaRepository<UserEntity, Long> {
    
    @Query("SELECT u FROM UserEntity AS u JOIN u.testimonial t WHERE t IS NOT NULL")
    List<UserEntity> findAllUsersWithTestimonials();
}
