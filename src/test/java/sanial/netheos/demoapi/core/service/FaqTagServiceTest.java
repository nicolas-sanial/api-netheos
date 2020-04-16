package sanial.netheos.demoapi.core.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.exception.ApiException;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.model.Tag;
import sanial.netheos.demoapi.core.repository.FaqRepository;
import sanial.netheos.demoapi.core.repository.FaqTagRepository;
import sanial.netheos.demoapi.core.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaqTagServiceTest {

    @Autowired
    FaqTagService faqTagService;

    @Autowired
    FaqRepository faqRepository;

    @Autowired
    TagRepository tagRepository;

    //This TU work when executing with IntelliJ (Option Run test using Gradle (Default)) but not working using gradle build in cmd don't know why (h2 don't work in cmd)
    @Disabled
    @Test
    public void createFaqTagTestOk(){

        FaqTagRepository faqTagRepository = mock(FaqTagRepository.class);

        //save some datas in H2 DB
        faqRepository.save(new Faq("question1", "answer1"));
        faqRepository.save(new Faq("question2", "answer2"));
        tagRepository.save(new Tag("tagName1"));
        tagRepository.save(new Tag("tagName2"));

        //First data verification
        List<Faq> listFaq1 = faqRepository.findAll();
        assertEquals(2, listFaq1.size());
        List<Tag> listTag1 = tagRepository.findAll();
        assertEquals(2, listTag1.size());

        //Create data that will be saved
        List<String> listTagCreation1 = new ArrayList<>();
        listTagCreation1.add("tagName3");
        listTagCreation1.add("tagName4");
        listTagCreation1.add("tagName5");
        FaqTagCreation faqTagCreate1 = new FaqTagCreation();
        faqTagCreate1.setQuestion("question3");
        faqTagCreate1.setAnswer("answer3");
        faqTagCreate1.setTagNameList(listTagCreation1);
        List<FaqTagCreation> list = new ArrayList<>();
        list.add(faqTagCreate1);

        //Mock the saving repo and execute the tested method
        when(faqTagRepository.saveAll(any(List.class))).thenReturn(list);
        faqTagService.createFaqTag(faqTagCreate1);

        //Second deta verification to check is the save have been executed
        List<Faq> listFaq2 = faqRepository.findAll();
        assertEquals(3, listFaq2.size());
        List<Tag> listTag2 = tagRepository.findAll();
        assertEquals(5, listTag2.size());
    }

    //Verify Tags are not saved twice
    //This TU work when executing with IntelliJ (Option Run test using Gradle (Default)) but not working using gradle build in cmd don't know why (h2 don't work in cmd)
    @Disabled
    @Test
    public void createFaqTagWithMissingParam(){

        FaqTagRepository faqTagRepository = mock(FaqTagRepository.class);

        //save some datas in H2 DB
        faqRepository.save(new Faq("question1", "answer1"));
        faqRepository.save(new Faq("question2", "answer2"));

        //First data verification
        List<Faq> listFaq1 = faqRepository.findAll();
        assertEquals(2, listFaq1.size());

        //Create data that will not be saved
        List<String> listTagCreation1 = new ArrayList<>();
        listTagCreation1.add("tagName3");
        listTagCreation1.add("tagName4");
        FaqTagCreation faqTagCreate1 = new FaqTagCreation();
        faqTagCreate1.setQuestion("question1");
        faqTagCreate1.setAnswer("answer1");
        faqTagCreate1.setTagNameList(listTagCreation1);
        List<FaqTagCreation> list = new ArrayList<>();
        list.add(faqTagCreate1);

        //Mock the saving repo and execute the tested method
        when(faqTagRepository.saveAll(any(List.class))).thenReturn(list);
        Assertions.assertThrows(ApiException.class, () -> {
            faqTagService.createFaqTag(faqTagCreate1);
        });
    }
}
