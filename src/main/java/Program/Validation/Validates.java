/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Validation;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Gabriel
 */
public class Validates {
    /**
     * This method makes the model validation with XML an XSD
     *
     * @param xmlFullFileName
     * @param xsdFullFileName
     * @return
     */
    public static boolean validateModel(String xmlFullFileName, String xsdFullFileName) {
        // Creates the factory
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // Enable support to namespace
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(true);
        // Attributes for validation.
        documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsdFullFileName);
        try {
            // Creates a builder for document xml.
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
             // Class responsible for save the validation errors.
            ErrorHandler errorHandler = new ErrorHandler();
            documentBuilder.setErrorHandler(errorHandler);
            Document document = null;
            // First parse.
            document = documentBuilder.parse(xmlFullFileName);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Load the schema
            Source schemaFile = new StreamSource(new File(xsdFullFileName));
            Schema schema = schemaFactory.newSchema(schemaFile);
            // Creates an object ValidationHandler
            Validator validator = schema.newValidator();
            // Indica o objeto que irá tratar os error. Observe que ao encontrar
            // um erro, este é simplesmente guardado e processo de validação continua.
            try {
                // Efetua a validação propriamente.
                validator.validate(new DOMSource(document));
            } catch (SAXException | IOException e) {
                // Se algum erro foi encontrado, apresenta-o.
                if (!errorHandler.handlerList.isEmpty()) {
                    for (String error : errorHandler.handlerList) {
                        System.out.println(error);
                    }
                }
                return false;
            }
            return true;
        } catch (ParserConfigurationException e) {
            System.out.println("Erro ao criar builder xml: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Erro ao parsear xml: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo xml: " + e.getMessage());
        }
        return false;
    }
}