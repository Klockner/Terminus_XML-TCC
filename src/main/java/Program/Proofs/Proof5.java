/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Proofs;

import Program.Util.OpenFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author klockner
 */
public class Proof5 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Proof5(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
    //Finds especific element in the nodes
    public String init() {
        StringBuilder sb = new StringBuilder();
        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if ((element.getElementsByTagName("categoria").item(0).getTextContent().equalsIgnoreCase("calça")) &&
                        (element.getElementsByTagName("plus-size").item(0).getTextContent().equalsIgnoreCase("sim"))) {
                    System.out.println("SKU: " + element.getElementsByTagName("sku").item(0).getTextContent());
                    sb.append("\n").append(element.getElementsByTagName("sku").item(0).getTextContent());
                }
            }
        }
        return sb.toString();
    }
}