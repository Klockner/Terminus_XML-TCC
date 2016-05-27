package Program;

//Esta classe aqui é para ser substituida pelo programa em XML que você fizer.

import Program.Parser.Finder;
import Program.Provas.Prova1;
import Program.Provas.Prova2;
import Program.Provas.Prova3;
import Program.Provas.Prova4;
import Program.Provas.Prova5;
import Program.Provas.Prova6;

//This class implements the controller between Proofs and View
public class ControlClass {

    //Attributes
    private Prova1 prova1a;
    private Prova1 prova1b;
    private Prova2 prova2;
    private Prova3 prova3a;
    private Prova3 prova3b;
    private Prova4 prova4;
    private Prova5 prova5;
    private Prova6 prova6a;
    private Prova6 prova6b;
    private Finder finder;

    //Constructor
    public ControlClass() {
        
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
                prova1a = new Prova1(xmlFileName);
                return prova1a.init();
            case "1b":
                prova1b = new Prova1(xmlFileName);
                return prova1b.init();
            case "2":
                prova2 = new Prova2(xmlFileName);
                return prova2.init();
            case "3a":
                prova3a = new Prova3(xmlFileName);
                return prova3a.init();
            case "3b":
                prova3b = new Prova3(xmlFileName);
                return prova3b.init();
            case "4":
                prova4 = new Prova4(xmlFileName);
                return prova4.init();
            case "5":
                prova5 = new Prova5(xmlFileName);
                return prova5.init();
            case "6a":
                prova6a = new Prova6(xmlFileName);
                return prova6a.init("l");
            case "6b":
                prova6b = new Prova6(xmlFileName);
                return prova6b.init("g");
            default:
                return null;
        }
    }
}
