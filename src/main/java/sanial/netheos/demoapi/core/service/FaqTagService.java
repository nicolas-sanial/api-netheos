package sanial.netheos.demoapi.core.service;

import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.model.FaqTag;

import java.util.List;

public interface FaqTagService {

    List<FaqTag> findAllFaqTag();

    void createFaqTag(FaqTagCreation faqTag);
}
