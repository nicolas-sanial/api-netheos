package sanial.netheos.demoapi.core.model;

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

    @Column(name = "name_tag", nullable = false)
    private String name;

    @Column(name = "description_tag", nullable = false)
    private String description;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    List<FaqTag> faqTagList;

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

    public List<FaqTag> getFaqTagList() {
        return faqTagList;
    }

    public void setFaqTagList(List<FaqTag> faqTagList) {
        this.faqTagList = faqTagList;
    }
}
