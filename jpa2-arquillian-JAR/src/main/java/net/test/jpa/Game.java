package net.test.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Game implements Serializable {

	private Long id;
	private String title;

	public Game() {
	}

	public Game(String title) {
		this.title = title;
	}

	@Id 
	@GeneratedValue
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	@NotNull
	@Size (min=3, max=50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Game@" + hashCode() + "[id = " + id + "; title = " + title + "]";
	}
}
