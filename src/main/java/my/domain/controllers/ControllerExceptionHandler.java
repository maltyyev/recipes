package my.domain.controllers;

import my.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by maltyyev on 20.01.18 1:46
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView pageNotFound(Exception e) { return getErrorPage("404error", e); }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView badRequest(Exception e) { return getErrorPage("400error", e); }

    private ModelAndView getErrorPage(String viewName, Exception e) {

        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
