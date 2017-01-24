package fernandoalvarez.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Thing {
	
	@Id
	@Column(name = "handle")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number")
	private int randomNumber;
	
	public void init (){
		
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
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
}
