package net.ardou.tinylink.error;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Service
public class ErrorService {
    static final String ERROR_MESSAGE_KEY = "errorMessage";
    static final String VIEW_NAME = "error";

    public ModelAndView withErrorMessage(String errorMessage) {
        return new ModelAndView(VIEW_NAME, Map.of(ERROR_MESSAGE_KEY, errorMessage));
    }
}
