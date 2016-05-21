/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.Parser;

import Program.Util.OpenFile;
import org.w3c.dom.Document;

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
    
    private void listIndividualTags(String individualId) {
        
    }
    
    private void listIndividualHierarchy(String individualId) {
        
    }
    
    private void listIndividualsByRestriction(String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2) {
        
    }
    
    private void individualSatisfiesRestriction(String individualId, String tagRestriction1, 
            String tagRestrictionValue1, String tagRestriction2, String tagRestrictionValue2) {
        
    }
    
    private void listTagValues(String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2, String tagTarget) {
        
    }
}
