package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.service.FaqTagService;

import javax.validation.Valid;
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

    @PostMapping(value = "/faqtag/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void FaqTagcreateOrUpdate(@Valid @RequestBody FaqTagCreation faqTag) {
        faqTagService.createFaqTag(faqTag);
    }
}
