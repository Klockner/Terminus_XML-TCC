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
public class Proof4 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Proof4(String xmlFileName) {
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
                //Find the element 1246194
                if (element.getElementsByTagName("sku").item(0).getTextContent().equals("1246194")) {
                    System.out.println(nNode.getParentNode().getNodeName());
                    System.out.println(nNode.getNodeName());
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
                    System.out.println("Unissex: " + element.getElementsByTagName("unissex").item(0).getTextContent());
                    System.out.println("Categoria: " + element.getElementsByTagName("categoria").item(0).getTextContent());
                    System.out.println("Subcategoria: " + element.getElementsByTagName("subcategoria").item(0).getTextContent());
                    
                    sb.append(nNode.getParentNode().getNodeName()).append(":");
                    sb.append("\n  ").append(nNode.getNodeName()).append(":");
                    sb.append("\n    SKU: ").append(element.getElementsByTagName("sku").item(0).getTextContent());
                    sb.append("\n    Nome: ").append(element.getElementsByTagName("nome").item(0).getTextContent());
                    sb.append("\n    Descrição: ").append(element.getElementsByTagName("descricao").item(0).getTextContent());
                    sb.append("\n    Marca: ").append(element.getElementsByTagName("marca").item(0).getTextContent());
                    sb.append("\n    Loja: ").append(element.getElementsByTagName("loja").item(0).getTextContent());
                    sb.append("\n    Preco: ").append(element.getElementsByTagName("preco").item(0).getTextContent());
                    sb.append("\n    Preco promocao: ").append(element.getElementsByTagName("preco-promocao").item(0).getTextContent());
                    sb.append("\n    Numero de parcelas: ").append(element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
                    sb.append("\n    URL: ").append(element.getElementsByTagName("url").item(0).getTextContent());
                    sb.append("\n    URL da imagem: ").append(element.getElementsByTagName("url-imagem").item(0).getTextContent());
                    sb.append("\n    Preenchimento: ");
                     for (int countColor = 0; countColor < element.getElementsByTagName("cor").getLength(); countColor++) {
                        sb.append("\n     Cor: ").append(element.getElementsByTagName("cor").item(countColor).getTextContent());
                    }
                    for (int countTexture = 0; countTexture < element.getElementsByTagName("textura").getLength(); countTexture++) {
                        sb.append("\n     Textura: ").append(element.getElementsByTagName("textura").item(countTexture).getTextContent());
                    }
                    for (int j = 0; j < element.getElementsByTagName("tamanho").getLength(); j++) {
                        sb.append("\n    Tamanho: ").append(element.getElementsByTagName("tamanho").item(j).getTextContent());
                    }
                    for (int countClassification = 0; countClassification < element.getElementsByTagName("classificacao").getLength(); countClassification++) {
                        sb.append("\n    Classificação: ").append(element.getElementsByTagName("classificacao").item(countClassification).getTextContent());
                    }
                    sb.append("\n    Unissex: ").append(element.getElementsByTagName("unissex").item(0).getTextContent());
                    sb.append("\n    Categoria: ").append(element.getElementsByTagName("categoria").item(0).getTextContent());
                    sb.append("\n    Subcategoria: ").append(element.getElementsByTagName("subcategoria").item(0).getTextContent());
                }
            }
        }
        return sb.toString();
    }
}