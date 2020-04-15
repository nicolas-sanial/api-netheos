package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sanial.netheos.demoapi.api.dto.ErrorPayload;
import sanial.netheos.demoapi.api.dto.SearchForm;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.service.FaqService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FaqController {

    @Autowired
    FaqService faqService;

    /**
     * US 2 : This controller allows you to get all FAQ entities -> question/answers
     * @return All defined FAQ in DB
     */
    @GetMapping(value = "/faq", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Faq> getAllFaq(){
        return faqService.findAllFaq();
    }

    /**
     * US 3 : This controller allows you to return all answers with the sent string contained in question/answer.
     * @param toCompare Ths string used to search all answers
     * @param errors The param containing all usage API errors
     * @return All the answers corresponding with the demand
     */
    @PostMapping(value = "/faq/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllFaqContainsParam(@Valid @RequestBody SearchForm toCompare, Errors errors){

        if (errors.hasErrors()) {
            // get all errors
            String message = errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(new ErrorPayload(message));
        }
        return ResponseEntity.ok(faqService.findAllLikeQuestionOrAnswer(toCompare.getToCompare()));
    }
}
