package sanial.netheos.demoapi.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.repository.FaqTagRepository;
import sanial.netheos.demoapi.core.service.FaqTagService;

import java.util.List;

@Service
public class FaqTagServiceImpl implements FaqTagService {

    @Autowired
    FaqTagRepository faqTagRepository;

    @Override
    public List<FaqTag> findAllFaqTag() {
        return faqTagRepository.findAll();
    }
}
