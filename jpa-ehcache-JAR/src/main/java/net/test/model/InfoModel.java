/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.test.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="info")
public class InfoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn (name = "status_id")
    private StatusModel status;
    @OneToMany
    @JoinColumn (name="info_id", referencedColumnName = "id")
    private List<InfoDynamicModel> infoDynamicList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InfoDynamicModel> getInfoDynamicList() {
        return infoDynamicList;
    }

    public void setInfoDynamicList(List<InfoDynamicModel> infoDynamicList) {
        this.infoDynamicList = infoDynamicList;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }
}
