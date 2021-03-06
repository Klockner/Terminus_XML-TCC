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
 Implements Proof2
 */
public class Proof2 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Proof2(String xmlFileName) {
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
                //Find the element 1200827
                if (element.getElementsByTagName("sku").item(0).getTextContent().equals("1200827")) {
                    if ((element.getElementsByTagName("categoria").item(0).getTextContent().equalsIgnoreCase("calça")) &&
                            (element.getElementsByTagName("plus-size").item(0).getTextContent().equalsIgnoreCase("sim"))) {
                        System.out.println("SKU: " + element.getElementsByTagName("sku").item(0).getTextContent());
                        System.out.println("PlusSize: " + element.getElementsByTagName("plus-size").item(0).getTextContent());
                        System.out.println("Categoria: " + element.getElementsByTagName("categoria").item(0).getTextContent());
                        sb.append("SKU: ").append(element.getElementsByTagName("sku").item(0).getTextContent());
                        sb.append("\nPlusSize: ").append(element.getElementsByTagName("plus-size").item(0).getTextContent());
                        sb.append("\nCategoria: ").append(element.getElementsByTagName("categoria").item(0).getTextContent());
                    }
                }
            }
        }
        return sb.toString();
    }
}
