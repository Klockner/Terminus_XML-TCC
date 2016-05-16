/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Validation;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Gabriel
 * This class is responsible for the errors of validation.
 * 
 */
public class ErrorHandler implements org.xml.sax.ErrorHandler {
    final List<String> handlerList= new ArrayList<>();
    
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        handlerList.add("ATENÇÃO: " + exception.getMessage());
    }
    @Override
    public void error(SAXParseException exception) throws SAXException {
        handlerList.add("ERRO: " + exception.getMessage());
    }
    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        handlerList.add("ERRO FATAL: " + exception.getMessage());
    }
}