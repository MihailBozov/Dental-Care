package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.ImageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCategoryRepository extends JpaRepository<ImageCategory, Long> {
}
