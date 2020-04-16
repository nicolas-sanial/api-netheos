package sanial.netheos.demoapi.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.exception.ApiException;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.model.Tag;
import sanial.netheos.demoapi.core.repository.FaqRepository;
import sanial.netheos.demoapi.core.repository.FaqTagRepository;
import sanial.netheos.demoapi.core.repository.TagRepository;
import sanial.netheos.demoapi.core.service.FaqTagService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqTagServiceImpl implements FaqTagService {

    //The constant Logger
    private static final Logger LOG = LoggerFactory.getLogger(FaqTagServiceImpl.class);

    @Autowired
    FaqTagRepository faqTagRepository;

    @Autowired
    FaqRepository faqRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<FaqTag> findAllFaqTag() {
        return faqTagRepository.findAll();
    }

    /**
     * comment -> FaqTagService
     */
    @Override
    public List<FaqTag> createFaqTag(FaqTagCreation faqTag) {

        List<FaqTag> tagListToSave = new ArrayList<>();
        Faq faq = new Faq(faqTag.getQuestion(), faqTag.getAnswer());

        //If question or answer of FAQ not existing, foreach tag, we check if exist, if not we save it.
        if(!faqRepository.findByQuestion(faqTag.getQuestion()).isPresent() || !faqRepository.findByAnswer(faqTag.getAnswer()).isPresent()){
            //This IF allow to just send FAQ without TAG
            if(faqTag.getTagNameList() != null && !faqTag.getTagNameList().isEmpty()){
                faqTag.getTagNameList().forEach(tagName -> {
                    Tag tag = new Tag(tagName);
                    //IF tag already exist, we don't create it.
                    if(!tagRepository.findByName(tagName).isPresent()){
                        tagListToSave.add(new FaqTag(faq, tag));
                    }else{
                        tagListToSave.add(new FaqTag(faq, tagRepository.findByName(tagName).get()));
                    }
                });
            }
            LOG.info("A new Faq as been create.");
            return faqTagRepository.saveAll(tagListToSave);
        //If question or answer of FAQ is already existing, we throw an exception.
        }else{
            //Here is a point of view. We can send exception, or don't create any FAQ, take the corresponding FAQ with FaqRepository and just set tag on it.
            //But to implement this feature, we have to get the exact question AND answer for a specific existing FAQ. Not implementing this solution cause not asked on US.
            throw new ApiException("In order to create a new Faq, you need to set new question and response, question or answer already exist.");
        }
    }
}
