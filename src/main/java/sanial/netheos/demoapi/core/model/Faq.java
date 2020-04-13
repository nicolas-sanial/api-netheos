package sanial.netheos.demoapi.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Faq entity : Defining question/answers objects, that can have tags associated with
 */
@Entity
@Table(name = "faq", indexes = {
        @Index(name = "question_idx", columnList = "question"),
        @Index(name = "answer_idx", columnList = "answer")
})
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "question", nullable = false, unique = true)
    private String question;

    @Column(name = "answer", nullable = false, unique = true)
    private String answer;

    @OneToMany(mappedBy = "faq", cascade = CascadeType.ALL)
    List<FaqTag> faqTagList;

    /* Default constructor */
    public Faq(){

    }

    public Faq(String question, String answer){
        this.question = question;
        this.answer= answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonIgnore
    public List<FaqTag> getFaqTagList() {
        return faqTagList;
    }

    public void setFaqTagList(List<FaqTag> faqTagList) {
        this.faqTagList = faqTagList;
    }
}
