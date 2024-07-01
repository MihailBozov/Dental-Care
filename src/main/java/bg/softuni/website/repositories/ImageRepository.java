package bg.softuni.website.repositories;

import bg.softuni.website.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    @Query("SELECT i FROM Image AS i WHERE i.name = 'quotes_white_start.svg' OR i.name = 'quotes_white_end.svg' ORDER BY i.name DESC")
    List<Image> findAllQuoteImagesOrdered();
}
