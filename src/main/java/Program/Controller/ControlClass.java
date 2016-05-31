package Program.Controller;

//Esta classe aqui é para ser substituida pelo programa em XML que você fizer.

import Program.Parser.Finder;
import Program.Proofs.Proof1;
import Program.Proofs.Proof2;
import Program.Proofs.Proof3;
import Program.Proofs.Proof4;
import Program.Proofs.Proof5;
import Program.Proofs.Proof6;

//This class implements the controller between Proofs and View
public class ControlClass {

    //Attributes
    private Proof1 prova1a;
    private Proof1 prova1b;
    private Proof2 prova2;
    private Proof3 prova3a;
    private Proof3 prova3b;
    private Proof4 prova4;
    private Proof5 prova5;
    private Proof6 prova6a;
    private Proof6 prova6b;
    private Finder finder;

    //Constructor
    public ControlClass() {
        
    }
    
    public String listTagValues(String xmlFileName, String tagRestriction1, String tagRestrictionValue1,
            String tagRestriction2, String tagRestrictionValue2, String tagTarget) {
        finder = new Finder(xmlFileName);
        return finder.listTagValues(tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2, tagTarget);
    }
    
    public String individualSatisfiesRestriction(String xmlFileName, String individualId, String tagId, String tagRestriction1, 
            String tagRestrictionValue1, String tagRestriction2, String tagRestrictionValue2) {
        finder = new Finder(xmlFileName);
        return finder.individualSatisfiesRestriction(individualId, tagId, tagRestriction1, tagRestrictionValue1, tagRestriction2, tagRestrictionValue2);
    }
    
    public String listIndividualsByRestriction(String xmlFileName, String tagRestriction1, String tagRestriction2,
            String tagRestrictionValue1, String tagRestrictionValue2) {
        finder = new Finder(xmlFileName);
        return finder.listIndividualsByRestriction(tagRestriction1, tagRestriction2, tagRestrictionValue1, tagRestrictionValue2);
    }
    
    public String listIndividualTags(String xmlFileName, String tagId, String idValue) {
        finder = new Finder(xmlFileName);
        return finder.listIndividualTags(tagId, idValue);
    }
    
    public String listIndividualHierarchy(String xmlFileName, String tagId, String idValue) {
        finder = new Finder(xmlFileName);
        return finder.listIndividualHierarchy(tagId, idValue);
    }
    
    //This method is responsible for apply the proofs and return the result string
    public String createProof(String proofNumber, String xmlFileName) {
        switch (proofNumber) {
            case "1a":
                prova1a = new Proof1(xmlFileName);
                return prova1a.init();
            case "1b":
                prova1b = new Proof1(xmlFileName);
                return prova1b.init();
            case "2":
                prova2 = new Proof2(xmlFileName);
                return prova2.init();
            case "3a":
                prova3a = new Proof3(xmlFileName);
                return prova3a.init();
            case "3b":
                prova3b = new Proof3(xmlFileName);
                return prova3b.init();
            case "4":
                prova4 = new Proof4(xmlFileName);
                return prova4.init();
            case "5":
                prova5 = new Proof5(xmlFileName);
                return prova5.init();
            case "6a":
                prova6a = new Proof6(xmlFileName);
                return prova6a.init("l");
            case "6b":
                prova6b = new Proof6(xmlFileName);
                return prova6b.init("g");
            default:
                return null;
        }
    }
}
