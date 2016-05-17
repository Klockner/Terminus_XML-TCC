/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Validation;

/**
 *
 * @author Gabriel
 */
public class Main {
    
    public static void main(String[] args) throws Throwable {
        String fileXml = "produtos.xml";
        String fileXsd = "produto.xsd";
        
        boolean isValid = Validates.validateModel(fileXml, fileXsd);
        if (isValid) {
            System.out.println("XML válido!");
        } else {
            System.out.println("Não foi possível validar o XML.");
        }
    }
}
