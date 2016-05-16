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
 * Implements Prova3
 */
public class Prova3 {
    private final OpenFile openFile;
    private final Document doc;
    
    //Constructor receive the doc
    public Prova3(String xmlFileName) {
        openFile = new OpenFile();
        doc = openFile.createDoc(xmlFileName);
    }
    
    //Finds especific element in the nodes
    public void init() {
        NodeList nodeList = doc.getElementsByTagName("produto-de-moda");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                //Find the element 1868494
                if (element.getElementsByTagName("sku").item(0).getTextContent().equals("1868494")) {
                    System.out.println("SKU: " + element.getElementsByTagName("sku").item(0).getTextContent());
                    System.out.println("Nome: " + element.getElementsByTagName("nome").item(0).getTextContent());
                    System.out.println("Descrição: " + element.getElementsByTagName("descricao").item(0).getTextContent());
                    System.out.println("Marca: " + element.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Loja: " + element.getElementsByTagName("loja").item(0).getTextContent());
                    System.out.println("Preco: " + element.getElementsByTagName("preco").item(0).getTextContent());
                    System.out.println("Preco Promoção: " + element.getElementsByTagName("preco-promocao").item(0).getTextContent());
                    System.out.println("Número de parcelas: " + element.getElementsByTagName("numero-de-parcelas").item(0).getTextContent());
                    System.out.println("Valor das parcelas: " + element.getElementsByTagName("valor-da-parcela").item(0).getTextContent());
                    System.out.println("URL: " + element.getElementsByTagName("url").item(0).getTextContent());
                    System.out.println("URL da imagem: " + element.getElementsByTagName("url-imagem").item(0).getTextContent());
                    System.out.println("Preenchimento: " + element.getElementsByTagName("preenchimento").item(0).getTextContent());
                    System.out.println("Cor: " + element.getElementsByTagName("cor").item(0).getTextContent());
                    System.out.println("Textura: " + element.getElementsByTagName("textura").item(0).getTextContent());
                    System.out.println("Tamanho: " + element.getElementsByTagName("tamanho").item(0).getTextContent());
                    System.out.println("PlusSize: " + element.getElementsByTagName("plus-size").item(0).getTextContent());
                    System.out.println("Classificação: " + element.getElementsByTagName("classificacao").item(0).getTextContent());
                    System.out.println("Categoria: " + element.getElementsByTagName("categoria").item(0).getTextContent());
                    System.out.println("Subcategoria: " + element.getElementsByTagName("subcategoria").item(0).getTextContent());
                }
            }
        }
    }
    
}
