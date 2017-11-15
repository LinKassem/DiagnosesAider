package da;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.XSD;

public class DA {

    //----------+----------+ GLOBAL VARIABLES +----------+----------+

	// create model
    OntModel m = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM);
 	
    // namespaces
    String SOURCE = "http://www.met.guc.edu.eg/";
    String NS = SOURCE + "#";
    
    String foafNS = "http://xmlns.com/foaf/0.1/";
    
    String local_relationNS = "//users//lin/desktop//rel.rdf"; // load the ontology
    String relNS = "http://purl.org/vocab/relationship/";
    
    String local_diseaseNS = "//users//lin/desktop//diseaseOntology.xrdf"; // load the ontology
    String diseaseNS ="http://purl.bioontology.org/ontology/DOID/";
    
    //----------+----------+ CONSTRUCTORS +----------+----------+

	// empty constructor
	public DA() {}
	
    //----------+----------+ METHODS +----------+----------+

	//-- Setters and getters
	public OntModel getM() {
		return m;
	}

	public void setM(OntModel m) {
		this.m = m;
	}
	
	public String getNS() {
		return NS;
	}

	public void setNS(String nS) {
		NS = nS;
	}

	public String getFoafNS() {
		return foafNS;
	}

	public void setFoafNS(String foafNS) {
		this.foafNS = foafNS;
	}

	public String getRelNS() {
		return relNS;
	}

	public void setRelNS(String relNS) {
		this.relNS = relNS;
	}

	public String getDiseaseNS() {
		return diseaseNS;
	}

	public void setDiseaseNS(String diseaseNS) {
		this.diseaseNS = diseaseNS;
	}
	//--

	/**
	 * Populates the model with the appropriate individuals, properties & statements.
	 */
	public void populateModel() {
		
        // add the prefixes to the model 
        // 	so that while printing we can see a prefix rather than a full namespace
        m.setNsPrefix("relation", relNS);
        m.setNsPrefix("disease", diseaseNS);
        
        // Load defined ontologies
        m.read(foafNS); // foaf ontology 
        m.read(local_relationNS); // relation ontology
        m.read(local_diseaseNS); // human disease ontology
        
        // Create OntClasses
        OntClass person  = m.getOntClass(foafNS + "Person");
        OntClass disease = m.getOntClass(local_diseaseNS + "disease");
        
        // Create individuals
        // - diseases
        Individual d1 = m.createIndividual(diseaseNS+"D1", disease);
        Individual d2 = m.createIndividual(diseaseNS+"D2", disease);
        Individual d3 = m.createIndividual(diseaseNS+"D3", disease);
        Individual d4 = m.createIndividual(diseaseNS+"D4", disease);
        Individual d5 = m.createIndividual(diseaseNS+"D5", disease);
        Individual d6 = m.createIndividual(diseaseNS+"D6", disease);
        
        // -family 1
        Individual gff1 = m.createIndividual(NS+"GFF1", person);
        Individual gmf1 = m.createIndividual(NS+"GMF1", person);
        Individual gfm1 = m.createIndividual(NS+"GFM1", person);
        Individual gmm1 = m.createIndividual(NS+"GMM1", person);
 
        Individual f1 = m.createIndividual(NS+"F1", person);
        Individual m1 = m.createIndividual(NS+"M1", person);
        
        Individual c11 = m.createIndividual(NS+"C11", person);
        Individual c21 = m.createIndividual(NS+"C21", person);
        
        
        // Create properties
        Property spouseOf  = m.getProperty(relNS + "spouseOf"); // symmetric property
        Property siblingOf  = m.getProperty(relNS + "siblingOf");
        
        Property grandchildOf = m.getProperty(relNS + "grandchildOf"); // symmetric property
        Property grandparentOf = m.getProperty(relNS + "grandparentOf");
        
        Property childOf  = m.getProperty(relNS + "childOf");
        Property parentOf = m.getProperty(relNS + "parentOf");
        
        Property hasName = m.getProperty(foafNS, "firstName");
        Property gender = m.getProperty(foafNS + "gender");
        
        Property hasDiseaseSymptom = m.getProperty(diseaseNS, "has_symptom"); // symptoms related to the disease
        Property hasDiseaseName = m.getProperty(diseaseNS, "label"); // name of the disease
        
        Property hasPatientSymptom = m.createProperty(foafNS, "has_patient_symptom"); // symptom related to the patient/person
        Property hasDisease = m.createProperty(foafNS, "has_disease"); // disease that the person suffers from 
        
        // Bind properties to classes -create RDF statement or relation-
        //----------+----------
        // - Diseases -
        m.add(d1, hasDiseaseName, "d1");
        m.add(d1, hasDiseaseSymptom, "s1");
        m.add(d1, hasDiseaseSymptom, "s2");
        m.add(d1, hasDiseaseSymptom, "s3");

        m.add(d2, hasDiseaseName, "d2");
        m.add(d2, hasDiseaseSymptom, "s4");
        m.add(d2, hasDiseaseSymptom, "s5");
        m.add(d2, hasDiseaseSymptom, "s6");

        m.add(d3, hasDiseaseName, "d3");
        m.add(d3, hasDiseaseSymptom, "s7");
        m.add(d3, hasDiseaseSymptom, "s8");
        m.add(d3, hasDiseaseSymptom, "s9");

        m.add(d4, hasDiseaseName, "d4");
        m.add(d4, hasDiseaseSymptom, "s10");
        m.add(d4, hasDiseaseSymptom, "s11");
        m.add(d4, hasDiseaseSymptom, "s12");

        m.add(d5, hasDiseaseName, "d5");
        m.add(d5, hasDiseaseSymptom, "s13");
        m.add(d5, hasDiseaseSymptom, "s14");
        m.add(d5, hasDiseaseSymptom, "s15");

        m.add(d6, hasDiseaseName, "d6");
        m.add(d6, hasDiseaseSymptom, "s16");
        m.add(d6, hasDiseaseSymptom, "s17");
        m.add(d6, hasDiseaseSymptom, "s18");

        //----------+----------+
        // - Family 1 -
        // GFF1
        m.add(gff1, spouseOf, gmf1);
        m.add(gff1, parentOf, f1);
        m.add(gff1, grandparentOf, c11);
        m.add(gff1, grandparentOf, c21);
        m.add(gff1, hasDisease, d1);
        m.add(gff1, gender, "male");
        
        //GMF1
        m.add(gmf1, spouseOf, gff1);
        m.add(gmf1, parentOf, f1);
        m.add(gmf1, grandparentOf, c11);
        m.add(gmf1, grandparentOf, c21);
        m.add(gmf1, hasDisease, d2);
        m.add(gmf1, hasDisease, d1);
        m.add(gmf1, gender, "female");
        
        //GFM1
        m.add(gfm1, spouseOf, gmm1);
        m.add(gfm1, parentOf, m1);
        m.add(gfm1, grandparentOf, c11);
        m.add(gfm1, grandparentOf, c21);
        m.add(gfm1, hasDisease, d3);
        m.add(gfm1, gender, "male");
        
        //GMM1
        m.add(gmm1, spouseOf, gfm1);
        m.add(gmm1, parentOf, m1);
        m.add(gmm1, grandparentOf, c11);
        m.add(gmm1, grandparentOf, c21);
        m.add(gmm1, hasDisease, d4);
        m.add(gmm1, gender, "female");

        
        //F1
        m.add(f1, spouseOf, m1);
        m.add(f1, parentOf, c11);
        m.add(f1, parentOf, c21);
        m.add(f1, hasDisease, d5);
        m.add(f1, gender, "male");
        
        //M1
        m.add(m1, spouseOf, f1);
        m.add(m1, parentOf, c11);
        m.add(m1, parentOf, c21);
        m.add(m1, hasDisease, d6);
        m.add(m1, gender, "female");
        
        //C11
        m.add(c11, siblingOf, c21);
        m.add(c11, childOf, f1);
        m.add(c11, childOf, m1);
        m.add(c11, grandchildOf, gff1);
        m.add(c11, grandchildOf, gmf1);
        m.add(c11, grandchildOf, gfm1);
        m.add(c11, grandchildOf, gmm1);
        m.add(c11, gender, "male");
        
        //C21
        m.add(c21, siblingOf, c21);
        m.add(c21, childOf, f1);
        m.add(c21, childOf, m1);
        m.add(c21, grandchildOf, gff1);
        m.add(c21, grandchildOf, gmf1);
        m.add(c21, grandchildOf, gfm1);
        m.add(c21, grandchildOf, gmm1);
        m.add(c21, gender, "male");
        
	}
		
	/**
	 * Prints the RDF representation of the model to an output.rdf file
	 * @throws IOException
	 */
	public void printRdfToFile() throws IOException {
        String outputFileName = "//Users//lin//Desktop//output.rdf";
        FileWriter out = new FileWriter( outputFileName );
        try {
            m.write( out, "Turtle" );
        }
        finally {
           try {
               out.close();
           }
           catch (IOException closeException) {
               System.out.println(closeException);
           }
        }
	}
	
	/**
	 * converts an id to a URI.
	 * @param ns - String: the namespace of the uri
	 * @param id - String: the id to append to the URI
	 * @return
	 */
	public String convertIdToURI( String ns, String id) {
		return "<" + ns + id + ">";
	}
	
	/**
	 * Checks if the input patient URI exists in the model or not.
	 * @param patientURI - String
	 * @return boolean
	 */
	public  boolean patientExists(String patientURI) {
		boolean result;
		
        String queryString =
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
        		"PREFIX disease: <http://purl.bioontology.org/ontology/DOID/> " +
        		"PREFIX relation: <http://purl.org/vocab/relationship/> " +	
        		"ASK { " +
        		patientURI + " a foaf:Person" +
        		"}";

 
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
        	result = qexec.execAsk();        	
        } finally {
        	qexec.close();
        }
		
        return result;
	}
    
	/**
	 * Converts a comma separated input string to the format of sparql 'VALUES'
	 * 	which is { "value1" "value2"}.
	 * @param symptoms - String: in the format "s1, s2, s3"
	 * @return String - String: { "value1" "value2"}
	 */
	public String convertToValuesFormat(String symptoms) {
		String[] parts = symptoms.split(","); //split at each comma
		String result = "";
		
		for (int i = 0; i < parts.length; i++) {
			result = result +"\"" + parts[i].trim() +"\" ";
		}
		
		return "{ " + result + " }";
	}

	/**
	 * Given some symptoms, all diseases with these symptoms are found and returned.
	 * @param symptoms - String
	 * @return ArrayList<Disease>
	 */
	public ArrayList<Disease> getPossibleDiseases(String symptoms) {
		
		ArrayList<Disease> possibleDiseases = new ArrayList<Disease>();
		ArrayList<String> tempStorage = new ArrayList<String>();
		
        String queryString =
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
        		"PREFIX disease: <http://purl.bioontology.org/ontology/DOID/> " +
        		"PREFIX relation: <http://purl.org/vocab/relationship/> " +	
        		"SELECT * WHERE { " +
        		"VALUES ?symptoms " + symptoms + " " + 
        		"?disease disease:has_symptom ?symptoms ." +
        		"?disease disease:label ?label ." +
        		"}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
        	ResultSet results = qexec.execSelect();
            while (results.hasNext())
            {
              QuerySolution soln = results.nextSolution() ;
             
              // To avoid duplicate results
              if(!tempStorage.contains( (soln.get("disease")).toString() ) ) {
            	  tempStorage.add( (soln.get("disease")).toString() );
            	  Disease d = new Disease("<"+(soln.get("disease")).toString()+">", (soln.get("label")).toString());
            	  possibleDiseases.add(d);
              }

            }
        } finally {
        	qexec.close();
        }
    	return possibleDiseases;
	}
	
	/**
	 * Given an ArrayList of Diseases, the probability of the every disease in this array list
	 * 	is set to 1/the number of diseases in the array list.
	 * @param diseases - ArrayList<Disease>
	 */
	public void InitializeDiseaseProbability(ArrayList<Disease> diseases) {

		for (int i = 0; i < diseases.size(); i++) {
			diseases.get(i).setProbability((float) 1 / diseases.size());
		}
		
	}
	
	/**
	 * Recursivly checks the parents of the patient URI and checks if they have any
	 * 	of the diseases in the possibleDiseases array list.
	 * 	Accordingly every disease in the possibleDiseases arrayList is updated.
	 * @param patientURI - String
	 * @param possibleDiseases - ArrayList<Disease>
	 */
	public void recursiveFamilyCheck(String patientURI, ArrayList<Disease> possibleDiseases) {
        String parentURI;
		
		String queryString =
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
        		"PREFIX disease: <http://purl.bioontology.org/ontology/DOID/> " +
        		"PREFIX relation: <http://purl.org/vocab/relationship/> " +	
        		"SELECT * WHERE { " +
        		"?parent relation:parentOf " + patientURI +
        		"}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
        	ResultSet results = qexec.execSelect();
        	
            while (results.hasNext())
            {
              QuerySolution soln = results.nextSolution() ;
              parentURI = "<" + soln.get("parent") + ">";
              //check if the parent has one of the diseases in the arrayList & accordingly update the arrayList
              hasDisease(parentURI, possibleDiseases);
              recursiveFamilyCheck(parentURI, possibleDiseases);
            }
        } finally {
        	qexec.close();
        }
		
	}
	
	/**
	 * Given a person URI and an array list of diseases, the method checks if this person has
	 * 	this disease or not.
	 * 	Accordingly every disease in the possibleDiseases arrayList is updated.
	 * @param personURI - String
	 * @param diseases - ArrayList<Disease>
	 */
	public void hasDisease(String personURI, ArrayList<Disease> diseases) {
		boolean result;
		for (int i = 0; i < diseases.size(); i++) {
			result = false;
	        
			String queryString =
	        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
	        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
	        		"PREFIX disease: <http://purl.bioontology.org/ontology/DOID/> " +
	        		"PREFIX relation: <http://purl.org/vocab/relationship/> " +	
	        		"ASK { " +
	        		personURI + " foaf:has_disease " + diseases.get(i).getDiseaseURI() +
	        		"}";

	 
	        Query query = QueryFactory.create(queryString);
	        QueryExecution qexec = QueryExecutionFactory.create(query, m);
	        try {
	        	result = qexec.execAsk();
	        	if(result) {
	        		diseases.get(i).setProbability(1+diseases.get(i).getProbability());
	        		diseases.get(i).getPeople().add(personURI);
	        	}
	        } finally {
	        	qexec.close();
	        }
		}
	}
	
	/**
	 * Loops over all the Disease objects in the possibleDiseases array and prints
	 * 	the relative properties & attributes of this disease.
	 * @param possibleDiseases - ArrayList<Disease>
	 */
	public void printResults(ArrayList<Disease> possibleDiseases){
        Disease d;
        for (int i = 0; i < possibleDiseases.size(); i++) {
        	d = possibleDiseases.get(i);
			System.out.println("Disease Name: " + d.getName());
			System.out.println("Disease URI: " + d.getDiseaseURI());
			System.out.println("Probability of having this disease according to inheritance factor: " + d.getProbability());
			System.out.println("Family members/ancestors with the same disease: ");
			if(d.getPeople().size() == 0) {
				System.out.println("none");
			} else {
				for (int j = 0; j < d.getPeople().size(); j++) {
					System.out.println("    " + d.getPeople().get(j));
				}
			}
			System.out.println("--------------------------------------------------");
			
		}
	}
	
	public void addPatientToSystem(){
		
	}
	
	//----------+----------+ MAIN METHOD +----------+----------+
	public static void main (String [] args) throws IOException {
		DA da = new DA();
		da.populateModel();
//		da.printRdfToFile();//
//		System.out.println(da.convertToValuesFormat("s1,     d1,   s3"));
		
	}

 
}
