package bg.softuni.website.seeders;

import bg.softuni.website.models.entities.DangerLevel;
import bg.softuni.website.models.enums.DangerLevelName;
import bg.softuni.website.repositories.DangerLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DangerLevelSeeder implements CommandLineRunner {
    
    private DangerLevelRepository dangerLevelRepository;
    
    @Autowired
    public DangerLevelSeeder(DangerLevelRepository dangerLevelRepository) {
        this.dangerLevelRepository = dangerLevelRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (this.dangerLevelRepository.count() == 0) {
            
            for (DangerLevelName name : DangerLevelName.values()) {
                this.dangerLevelRepository.saveAndFlush(new DangerLevel(name));
            }
        }
    }
}
