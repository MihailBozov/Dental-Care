package bg.softuni.website.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestimonialDto {
    
    private String authorFirstName;
    
    private String authorImageUrl;
    
    private String content;
    
    private int[] stars;
    
    private String QuoteStartImageUrl;
    
    private String QuoteEndImageUrl;
    
    public TestimonialDto() {
    }
    
}
