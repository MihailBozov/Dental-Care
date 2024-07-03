package bg.softuni.website.web;

import bg.softuni.website.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LocationController {
    
    @GetMapping("/location/{id}")
    public String getObject(@PathVariable String id) {
        // Simulate an object not found scenario
        throw new ObjectNotFoundException("Object not found", id);
    }
}

