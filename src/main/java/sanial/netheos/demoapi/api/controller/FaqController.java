package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sanial.netheos.demoapi.api.dto.SearchForm;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.service.FaqService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FaqController {

    @Autowired
    FaqService faqService;

    /**
     * US 2 : This controller is used to get all FAQ entities -> question/answers
     * @return All defined FAQ in DB
     */
    @GetMapping(value = "/faq", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Faq> getAllFaq(){
        return faqService.findAllFaq();
    }

    @PostMapping(value = "/faq/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllFaqContainsParam(@RequestBody SearchForm toCompare){
        return faqService.findAllLikeQuestionOrAnswer(toCompare.getToCompare());
    }
}
