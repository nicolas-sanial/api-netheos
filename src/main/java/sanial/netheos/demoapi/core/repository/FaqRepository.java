package sanial.netheos.demoapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanial.netheos.demoapi.core.model.Faq;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

    /**
     * Catch all Faq object where the searched String in contained in question or answer attribute.
     * Using like %% break the table index, maybe better to use Elasticsearch in this case, depending on DB volume.
     * @param toCompare the string that have to be searched in question or answers
     * @return the list of faq that contain the string in parameter
     */
    @Query("from Faq where question like '%:toCompare%' and answer like '%:toCompare%'")
    List<Faq> findAllLikeQuestionOrAnswer(@Param("toCompare") String toCompare);


}
