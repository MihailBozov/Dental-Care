package bg.softuni.website.web.exception;

import bg.softuni.website.util.exception.ObjectNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler(ObjectNotFoundException.class)
//    public String handleObjectNotFound(ObjectNotFoundException e, Model model) {
//
//        model.addAttribute("id", e.getId());
//        return "errors-controller-based/not-found-global";
//    }

}
