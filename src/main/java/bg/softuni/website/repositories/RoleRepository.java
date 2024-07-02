package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByName(UserRole name);
    
}
