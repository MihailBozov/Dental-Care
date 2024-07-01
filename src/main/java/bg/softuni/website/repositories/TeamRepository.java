package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT User AS u FROM User WHERE u.roles.size > 1")
    List<User> findAllByUserRolesSizeGreaterThanOne();
}
