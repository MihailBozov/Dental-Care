//package bg.softuni.website.services.rest;
//
//import bg.softuni.website.models.dtos.EditTreatmentDto;
//import bg.softuni.website.models.dtos.NewTreatmentDto;
//import bg.softuni.website.models.dtos.TreatmentDto;
//import bg.softuni.website.models.entities.Treatment;
//import bg.softuni.website.repositories.TreatmentRepository;
//import jakarta.transaction.Transactional;
//import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TreatmentRestService {
//    
//    private final Logger logger = LoggerFactory.getLogger(TreatmentRestService.class);
//    private final TreatmentRepository treatmentRepository;
//    
//    private final ModelMapper modelMapper;
//    
//    private final RestClient treatmentRestClient;
//    
//    @Autowired
//    public TreatmentRestService(TreatmentRepository treatmentRepository, ModelMapper modelMapper, @Qualifier("treatmentsRestClient") RestClient treatmentRestClient) {
//        this.treatmentRepository = treatmentRepository;
//        this.modelMapper = modelMapper;
//        this.treatmentRestClient = treatmentRestClient;
//    }
//    
//    
//    
//    public void newTreatment(NewTreatmentDto newTreatmentDto) {
//        treatmentRestClient
//                .post()
//                .uri("/treatments")
//                .body(newTreatmentDto)
//                .retrieve();
//    }
//    
//
//    public List<TreatmentDto> getAllTreatmentDtos() {
//        logger.warn("Getting all treatment dtos");
//        return treatmentRestClient
//                .get()
//                .uri("http://localhost:8081/treatments")
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<TreatmentDto>>() {});
//    }
//
//    
//    public boolean deleteTreatment(long id) {
//        return Boolean.TRUE.equals(this.treatmentRestClient
//                .delete()
//                .uri("http://localhost:8081/treatments/" + id)
//                .retrieve()
//                .body(Boolean.class));
//                
//    }
//
//    
//    public boolean editTreatment(EditTreatmentDto editTreatmentDto, long id) {
//        this.treatmentRestClient
//                .put()
//                .uri("http://localhost:8081/treatments/" + id)
//                .body(editTreatmentDto)
//                .retrieve();
//        return true;
//    }
//    
//
//    public Treatment getTreatment(Long id) {
//        return treatmentRestClient
//                .get()
//                .uri("http://localhost:8081" + id)
//                .retrieve()
//                .body(Treatment.class);
//    }
//
//    public EditTreatmentDto getEditTreatmentDto(Long id) {
//        return treatmentRestClient
//                .put()
//                .uri("http://localhost:8081" + id)
//                .retrieve()
//                .body(EditTreatmentDto.class);
//    }
//}
//
