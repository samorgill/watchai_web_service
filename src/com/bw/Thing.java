package com.bw;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Thing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String thing;
	private String state;
	private String serial;
	
	


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
