*********************************** JSON To XML Conversion ************************************************

1) we have 2 classes and 1 interface, which is available in the src//org//objects package

2) The interface XMLJSONConverterI has one method named convertJSONtoXML, used to pass source path and destination path.

3) The class ConverterFactory used to give the implementation details for the method convertJSONtoXML.

4) The class JSONXMLImpl used to process the JSON data and return xml data.

5) We have used json-simple-1.1.jar for parse JSON to XML. 
   (Refer the url to download json-simple-1.1.jar file http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm).
  
6) We have build.xml file, which is used to compile and create executable jar file.

7) Once jar is created by running build.xml, we can run the jar in the command prompt by passing the source file and destination file 
   as arguments.
   
8) After execution of the project, Xml file will be generated in the mentioned destination path and file name.

9) The sample tested file is available under sample folder.


************************************************* End ******************************************************


