package bg.softuni.website.seeders;

import bg.softuni.website.models.entities.ImageCategory;
import bg.softuni.website.models.enums.ImageCategoryName;
import bg.softuni.website.repositories.ImageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImageCategorySeeder implements CommandLineRunner {
    
    private ImageCategoryRepository imageCategoryRepository;
    
    @Autowired
    public ImageCategorySeeder(ImageCategoryRepository imageCategoryRepository) {
        this.imageCategoryRepository = imageCategoryRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (this.imageCategoryRepository.count() == 0) {
            for(ImageCategoryName name : ImageCategoryName.values()) {
                this.imageCategoryRepository.saveAndFlush(new ImageCategory(name));
            }
        }
    }
}
