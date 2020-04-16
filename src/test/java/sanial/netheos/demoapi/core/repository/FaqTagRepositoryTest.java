package sanial.netheos.demoapi.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.model.FaqTag;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaqTagRepositoryTest {

    @Autowired
    FaqTagRepository faqTagRepository;

    //Test question constraint on faq creation
    @Test
    public void saveFaqTagWithoutQuestion(){
        FaqTag faqTag = new FaqTag();
        Faq faq = new Faq();
        faq.setAnswer("test");
        faqTag.setFaq(faq);

        //Expect throw specific Exception
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            faqTagRepository.save(faqTag);
        });
    }

    //Test answer constraint on faq creation
    @Test
    public void saveFaqTagWithoutAnswer(){
        FaqTag faqTag = new FaqTag();
        Faq faq = new Faq();
        faq.setQuestion("test");
        faqTag.setFaq(faq);

        //Expect throw specific Exception
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            faqTagRepository.save(faqTag);
        });
    }
}
