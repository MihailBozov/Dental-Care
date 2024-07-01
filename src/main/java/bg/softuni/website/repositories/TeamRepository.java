package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User AS u JOIN u.roles WHERE SIZE(u.roles) > 1")
    List<User> findAllTeamMembers();
}
