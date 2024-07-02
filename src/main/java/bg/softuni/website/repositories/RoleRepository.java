package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByName(UserRole name);
}
