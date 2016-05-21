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
 */
public class Prova6 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Prova6(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
    //Finds especific element in the nodes
    public String init(String parameterSize) {
        int countFinder = 0;
        StringBuilder sb = new StringBuilder();
        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if (element.getElementsByTagName("categoria").item(0).getTextContent().equalsIgnoreCase("blusa")) {
                    for (int size = 0; size < element.getElementsByTagName("tamanho").getLength(); size++) {
                        if (element.getElementsByTagName("tamanho").item(size).getTextContent().equalsIgnoreCase(parameterSize)) {
                            System.out.println("\n" + element.getElementsByTagName("sku").item(0).getTextContent());
                            sb.append("\n").append(element.getElementsByTagName("sku").item(0).getTextContent());
                            countFinder++;
                        }
                    }
                }
            }
        }
        if (countFinder > 0) {
            return sb.toString();
        } else {
            sb.append("\nNada foi encontrado!");
            return sb.toString();
        }
    }
}