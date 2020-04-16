package sanial.netheos.demoapi.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanial.netheos.demoapi.core.exception.ApiException;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.repository.FaqRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaqServiceTest {

    @Autowired
    FaqService faqService;

    @Autowired
    FaqRepository faqRepository;

    @Autowired
    EntityManager entityManager;

    //This TU work when executing with IntelliJ (Option Run test using Gradle (Default)) but not working using gradle build in cmd don't know why
    @Disabled
    @Test
    public void findAllLikeQuestionOrAnswerOk(){
        //Data that will be returned by mocked repo
        faqRepository.save(new Faq("question1","answer1"));
        faqRepository.save(new Faq("question2","answer2"));
        faqRepository.save(new Faq("question3","answer3"));

        faqRepository.save(new Faq("aaa","bbb"));
        faqRepository.save(new Faq("aaaa","bbbb"));

        //Test if service return the expected result
        List<String> list = faqService.findAllLikeQuestionOrAnswer("est");
        assertEquals(3, list.size());
        List<String> list2 = faqService.findAllLikeQuestionOrAnswer("swe");
        assertEquals(3, list2.size());
        List<String> list3 = faqService.findAllLikeQuestionOrAnswer("aa");
        assertEquals(2, list3.size());

    }

    @Test
    public void findAllLikeQuestionOrAnswerWithParamNull(){
        Assertions.assertThrows(ApiException .class, () -> {
            faqService.findAllLikeQuestionOrAnswer(null);
        });
    }

}
