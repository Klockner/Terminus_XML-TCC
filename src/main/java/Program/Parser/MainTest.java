/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Parser;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

/**
 *
 * @author klockner
 */
public class MainTest {
 
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, XPathExpressionException {
        Finder finder = new Finder("produto_prova5.xml");
//        finder.novoSax("produto_prova1a.xml");
//        finder.testeDom();
//        finder.listIndividualHierarchy("SKU", "1763822");
//        finder.listIndividualsByRestriction("categoria", "plus-size", "calça", "sim");
        finder.individualSatisfiesRestriction("1200827", "sku", "categoria", "calça", "plus-size", "sim");
    }
}
