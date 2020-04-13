package sanial.netheos.demoapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.model.Tag;

import java.util.List;

@Repository
public interface FaqTagRepository extends JpaRepository<FaqTag, Integer> {

    List<FaqTag> findByFaqOrderById(Faq faq);

    List<FaqTag> findByTagOrderById(Tag tag);
}
