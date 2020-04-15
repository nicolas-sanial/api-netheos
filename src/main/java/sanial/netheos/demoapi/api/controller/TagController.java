package sanial.netheos.demoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanial.netheos.demoapi.core.model.Tag;
import sanial.netheos.demoapi.core.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     *
     * @return all the tags
     */
    @GetMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tag> getAllTag(){
        return tagService.findAllTag();
    }
}
