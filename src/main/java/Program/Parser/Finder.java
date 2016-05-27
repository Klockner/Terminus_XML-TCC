/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Parser;

import Program.Util.OpenFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author klockner
 */
public class Finder {
    private final OpenFile openFile;
    private final Document doc;
    private boolean found = false;
    private StringBuilder sbResult;
    private String nodeLastChild;
    private String idProduct;
    private List<String> listIdProducts;
    private int countRestrictions;
    
    //Constructor receive the doc
    public Finder(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
    public String individualSatisfiesRestriction(String individualId, String tagId, String tagRestriction1, 
            String tagRestrictionValue1, String tagRestriction2, String tagRestrictionValue2) {
        Node nodeRoot = doc.getFirstChild();
        NodeList nodeList = nodeRoot.getChildNodes();
        Node node1;
        sbResult = new StringBuilder();
        countRestrictions = 0;

        //Get the tag reference of the product
        node1 = nodeList.item(1);
        
        //Call the method for parse the xml recursively
        recursiveParseForIndividualSatisfiesRestriction(nodeList, node1, individualId, tagId, tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2);
        
        if (countRestrictions > 1) {
            sbResult.append("O indivíduo ").append(individualId).append(" satisfaz as condições!");
        } else {
            sbResult.append("O indivíduo ").append(individualId).append(" NÃO satisfaz as condições!");
        }
        return sbResult.toString();
    }
    
    public void recursiveParseForIndividualSatisfiesRestriction(NodeList nodeList,
            Node nodeNewProduct, String individualId, String tagId, String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2) {
        Node node;
        
        for (int i=0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                //if finds the id
                if (node.getNodeName().equalsIgnoreCase(tagId)) {
                    if (node.getTextContent().equalsIgnoreCase(individualId)) {
                        found = true;
                    } else {
                        found = false;
                    }
                }
                if (found) {
                    //If the element has children
                    if (node.getChildNodes().getLength() > 1) {
                        //The tag that represents new element
                        if (nodeNewProduct.getNodeName().equals(node.getNodeName())) {
                            return;
                        }
                        recursiveParseForIndividualSatisfiesRestriction(node.getChildNodes(), nodeNewProduct, individualId, tagId, tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2);
                    } else {
                        //If does not have children and have value
                        //it is like child, but it represents the value
                        if (node.getFirstChild() != null) {
                            if ((node.getNodeName().equalsIgnoreCase(tagRestriction1)) &&
                                    (node.getTextContent().equalsIgnoreCase(tagRestrictionValue1))) {
                                sbResult.append("Indivíduo identificado por ").append(individualId).append(" satisfaz restrição 1");
                                sbResult.append("\n").append(tagRestriction1).append(": ").append(tagRestrictionValue1).append("\n\n");
                                countRestrictions++;
                            }
                            if ((node.getNodeName().equalsIgnoreCase(tagRestriction2)) &&
                                    (node.getTextContent().equalsIgnoreCase(tagRestrictionValue2))) {
                                sbResult.append("Indivíduo identificado por ").append(individualId).append(" satisfaz restrição 2");
                                sbResult.append("\n").append(tagRestriction2).append(": ").append(tagRestrictionValue2).append("\n\n");
                                countRestrictions++;
                            }
                        }
                    }
                } else {
                    recursiveParseForIndividualSatisfiesRestriction(node.getChildNodes(), nodeNewProduct, individualId, tagId, tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2);
                }
                //Se não encontrar o id continua o parse
            } else {
                if (node.getChildNodes().getLength() > 1) {
                    recursiveParseForIndividualSatisfiesRestriction(node.getChildNodes(), nodeNewProduct, individualId, tagId, tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2);
                } 
            }
        }    
    }
    
    
    public String listIndividualsByRestriction(String tagRestriction1, String tagRestriction2,
            String tagRestrictionValue1, String tagRestrictionValue2) {
        Node nodeRoot = doc.getFirstChild();
        NodeList nodeList = nodeRoot.getChildNodes();
        Node node1;
        sbResult = new StringBuilder();
        listIdProducts = new ArrayList<>();

        //Get the tag reference of the product
        node1 = nodeList.item(1);
        
        //Call the method for parse the xml recursively
        recursiveParseListIndividualByRestriction(nodeList, node1, tagRestriction1, tagRestriction2, tagRestrictionValue1, tagRestrictionValue2);
        
        sbResult.append("Indivíduos que satisfazem as restrições\n\n");
        
        //Take the ids that appear more than one time, matches with all two conditions
        Set<String> unique = new HashSet<>(listIdProducts);
        for (String key : unique) {
            //If only one option was chosen
            if (tagRestriction1.equals("") || tagRestriction2.equals("")) {
                if (Collections.frequency(listIdProducts, key) > 0) {
                    System.out.println(key);
                    sbResult.append(key).append("\n");
                }
            } else {
                if (Collections.frequency(listIdProducts, key) > 1) {
                    System.out.println(key);
                    sbResult.append(key).append("\n");
                }
            }
        }
        return sbResult.toString();
    }
    
    public void recursiveParseListIndividualByRestriction(NodeList nodeList, Node nodeNewProduct, String tagRestriction1,
            String tagRestriction2, String tagRestrictionValue1, String tagRestrictionValue2) {
        Node node;
        Node nodeAux;
        
        for (int i=0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                //if satisfies the first restriction
                if ((node.getNodeName().equalsIgnoreCase(tagRestriction1)) &&
                        (node.getTextContent().equalsIgnoreCase(tagRestrictionValue1))) {
                    nodeAux = node.getParentNode();
                    idProduct = nodeAux.getChildNodes().item(1).getTextContent();
                    listIdProducts.add(idProduct);
                }
                if ((node.getNodeName().equalsIgnoreCase(tagRestriction2)) &&
                        (node.getTextContent().equalsIgnoreCase(tagRestrictionValue2))) {
                    nodeAux = node.getParentNode();
                    idProduct = nodeAux.getChildNodes().item(1).getTextContent();
                    listIdProducts.add(idProduct);
                }
                //If the element has children
                if (node.getChildNodes().getLength() > 1) {
                    recursiveParseListIndividualByRestriction(node.getChildNodes(), nodeNewProduct, tagRestriction1, tagRestriction2, tagRestrictionValue1, tagRestrictionValue2);
                } 
            }
        }    
    }
    
    public String listIndividualHierarchy(String tagId, String idValue) {
        Node nodeRoot = doc.getFirstChild();
        NodeList nodeList = nodeRoot.getChildNodes();
        Node node1;
        sbResult = new StringBuilder();

        //Get the tag reference of the product
        node1 = nodeList.item(1);
        
        sbResult.append(nodeRoot.getNodeName());
        System.out.println(nodeRoot.getNodeName());
        sbResult.append("\n  ").append(node1.getNodeName());
        System.out.println("  " + node1.getNodeName());
        //Call the method for parse the xml recursively
        recursiveParseForIndividualHierarchy(nodeList, node1, tagId, idValue);
        return sbResult.toString();
    }
    
    public void recursiveParseForIndividualHierarchy(NodeList nodeList, Node nodeNewProduct, String tagId, String idValue) {
        Node node;
        
        for (int i=0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                //if finds the id on sku
                if (node.getNodeName().equalsIgnoreCase(tagId)) {
                    if (node.getTextContent().equalsIgnoreCase(idValue)) {
                        found = true;
                    } else {
                        if (found) {
                            return;
                        }
                        found = false;
                    }
                }
                if (found) {
                    //If the element has children
                    if (node.getChildNodes().getLength() > 1) {
                        //The tag that represents new element
                        if (nodeNewProduct.getNodeName().equals(node.getNodeName())) {
                            return;
                        }
                        //This is the case of "preenchimento"
                        System.out.println("    " + node.getNodeName() + ": ");
                        sbResult.append("\n    ").append(node.getNodeName()).append(": ");
                        
                        nodeLastChild = node.getNextSibling().getNextSibling().getNodeName();
                        recursiveParseForIndividualHierarchy(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                    } else {
                        //All of this rules to print the hierarchy ¬¬
                        if (!node.getNodeName().equals(nodeLastChild)) {
                            if (nodeLastChild != null)  {
                                if (nodeLastChild.equals("")) {
                                    System.out.println("    " + node.getNodeName() + ": " + node.getTextContent());
                                    sbResult.append("\n    ").append(node.getNodeName()).append(": ").append(node.getTextContent());
                                } else {
                                    System.out.println("      " + node.getNodeName() + ": " + node.getTextContent());
                                    sbResult.append("\n      ").append(node.getNodeName()).append(": ").append(node.getTextContent());
                                }
                            } else {
                                System.out.println("    " + node.getNodeName() + ": " + node.getTextContent());
                                sbResult.append("\n    ").append(node.getNodeName()).append(": ").append(node.getTextContent());
                            }
                        } else {
                            nodeLastChild = "";
                            //If does not have children and have value
                            //it is like child, but it represents the value
                            if (node.getFirstChild() != null) {
                                System.out.println("    " + node.getNodeName() + ": " + node.getTextContent());
                                sbResult.append("\n    ").append(node.getNodeName()).append(": ").append(node.getTextContent());
                            }
                        }
                    }
                } else {
                    recursiveParseForIndividualHierarchy(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                }
                //If didnt find the id, continue parsing
            } else {
                if (node.getChildNodes().getLength() > 1) {
                    recursiveParseForIndividualHierarchy(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                } 
            }
        }    
    }
    
    public String listIndividualTags(String tagId, String idValue) {
        Node nodeRoot = doc.getFirstChild();
        NodeList nodeList = nodeRoot.getChildNodes();
        Node node1;
        sbResult = new StringBuilder();

        //Get the tag reference of the product
        node1 = nodeList.item(1);
        
        //Call the method for parse the xml recursively
        recursiveParseForIndividualTag(nodeList, node1, tagId, idValue);
        return sbResult.toString();
    }
    
    public void recursiveParseForIndividualTag(NodeList nodeList, Node nodeNewProduct, String tagId, String idValue) {
        Node node;
        
        for (int i=0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                //if finds the id
                if (node.getNodeName().equalsIgnoreCase(tagId)) {
                    if (node.getTextContent().equalsIgnoreCase(idValue)) {
                        found = true;
                    } else {
                        found = false;
                    }
                }
                if (found) {
                    //If the element has children
                    if (node.getChildNodes().getLength() > 1) {
                        //The tag that represents new element
                        if (nodeNewProduct.getNodeName().equals(node.getNodeName())) {
                            return;
                        }
                        //This is the case of "preenchimento"
                        System.out.println("");
                        System.out.println(node.getNodeName() + ": ");
                        sbResult.append(node.getNodeName()).append(": \n");
                        recursiveParseForIndividualTag(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                    } else {
                        //If does not have children and have value
                        //it is like child, but it represents the value
                        if (node.getFirstChild() != null) {
                            System.out.println("");
                            System.out.println(node.getNodeName() + ": " + node.getTextContent());
                            sbResult.append(node.getNodeName()).append(": ").append(node.getTextContent()).append("\n");
                        }
                    }
                } else {
                    recursiveParseForIndividualTag(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                }
                //Se não encontrar o id continua o parse
            } else {
                if (node.getChildNodes().getLength() > 1) {
                    recursiveParseForIndividualTag(node.getChildNodes(), nodeNewProduct, tagId, idValue);
                } 
            }
        }    
    }
    
    public void testeDom() {
        Node nodeRoot = doc.getFirstChild();
        NodeList nodeList = nodeRoot.getChildNodes();
        Node node1;

        //Get the tag reference of the product
        node1 = nodeList.item(1);
        
        //Call the method for parse the xml recursively
        recursiveParse(nodeList, node1);
    }
    
    public void recursiveParse(NodeList nodeList, Node nodeNewProduct) {
        Node node;
        
        for (int i=0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            
            //Starts new element
            if (nodeNewProduct.getNodeName().equals(node.getNodeName())) {
                System.out.println("NOVO PRODUTO");
            }
            
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                //If the element has children
                if (node.getChildNodes().getLength() > 1) {
                    System.out.println("");
                    System.out.println(node.getNodeName() + ": ");
                    recursiveParse(node.getChildNodes(), nodeNewProduct);
                } else {
                    //If does not have children and have value
                    //it is like 1 child, but it represents the value
                    if (node.getFirstChild() != null) {
                        System.out.println("");
                        System.out.println(node.getNodeName() + ": " + node.getTextContent());
                    }
                }
            }
        }    
    }
            
            
//    public String listIndividualTags(String individualId) {
//        StringBuilder sb = new StringBuilder();
//        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node nNode = nodeList.item(i);
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) nNode;
//                //Find the individual
//                if (element.getElementsByTagName("sku").item(0).getTextContent().equals(individualId)) {
//                    System.out.println("SKU: " + element.getElementsByTagName("sku").item(0).getTextContent());
//                    System.out.println("Nome: " + element.getElementsByTagName("nome").item(0).getTextContent());
//                    System.out.println("Descrição: " + element.getElementsByTagName("descricao").item(0).getTextContent());
//                    System.out.println("Marca: " + element.getElementsByTagName("marca").item(0).getTextContent());
//                    System.out.println("Loja: " + element.getElementsByTagName("loja").item(0).getTextContent());
//                    System.out.println("Preco: " + element.getElementsByTagName("preco").item(0).getTextContent());
//                    System.out.println("Preco promocao: " + element.getElementsByTagName("preco-promocao").item(0).getTextContent());
//                    System.out.println("Numero de parcelas: " + element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
//                    System.out.println("Valor das parcelas: " + element.getElementsByTagName("valor-da-parcela").item(0).getTextContent());
//                    System.out.println("URL: " + element.getElementsByTagName("url").item(0).getTextContent());
//                    System.out.println("URL da imagem: " + element.getElementsByTagName("url-imagem").item(0).getTextContent());
//                    System.out.println("Preenchimento: ");
//                    for (int countColor = 0; countColor < element.getElementsByTagName("cor").getLength(); countColor++) {
//                        System.out.println("    Cor: " + element.getElementsByTagName("cor").item(countColor).getTextContent());
//                    }
//                    for (int countTexture = 0; countTexture < element.getElementsByTagName("textura").getLength(); countTexture++) {
//                        System.out.println("    Textura: " + element.getElementsByTagName("textura").item(countTexture).getTextContent());
//                    }
//                    for (int j = 0; j < element.getElementsByTagName("tamanho").getLength(); j++) {
//                        System.out.println("Tamanho: " + element.getElementsByTagName("tamanho").item(j).getTextContent());
//                    }
//                    for (int countClassification = 0; countClassification < element.getElementsByTagName("classificacao").getLength(); countClassification++) {
//                        System.out.println("Classificação: " + element.getElementsByTagName("classificacao").item(countClassification).getTextContent());
//                    }
//                    System.out.println("Categoria: " + element.getElementsByTagName("categoria").item(0).getTextContent());
//                    System.out.println("Subcategoria: " + element.getElementsByTagName("subcategoria").item(0).getTextContent());
//                    
//                    sb.append("SKU: ").append(element.getElementsByTagName("sku").item(0).getTextContent());
//                    sb.append("\nNome: ").append(element.getElementsByTagName("nome").item(0).getTextContent());
//                    sb.append("\nDescrição: ").append(element.getElementsByTagName("descricao").item(0).getTextContent());
//                    sb.append("\nMarca: ").append(element.getElementsByTagName("marca").item(0).getTextContent());
//                    sb.append("\nLoja: ").append(element.getElementsByTagName("loja").item(0).getTextContent());
//                    sb.append("\nPreco: ").append(element.getElementsByTagName("preco").item(0).getTextContent());
//                    sb.append("\nPreco promocao: ").append(element.getElementsByTagName("preco-promocao").item(0).getTextContent());
//                    sb.append("\nNumero de parcelas: ").append(element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
//                    sb.append("\nURL: ").append(element.getElementsByTagName("url").item(0).getTextContent());
//                    sb.append("\nURL da imagem: ").append(element.getElementsByTagName("url-imagem").item(0).getTextContent());
//                    sb.append("\nPreenchimento: ");
//                     for (int countColor = 0; countColor < element.getElementsByTagName("cor").getLength(); countColor++) {
//                        sb.append("\n   Cor: ").append(element.getElementsByTagName("cor").item(countColor).getTextContent());
//                    }
//                    for (int countTexture = 0; countTexture < element.getElementsByTagName("textura").getLength(); countTexture++) {
//                        sb.append("\n   Textura: ").append(element.getElementsByTagName("textura").item(countTexture).getTextContent());
//                    }
//                    for (int j = 0; j < element.getElementsByTagName("tamanho").getLength(); j++) {
//                        sb.append("\nTamanho: ").append(element.getElementsByTagName("tamanho").item(j).getTextContent());
//                    }
//                    for (int countClassification = 0; countClassification < element.getElementsByTagName("classificacao").getLength(); countClassification++) {
//                        sb.append("\nClassificação: ").append(element.getElementsByTagName("classificacao").item(countClassification).getTextContent());
//                    }
//                    sb.append("\nCategoria: ").append(element.getElementsByTagName("categoria").item(0).getTextContent());
//                    sb.append("\nSubcategoria: ").append(element.getElementsByTagName("subcategoria").item(0).getTextContent());
//                }
//            }
//        }
//        if (sb.toString().equals("")) {
//            sb.append("O indivíduo não foi encontrado!");
//        }
//        return sb.toString();
//    }
    
    public String listTagValues(String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2, String tagTarget) {
        return null;
    }
}
