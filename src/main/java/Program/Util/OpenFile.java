/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Util;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author klockner
 * This class is responsible for work with the files
 */
public class OpenFile {
    
    //This method returns the xml like a Document
    public Document createDoc(String xmlFileName) {
        try {  
            File xmlFile = new File(xmlFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            //optional, but recommended
            doc.getDocumentElement().normalize();
            return doc;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro ao abrir o documento xml: " + e.getMessage());
            return null;
        }
    }
}
