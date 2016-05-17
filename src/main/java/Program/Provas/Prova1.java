/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Provas;

import Program.Util.OpenFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author klockner
 * Implements Prova1
 */
public class Prova1 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Prova1(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
    //Finds especific element in the nodes
    public String init() {
        StringBuilder sb = new StringBuilder();
        //Get produto-de-moda nodes
        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
        //Visit all the nodes
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if (element.getElementsByTagName("categoria").item(0).getTextContent().equalsIgnoreCase("vestido")) {
                    System.out.println("Classificação: " + element.getElementsByTagName("classificacao").item(0).getTextContent());
                    sb.append("Classificação: ").append(element.getElementsByTagName("classificacao").item(0).getTextContent());
                }
            }
        }
        return sb.toString();
    }
}
