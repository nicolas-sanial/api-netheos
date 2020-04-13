package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.service.FaqTagService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FaqTagController {

    @Autowired
    FaqTagService faqTagService;

    @GetMapping(value = "/faqtag", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FaqTag> getAllFaq(){
        return faqTagService.findAllFaqTag();
    }
}
