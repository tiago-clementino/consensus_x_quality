import java.util.List;

import consensus.WordNetDistance;
import consensus.WordNetDistance2;
import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class LeskSimilarity{

    public static void main(String[] args) {
	    ILexicalDatabase db = new NictWordNet();
	    RelatednessCalculator lesk = new Lesk(db);
	    String word1="pink";
	    POS posWord1=  POS.n;
	    String word2= "pink";
	    POS posWord2= POS.n;
	    double maxScore = 0;
	
	        WS4JConfiguration.getInstance().setMFS(true);
	
	        List<Concept> synsets1 = (List<Concept>)db.getAllConcepts(word1, posWord1.name());
	        List<Concept> synsets2 = (List<Concept>)db.getAllConcepts(word2, posWord2.name());
	
	        for(Concept synset1: synsets1) {
	            for (Concept synset2: synsets2) {
	                Relatedness relatedness =     lesk.calcRelatednessOfSynset(synset1, synset2);
	            double score = relatedness.getScore();
	            if (score > maxScore) { 
	                maxScore = score;
	            }
	          }
	        }
	
	    if (maxScore == -1D) {
	        maxScore = 0.0;
	    }
	
	    System.out.println("Similarity score of " + word1 + " & " + word2 + " : " + maxScore);
	    System.out.println("Similarity score of " + word1 + " & " + word2 + " : " + (new WordNetDistance()).calculate(word1, word2));
	    System.out.println("Similarity score of " + word1 + " & " + word2 + " : " + (new WordNetDistance2()).calculate(word1, word2));
    }
}