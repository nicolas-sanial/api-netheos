package sanial.netheos.demoapi.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.repository.FaqRepository;
import sanial.netheos.demoapi.core.service.FaqService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    //The constant Logger
    private static final Logger LOG = LoggerFactory.getLogger(FaqServiceImpl.class);

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public List<Faq> findAllFaq(){
        return faqRepository.findAll();
    }

    /**
     * This method use repo to get all
     * @param toCompare
     * @return
     */
    @Override
    public List<String> findAllLikeQuestionOrAnswer(String toCompare){
        List<String> answerList = new ArrayList<>();
        List<Faq> listFaq = faqRepository.findAllLikeQuestionOrAnswer(toCompare);
        listFaq.forEach(faq -> {
            answerList.add(faq.getAnswer());
        });
        LOG.info("All answers returned with the string : {}", toCompare);
        return answerList;
    }
}
