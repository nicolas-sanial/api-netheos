package sanial.netheos.demoapi.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanial.netheos.demoapi.core.model.Tag;
import sanial.netheos.demoapi.core.repository.TagRepository;
import sanial.netheos.demoapi.core.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> findAllTag() {
        return tagRepository.findAll();
    }
}
