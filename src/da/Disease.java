package da;

import java.util.ArrayList;

import org.apache.jena.base.Sys;

public class Disease {
	
	String name;
	String diseaseURI;
	float probability;
	ArrayList<String> people;
	
	public Disease(String diseaseURI, String name) {
		this.name = name;
		this.diseaseURI = diseaseURI;
		this.probability = 1;
		this.people = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiseaseURI() {
		return diseaseURI;
	}

	public void setDiseaseURI(String diseaseURI) {
		this.diseaseURI = diseaseURI;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	public ArrayList<String> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<String> people) {
		this.people = people;
	}
	
	public void printDisease() {
		System.out.println("URI: " + this.getDiseaseURI());
		System.out.println("prob: " + this.getProbability());
		System.out.println("people: " + this.getPeople().toString());
		System.out.println("-------");
	}
	
	
}
