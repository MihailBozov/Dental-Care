package bg.softuni.website.services;

import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TreatmentService {
    
    TreatmentRepository treatmentRepository;
    
    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }
    
    public List<Treatment> getAllTreatments() {
        return this.treatmentRepository.findAll();
    }
}
