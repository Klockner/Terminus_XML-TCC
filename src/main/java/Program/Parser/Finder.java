/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Parser;

import Program.Util.OpenFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author klockner
 */
public class Finder {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Finder(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
//    public void testeDom() {
//        Node nodeRoot = doc.getFirstChild();
//        NodeList nodeList = nodeRoot.getChildNodes();
//        Node node1, node2, node3;
//
//        for (int i=0; i < nodeList.getLength(); i++) {
//            node1 = nodeList.item(i);
//            
//            if(node1.getNodeType()==Node.ELEMENT_NODE) {
//                NodeList nodeList2 = node1.getChildNodes();
//                for(int i2=0; i2<nodeList2.getLength(); i2++) {
//                    node2 = nodeList2.item(i2);
//                    
//                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
//                        //Se for um elemento pai
//                        if (node2.getChildNodes().getLength() > 1) {
//                            System.out.println("");
//                            System.out.println(node2.getNodeName() + ": ");
//                            NodeList nodeList3 = node2.getChildNodes();
//                            for(int i3=0; i3<nodeList3.getLength(); i3++) {
//                                node3 = nodeList3.item(i3);
//                                //Se o conteudo (texto, valor) da tag for diferente de null, então imprime
//                                if (node3.getFirstChild() != null) {
//                                    System.out.println("    " + node3.getNodeName() + ": " + node3.getTextContent());
//                                }
//                            }
//                        } else {
//                            //Se o conteudo (texto, valor) da tag for diferente de null, então imprime
//                            if (node2.getFirstChild() != null) {
//                                System.out.println("");
//                                System.out.println(node2.getNodeName() + ": " + node2.getTextContent());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    
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
            
            //Começa novo elemento, novo produto
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
                    if (node.getFirstChild() != null) {
                        System.out.println("");
                        System.out.println(node.getNodeName() + ": " + node.getTextContent());
                    }
                }
            }
        }    
    }
            
            
    public String listIndividualTags(String individualId) {
        StringBuilder sb = new StringBuilder();
        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                //Find the individual
                if (element.getElementsByTagName("sku").item(0).getTextContent().equals(individualId)) {
                    System.out.println("SKU: " + element.getElementsByTagName("sku").item(0).getTextContent());
                    System.out.println("Nome: " + element.getElementsByTagName("nome").item(0).getTextContent());
                    System.out.println("Descrição: " + element.getElementsByTagName("descricao").item(0).getTextContent());
                    System.out.println("Marca: " + element.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Loja: " + element.getElementsByTagName("loja").item(0).getTextContent());
                    System.out.println("Preco: " + element.getElementsByTagName("preco").item(0).getTextContent());
                    System.out.println("Preco promocao: " + element.getElementsByTagName("preco-promocao").item(0).getTextContent());
                    System.out.println("Numero de parcelas: " + element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
                    System.out.println("Valor das parcelas: " + element.getElementsByTagName("valor-da-parcela").item(0).getTextContent());
                    System.out.println("URL: " + element.getElementsByTagName("url").item(0).getTextContent());
                    System.out.println("URL da imagem: " + element.getElementsByTagName("url-imagem").item(0).getTextContent());
                    System.out.println("Preenchimento: ");
                    for (int countColor = 0; countColor < element.getElementsByTagName("cor").getLength(); countColor++) {
                        System.out.println("    Cor: " + element.getElementsByTagName("cor").item(countColor).getTextContent());
                    }
                    for (int countTexture = 0; countTexture < element.getElementsByTagName("textura").getLength(); countTexture++) {
                        System.out.println("    Textura: " + element.getElementsByTagName("textura").item(countTexture).getTextContent());
                    }
                    for (int j = 0; j < element.getElementsByTagName("tamanho").getLength(); j++) {
                        System.out.println("Tamanho: " + element.getElementsByTagName("tamanho").item(j).getTextContent());
                    }
                    for (int countClassification = 0; countClassification < element.getElementsByTagName("classificacao").getLength(); countClassification++) {
                        System.out.println("Classificação: " + element.getElementsByTagName("classificacao").item(countClassification).getTextContent());
                    }
                    System.out.println("Categoria: " + element.getElementsByTagName("categoria").item(0).getTextContent());
                    System.out.println("Subcategoria: " + element.getElementsByTagName("subcategoria").item(0).getTextContent());
                    
                    sb.append("SKU: ").append(element.getElementsByTagName("sku").item(0).getTextContent());
                    sb.append("\nNome: ").append(element.getElementsByTagName("nome").item(0).getTextContent());
                    sb.append("\nDescrição: ").append(element.getElementsByTagName("descricao").item(0).getTextContent());
                    sb.append("\nMarca: ").append(element.getElementsByTagName("marca").item(0).getTextContent());
                    sb.append("\nLoja: ").append(element.getElementsByTagName("loja").item(0).getTextContent());
                    sb.append("\nPreco: ").append(element.getElementsByTagName("preco").item(0).getTextContent());
                    sb.append("\nPreco promocao: ").append(element.getElementsByTagName("preco-promocao").item(0).getTextContent());
                    sb.append("\nNumero de parcelas: ").append(element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
                    sb.append("\nURL: ").append(element.getElementsByTagName("url").item(0).getTextContent());
                    sb.append("\nURL da imagem: ").append(element.getElementsByTagName("url-imagem").item(0).getTextContent());
                    sb.append("\nPreenchimento: ");
                     for (int countColor = 0; countColor < element.getElementsByTagName("cor").getLength(); countColor++) {
                        sb.append("\n   Cor: ").append(element.getElementsByTagName("cor").item(countColor).getTextContent());
                    }
                    for (int countTexture = 0; countTexture < element.getElementsByTagName("textura").getLength(); countTexture++) {
                        sb.append("\n   Textura: ").append(element.getElementsByTagName("textura").item(countTexture).getTextContent());
                    }
                    for (int j = 0; j < element.getElementsByTagName("tamanho").getLength(); j++) {
                        sb.append("\nTamanho: ").append(element.getElementsByTagName("tamanho").item(j).getTextContent());
                    }
                    for (int countClassification = 0; countClassification < element.getElementsByTagName("classificacao").getLength(); countClassification++) {
                        sb.append("\nClassificação: ").append(element.getElementsByTagName("classificacao").item(countClassification).getTextContent());
                    }
                    sb.append("\nCategoria: ").append(element.getElementsByTagName("categoria").item(0).getTextContent());
                    sb.append("\nSubcategoria: ").append(element.getElementsByTagName("subcategoria").item(0).getTextContent());
                }
            }
        }
        if (sb.toString().equals("")) {
            sb.append("O indivíduo não foi encontrado!");
        }
        return sb.toString();
    }
    
    public String listIndividualHierarchy(String individualId) {
        return null;
    }
    
    public String listIndividualsByRestriction(String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2) {
        return null;
    }
    
    public String individualSatisfiesRestriction(String individualId, String tagRestriction1, 
            String tagRestrictionValue1, String tagRestriction2, String tagRestrictionValue2) {
        return null;
    }
    
    public String listTagValues(String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2, String tagTarget) {
        return null;
    }
}
