package sanial.netheos.demoapi.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Tags entity : defining tag objects, that can be associated with a specific entity faq
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name_tag", nullable = false, unique = true)
    private String name;

    @Column(name = "description_tag")
    private String description;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    List<FaqTag> faqTagList;

    /* Default constructor */
    public Tag(){

    }

    public Tag(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public List<FaqTag> getFaqTagList() {
        return faqTagList;
    }

    public void setFaqTagList(List<FaqTag> faqTagList) {
        this.faqTagList = faqTagList;
    }
}
