package uk.ac.mmu.watchai.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;


/**
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 */

@Entity
public class Thing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String thing;
	private String state;
	private String serial;
	
	//Constructor
	public Thing (String tName, String st, String sl){
		
		setThing(tName);
		setState(st);
		setSerial(sl);
	}		
	
	public String getThingName() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String toString(){
		String output = thing + " " + state;
		return output;
	}
	
}
