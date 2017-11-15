# DiagnosisAider
This project was implemented as part of an elective course [CSEN 915 Semi-Structured Data and the Web].


## Introduction
The term ‘semantic web’ was first coined by [Tim Berners Lee](https://www.w3.org/People/Berners-Lee/) in 2011, were he had the vision that data on the internet is very valuable and with unlimited potential. Tim Berners Lee proposed that if the data was written in a standard format across the web and with relations stated between the data, the [linked-data format](http://tomheath.com/papers/bizer-heath-berners-lee-ijswis-linked-data.pdf), this will enable scientists from different backgrounds to collaborate together, make discoveries and detect patterns in the data. Thus empowering scientists to solve major challenges; like curing cancer or Alzheimer’s. He also suggested that this linked-data standard format will make the data more understandable for the machines as well. Thus, opening new roads and possibilities in the fields of artificial intelligence and machine learning.
Currently there are huge efforts towards making people, organizations and governments publish their data in the linked-data standard format. (DBPedia)[http://wiki.dbpedia.org/] is one of the leading projects that aim to collect data from the web and put them in a the linked-data structured and standardized format. There are several [semantic web applications](http://www.cambridgesemantics.com/semantic-university/example-semantic-web-applications) that make use of linked-data. These semantic web applications are being used by companies in various fields, such as media, medical research and even by oil companies.

## The idea behind DiagnosisAider
The aim of ‘DiagnosisAider’ application is to help doctors during the phase of diagnosis of patients seeking medical care. As it will empower doctors with the ability to use and investigate linked data between the patient’s family medical history, patient’s current disease symptoms along with a data set of medical diseases and their associated information. Thus, allowing the doctors to diagnose diseases correctly and in a shorter period. Ultimately affecting the health of patients and speeding up their recovery.
During the implementation of ‘DiagnosisAider’, we have made the assumption that it will be used in a health care system that stores the data of the patient’s family relations and medical history in a standard linked-data format (or ontologically based data format).

## Scenario
The scenario starts with a patient suffering from particular symptoms coming to the doctor seeking for a medical diagnosis.
The doctor will take the patient’s medical id and enter it in the system. 
The system will check if there are records for the patient or not. If the patient is found, the doctor is asked to enter the symptoms that the patient has .
The system will output a result set of all the possible diseases that the patient might be suffering from.
The system assumes that the probability that a patient is suffering from one of the diseases in the result set is [1/ (number of diseases in the result set)].
Then for every disease in this set, the family history of the patient will be checked to see if there is an existing family member who is already suffering from the same disease. If a family member was found to be suffering from the same disease, the probability of the patient likelihood to be suffering from the same disease is incremented.
After scanning the data of all the family members of the patient, the system will output the results to the doctor in a clear and readable format. Thus, allowing the doctor to base his diagnosis not only on the patient’s symptoms but taking consideration of the family history as well.

## Console demo
The current demo is run through the console and is with only a basic user interface.
For this demo we used manually entered data set to represent a family and the diseases they have. The data set along with the relations and properties is explained in more details in the report provided in the repository.
```
Welcome to Diagnosis Aider
Please enter your ID
>C11
Patient found. Please enter your symptoms separated by ','
then press enter when you are done.
>s1, s7, s12, s18, headache, bone-ache, back pain
Symptoms read.
Processing!

=============
Results Ready:
=============
Disease Name: d1
Disease URI: <http://purl.bioontology.org/ontology/DOID/D1>
Probability of having this disease according to inheritance
factor: 1.25
Family members/ancestors with the same disease:
    <http://www.met.guc.edu.eg/#GFF1>
----
Disease Name: d3
Disease URI: <http://purl.bioontology.org/ontology/DOID/D3>
Probability of having this disease according to inheritance
factor: 1.25
Family members/ancestors with the same disease:
    <http://www.met.guc.edu.eg/#GFM1>
----
Disease Name: d4
Disease URI: <http://purl.bioontology.org/ontology/DOID/D4>
Probability of having this disease according to inheritance
factor: 1.25
Family members/ancestors with the same disease:
    <http://www.met.guc.edu.eg/#GMM1>
----
Disease Name: d6
Disease URI: <http://purl.bioontology.org/ontology/DOID/D6>
Probability of having this disease according to inheritance
factor: 1.25
Family members/ancestors with the same disease:
    <http://www.met.guc.edu.eg/#M1>
----
```
 
## Technologies used
In this section we will illustrate the libraries and ontologies used to build ‘Diagnosis Aider’.
Several ontologies were used:
1. [FOAF](http://www.foaf-project.org/) ontology was used to represent family individuals and their properties.
2. The [RELATIONSHIP](http://vocab.org/relationship/) ontology was used to model the relation between family members, e.g. John is fatherOf Mary. The RELATIONSHIP ontology is built on top of the foaf ontology, in other words it compliments the FOAF ontology.
3. The [Human Disease](https://bioportal.bioontology.org/ontologies/DOID) ontology was used to represent diseases and their properties.
APIs
4. [Apache Jena](https://jena.apache.org/) java based API was used to interact with, extract information from and read the above mentioned ontologies. Apache Jena API provides the possibility to build our own model (a container like structure) and fill this model with the ontologies, properties and statements that we want and build. This model can then be queried and altered using the [SPARQL](https://www.w3.org/TR/rdf-sparql-query/) query engine.
