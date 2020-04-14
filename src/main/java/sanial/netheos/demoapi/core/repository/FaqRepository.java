package sanial.netheos.demoapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanial.netheos.demoapi.core.model.Faq;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

    /**
     * Catch all Faq object where the searched String is contained in question or answer attribute.
     * Using like %% break the table index, maybe better to use Elasticsearch in this case, depending on DB volume.
     * @param toCompare the string that have to be searched in question or answers
     * @return the list of faq that contain the string in parameter
     */
    @Query("from Faq where answer like CONCAT('%',:toCompare,'%') or question like CONCAT('%',:toCompare,'%')")
    List<Faq> findAllLikeQuestionOrAnswer(@Param("toCompare") String toCompare);

    Optional<Faq> findByQuestion(String question);

    Optional<Faq> findByAnswer(String answer);
}
