package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByName(UserRoles name);
}
