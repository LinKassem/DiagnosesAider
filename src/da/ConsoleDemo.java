package da;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.jena.base.Sys;

public class ConsoleDemo {
    public static void main(String[] args) throws IOException { 
        
    	//-- Create a new DA object & populate its model.
    	DA da = new DA();
    	da.populateModel();
    	
    	//-- Create buffered reader
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to *Diagnosis Aider*");
        System.out.println("Please enter your ID");
        
        String patientId = br.readLine();
        
        // convert Id to URI
        String patientURI = da.convertIdToURI(da.getNS(), patientId);
        //--
        
        // check if the patient exists
        if(da.patientExists(patientURI)) {
        	System.out.println("Patient found. Please enter your symptoms separated by ',' then press enter when you are done.");
        } else {
        	// REGISTER HIM [MISSING Feature]
        	// This is a quick fix
        	System.out.println("Patient not found!");
        	br.close(); 
        }
        //--
        
        // read the symptoms
        String symptoms = br.readLine();
        System.out.println("Symptoms read.");
        System.out.println("Processing!");
        //--
        
        // put the symptoms in the appropriate format e.g. { "s1" "s2" }
        symptoms = da.convertToValuesFormat(symptoms);
        //--
        
        // get the possible diseases given the symptoms
        ArrayList<Disease> possibleDiseases = new ArrayList<Disease>();
        possibleDiseases = da.getPossibleDiseases(symptoms);
        //-
        
		// give a disease probability to each Disease in the array
		da.InitializeDiseaseProbability(possibleDiseases);
		//--
		
        // recursivly check the family of the patient & accordingly update the disease probability
        da.recursiveFamilyCheck(patientURI, possibleDiseases);

        // display the results
        System.out.println("===============");
        System.out.println("Results Ready: ");
        System.out.println("===============");
        da.printResults(possibleDiseases);
        //--

    }
}
