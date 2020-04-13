package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.service.FaqService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FaqController {

    @Autowired
    FaqService faqService;

    @GetMapping(value = "/faq", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Faq> getAllFaq(){
        return faqService.findAllFaq();
    }
}
