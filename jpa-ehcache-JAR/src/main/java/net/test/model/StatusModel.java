package net.test.model;

import javax.persistence.*;

@Entity
@Table(name="status")
@org.hibernate.annotations.Cache(usage =
org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY
)
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
