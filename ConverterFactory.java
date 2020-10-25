package org.objects;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ConverterFactory implements XMLJSONConverterI 
{
	public void convertJSONtoXML(String sourcePath, String destinationPath) 
	{
		File sourceFile = new File(sourcePath);
		File destinationFile = new File(destinationPath);
		String xmlData ="";
		try {
			if (sourceFile.exists()){
				JSONXMLImpl jsonToXml= new JSONXMLImpl();
				xmlData = jsonToXml.createXMLJSONConverter(sourceFile);
			}
			else{
				throw new FileNotFoundException();
			}
			
			validateXMLData(xmlData); 
			
			FileWriter xmlWriter = new FileWriter(destinationFile);
			xmlWriter.write(xmlData);
			xmlWriter.close();
			
			System.out.println("--- Process of JSON to XML convert has been Completed -- please look at DestinationFile ****  " +destinationFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateXMLData(String xmlData) throws Exception { // XML Data Validation
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			db.parse(new ByteArrayInputStream(xmlData.getBytes("UTF-8")));
		} 
		catch (Exception e) {
			throw new Exception("XML data is not valid");
		}
	}
	public static void main(String[] args) throws Exception 
	{
		XMLJSONConverterI obj = new ConverterFactory();
		obj.convertJSONtoXML(args[0] , args[1]);
	}	
}
