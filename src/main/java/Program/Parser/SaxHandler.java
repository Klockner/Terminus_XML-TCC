/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author klockner
 */
public class SaxHandler extends DefaultHandler {
    private Map<String, String> mapXml = new HashMap<>();
 
    //As we read any XML element we will push that in this stack
    private Stack elementStack = new Stack();
    
    private String tag = "";
    
    public void startDocument() throws SAXException {

    }

    public void endDocument() throws SAXException {

    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
        //Push it in element stack
        this.elementStack.push(qName);
        tag = qName;
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        //Remove last added  element
        this.elementStack.pop();
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
 
        if (value.length() == 0)
        {
            return; // ignore white space
        } else {
            System.out.println(tag + ": " + value);
            mapXml.put(tag, value);
        }
    }
    
    /**
     * Utility method for getting the current element in processing
     * */
    public String currentElement()
    {
        return (String) this.elementStack.peek();
    }
}
