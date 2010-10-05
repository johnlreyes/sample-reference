package net.test.model;

import javax.persistence.*;

@Entity
@Table (name="info_dynamic")
public class InfoDynamicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @OneToOne
    @JoinColumn (name = "info_id")
	private InfoModel info;
    @Column (name = "info_code")
	private String code;
    @Column (name = "info_value")
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InfoModel getInfo() {
		return info;
	}

	public void setInfo(InfoModel info) {
		this.info = info;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}