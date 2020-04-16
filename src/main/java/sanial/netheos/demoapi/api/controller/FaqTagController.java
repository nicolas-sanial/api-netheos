package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sanial.netheos.demoapi.api.dto.ErrorPayload;
import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.service.FaqTagService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FaqTagController {

    @Autowired
    FaqTagService faqTagService;

    @GetMapping(value = "/faqtag", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FaqTag> getAllFaq(){
        return faqTagService.findAllFaqTag();
    }

    /**
     * US 1 : This controller allows you to create new faqs and their associated tags.
     * @param faqTag The payload sent containing all informations for creation
     * @param errors The param containing all usage API errors
     * @return the result of the creation, ok or errors.
     */
    @PostMapping(value = "/faqtag/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> FaqTagCreateOrUpdate(@Valid @RequestBody FaqTagCreation faqTag, Errors errors) {

        if (errors.hasErrors()) {
            // get all errors
            String message = errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(new ErrorPayload(message));
        }

        faqTagService.createFaqTag(faqTag);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
