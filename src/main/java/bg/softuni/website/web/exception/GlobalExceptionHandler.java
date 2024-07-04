//package bg.softuni.website.web.exceptionHandlers;
//
//import bg.softuni.website.util.exception.ObjectNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    
//    @ExceptionHandler(ObjectNotFoundException.class)
//    public ModelAndView handleObjectNotFound(ObjectNotFoundException e) {
//        ModelAndView modelAndView = new ModelAndView("errors-controller-based/not-found-global");
//        modelAndView.addObject("id", e.getId());
//        return modelAndView;
//    }
//}
