package kg.attractor.istore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;


@ControllerAdvice(annotations = RestController.class)
public class BindExceptionHandler {

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBind(BindException ex){
        BindingResult bindingResult = ex.getBindingResult();

        List<String> apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());
        
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}
