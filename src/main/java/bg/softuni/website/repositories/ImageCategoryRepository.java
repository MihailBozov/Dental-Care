package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.ImageCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageCategoryRepository extends JpaRepository<ImageCategory, Long> {
}
