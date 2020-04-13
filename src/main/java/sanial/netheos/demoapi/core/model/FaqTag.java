package sanial.netheos.demoapi.core.model;

import javax.persistence.*;

@Entity
@Table(name = "faq_tag")
public class FaqTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_faq", nullable = false)
    private Faq faq;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_tag", nullable = false)
    private Tag tag;
}
