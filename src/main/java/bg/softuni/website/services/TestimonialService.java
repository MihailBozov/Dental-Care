package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Testimonial;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.repositories.TeamRepository;
import bg.softuni.website.repositories.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestimonialService {
    private TestimonialsRepository testimonialsRepository;
    private ImageRepository imageRepository;
    
    @Autowired
    public TestimonialService(TestimonialsRepository testimonialsRepository, ImageRepository imageRepository) {
        this.testimonialsRepository = testimonialsRepository;
        this.imageRepository = imageRepository;
    }
    
    
    public List<TestimonialDto> getAllTestimonials() {
        List<User> users = this.testimonialsRepository.findAllUsersWithTestimonials();
        List<Image> icons = this.imageRepository.findAllQuoteImagesOrdered();
        List<TestimonialDto> testimonialDtos = new ArrayList<>();
        for (User user : users) {
            TestimonialDto testimonialDto = new TestimonialDto();
            testimonialDto.setAuthorFirstName(user.getFirstName());
            testimonialDto.setAuthorImageUrl(user.getImage().getUrl());
            testimonialDto.setContent(user.getTestimonial().getContent());
            
            testimonialDto.setStars(new int[user.getTestimonial().getCountStars()]);
            testimonialDto.setQuoteStartImageUrl(icons.get(0).getUrl());
            testimonialDto.setQuoteEndImageUrl(icons.get(1).getUrl());
            testimonialDtos.add(testimonialDto);
        }
        return testimonialDtos;
    }
}
