package consensus;

import java.util.List;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.HirstStOnge;
import edu.cmu.lti.ws4j.impl.JiangConrath;
import edu.cmu.lti.ws4j.impl.LeacockChodorow;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class WordNetDistance2 implements Distance {

	public WordNetDistance2() {}
	
	private static ILexicalDatabase db = new NictWordNet();
	
	//available options of metrics
	private static RelatednessCalculator[] rcs = { new HirstStOnge(db),
			new LeacockChodorow(db), new Lesk(db), new WuPalmer(db),
			new Resnik(db), new JiangConrath(db), new Lin(db), new Path(db) };
	
	private static double compute(String x, String y, int method) {
		WS4JConfiguration.getInstance().setMFS(true);
		List<POS[]> posPairs = rcs[method].getPOSPairs();
		double maxScore = -1D;
		
		for(POS[] posPair: posPairs) {
	        List<Concept> synsets1 = (List<Concept>)db.getAllConcepts(x, posPair[0].toString());
	        List<Concept> synsets2 = (List<Concept>)db.getAllConcepts(y, posPair[1].toString());
	        for(Concept synset1: synsets1) {
	            for (Concept synset2: synsets2) {
	                Relatedness relatedness = rcs[method].calcRelatednessOfSynset(synset1, synset2);
	                double score = relatedness.getScore();
	                if (score > maxScore) {
	                    maxScore = score;
	                }
	            }
	        }
	    }
		
		
		//double s = rcs[method].calcRelatednessOfWords(x, y);
		return maxScore;
	}

	public double calculate(String x, String y) {
		return compute(x,y,7);//lin as default
	}

}
