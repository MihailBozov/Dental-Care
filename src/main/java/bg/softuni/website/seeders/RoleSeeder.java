package bg.softuni.website.seeders;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.enums.UserRoles;
import bg.softuni.website.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {
    
    RoleRepository roleRepository;
    
    @Autowired
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count() == 0) {
            
            for (UserRoles role : UserRoles.values()) {
                this.roleRepository.saveAndFlush(new Role(role));
            }
        }
    }
}
