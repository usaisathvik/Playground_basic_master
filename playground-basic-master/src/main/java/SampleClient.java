import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class SampleClient {

    public static void main(String[] theArgs) throws IOException{

       String fileName = "lastname.txt";
       SampleClient sampleClient = new SampleClient();
       // there nothing mentioned how to disable searching data from cache. I have looped three time and logging average response time for each reqest.
       for(int i =0; i<3; i++)
           System.out.println(sampleClient.readFile(fileName));
        // Search for Patient resources
        
    }
    
     String readFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();        
        File file = new File(classLoader.getResource(fileName).getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
       // client.registerInterceptor(new LoggingInterceptor(false));
        client.registerInterceptor(new RequestInterceptor());
        
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                
            	Bundle response = client
                        .search()
                        .forResource("Patient")
                        .where(Patient.FAMILY.matches().value(line))
                        .returnBundle(Bundle.class)
                        .execute();                
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}
