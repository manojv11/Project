package org.objects;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONXMLImpl {
	
	private static String separator = "\n";
	
	String createXMLJSONConverter(File sourceFile) throws Exception 
	{
		Object parseObj = null;
		String xmlData = "";
		try {
			parseObj = new JSONParser().parse(new FileReader(sourceFile));
		} 
		catch (Exception e) {
			throw new Exception("JSON file format is not valid");
		}
		if (parseObj instanceof JSONObject) {
			JSONObject jsonObj = (JSONObject) parseObj;
			if (jsonObj != null) {
				xmlData = "<?xml version='1.0' encoding='UTF-8'?>" + separator + "<object>" + separator;
				String appendData = processJSON2XML(jsonObj);
				xmlData += appendData + "</object>";
			}
		}
		return xmlData;
	}
	
	private String processJSON2XML(JSONObject jsonObj) throws Exception 
	{
		String objElement = "";
		for (Object objKey : jsonObj.keySet()) {
			Object objValue = jsonObj.get(objKey);
			objElement += createProcess2XML(objKey, objValue, '1'); //1 For identify JSON object
		}
		return objElement;
	}
	
	@SuppressWarnings("unchecked")
	private String createProcess2XML(Object objKey, Object objValue, char identity)throws Exception 
	{
		String objElement = "";
	
		if (objValue instanceof JSONObject) {
			if (identity == '1') { //For JSON object
				objElement += "<object name='" + objKey + "'>" + separator;
				objValue = processJSON2XML((JSONObject) objValue);
				objElement += objValue + "</object>" + separator;
			} else { //For JSON Array
				objElement += "<object>" + separator;
				objValue = processJSON2XML((JSONObject) objValue);
				objElement += objValue + "</object>" + separator;
			}
		} 
		else if (objValue instanceof Boolean) {
			if (identity == '1') { //For JSON object
				objElement += "<boolean name='" + objKey + "'>" + objValue + "</boolean>" + separator;
			} else { //For JSON Array
				objElement += "<boolean>" + objValue + "</boolean>" + separator;
			}
		} 
		else if (objValue instanceof String) {
			if (identity == '1') { //For JSON object
				objElement += "<string name='" + objKey + "'>" + objValue + "</string>" + separator;
			} else { //For JSON Array
				objElement += "<string>" + objValue + "</string>" + separator;
			}
		} 
		else if (objValue instanceof Number) {
			if (identity == '1') { //For JSON object
				objElement += "<number name='" + objKey + "'>" + objValue + "</number>" + separator;
			} else { //For JSON Array
				objElement += "<number>" + objValue + "</number>" + separator;
			}
		} 
		else if (objValue instanceof JSONArray) {
			objElement += "<array name='" + objKey + "'>" + separator;
			Iterator<String> itrJsonArray = ((JSONArray) objValue).iterator();
			
			while(itrJsonArray.hasNext()) {
				objElement += createProcess2XML(objKey, itrJsonArray.next(), '2'); //2 For identify JSON Array
			}
			
			objElement += "</array>" + separator;
		} 
		else {
			objElement += "<null name='" + objKey + "'/>" + separator;
		}
		return objElement;
	}
}
