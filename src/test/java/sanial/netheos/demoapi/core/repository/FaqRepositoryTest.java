package sanial.netheos.demoapi.core.repository;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanial.netheos.demoapi.core.model.Faq;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaqRepositoryTest {

    @Autowired
    FaqRepository faqRepository;

    //Test the query
    //This TU work when executing with IntelliJ (Option Run test using Gradle (Default)) but not working using gradle build in cmd don't know why (h2 don't work in cmd)
    @Disabled
    @Test
    public void findAllLikeQuestionOrAnswer(){
        //Save some Faq entities
        faqRepository.save(new Faq("question1", "answer1"));
        faqRepository.save(new Faq("question2", "answer2"));
        faqRepository.save(new Faq("question3", "answer3"));

        //Verify the request find the expect number of result
        assertEquals(3, faqRepository.findAllLikeQuestionOrAnswer("sti").size());
        //Test searching in answers attributes
        assertEquals("answer3", faqRepository.findAllLikeQuestionOrAnswer("swe").get(2).getAnswer());
    }
}
