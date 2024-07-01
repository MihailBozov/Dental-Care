package bg.softuni.website.models.dtos;

import java.util.List;

public class TestimonialDto {
    
    private String authorFirstName;
    
    private String authorImageUrl;
    
    private String content;
    
    private int[] stars;
    
    private String QuoteStartImageUrl;
    
    private String QuoteEndImageUrl;
    
    public TestimonialDto() {
    }
    
    public String getAuthorFirstName() {
        return authorFirstName;
    }
    
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }
    
    public String getAuthorImageUrl() {
        return authorImageUrl;
    }
    
    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getQuoteStartImageUrl() {
        return QuoteStartImageUrl;
    }
    
    public void setQuoteStartImageUrl(String quoteStartImageUrl) {
        QuoteStartImageUrl = quoteStartImageUrl;
    }
    
    public String getQuoteEndImageUrl() {
        return QuoteEndImageUrl;
    }
    
    public void setQuoteEndImageUrl(String quoteEndImageUrl) {
        QuoteEndImageUrl = quoteEndImageUrl;
    }
    
    public int[] getStars() {
        return stars;
    }
    
    public void setStars(int[] stars) {
        this.stars = stars;
    }
}
