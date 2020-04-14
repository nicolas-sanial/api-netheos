package sanial.netheos.demoapi.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.repository.FaqRepository;
import sanial.netheos.demoapi.core.service.FaqService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public List<Faq> findAllFaq(){
        return faqRepository.findAll();
    }

    public List<String> findAllLikeQuestionOrAnswer(String toCompare){
        List<String> answerList = new ArrayList<>();
        List<Faq> listFaq = faqRepository.findAllLikeQuestionOrAnswer(toCompare);
        listFaq.forEach(faq -> {
            answerList.add(faq.getAnswer());
        });
        return answerList;
    }
}
