package consensus;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

import de.linguatools.disco.CorruptConfigFileException;
import de.linguatools.disco.DISCO;
import de.linguatools.disco.DISCO.SimilarityMeasure;
import de.linguatools.disco.DISCOLuceneIndex;
import de.linguatools.disco.ReturnDataBN;
import de.linguatools.disco.TextSimilarity;

public class DiscoDistance implements Distance {

	public DiscoDistance() {
		// TODO Auto-generated constructor stub
	}
	
	public static double compute(String x, String y, String path) {

		// first command line argument is path to the DISCO word space directory
        //String discoDir = path;
        // second argument is the input word
        //String word = x;

		/****************************************
		 * create instance of class DISCO.      *
		 * Do NOT load the word space into RAM. *
		 ****************************************/
		float sim = 0.0f;
        try {
        	DISCO disco = DISCO.load(path);
        	// is the word space of type "sim"?
        	/*
            if( !disco.getWordspaceType().equals(DISCO.WordspaceType.SIM) ){
               System.err.println("The word space "+path+" is not of type SIM! It is the type "+disco.getWordspaceType());
               return -1.0; 
            }
            */
            int freqX = disco.frequency(x);
            int freqY = disco.frequency(y);
            
            /*if(freqX == 0) {
                System.err.println("Frequency of "+x+" is zero");
            	return -1.0;
            }
            if(freqY == 0) {
                System.err.println("Frequency of "+y+" is zero");
            	return -1.0;
            }*/
            sim = TextSimilarity.directedTextSimilarity(x, y, disco, SimilarityMeasure.COSINE);
            sim = ((sim+1.0f)/2.0f);
            //sim = disco.semanticSimilarity(x, y, 
			//    	DISCO.getVectorSimilarity(SimilarityMeasure.COSINE));
            //System.out.println("Distance beetwen '"+x+"' and '"+y+"' is "+sim);
        } catch (FileNotFoundException | CorruptConfigFileException ex) {
            System.err.println("Error creating DISCO instance: "+ex);
            return -1.0;
        } catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
        // and print it to stdout
        //System.out.println("Frequency of "+word+" is "+freq);

        
        
        
		
        return sim;
/*
        // retrieve the collocations for the input word
        ReturnDataCol[] collocationResult = disco.collocations(word);
        // and print the first 20 to stdout
        System.out.println("Collocations:");
        for(int i = 1; i < collocationResult.length; i++){
            System.out.println("\t"+collocationResult[i].word+"\t"+
                    collocationResult[i].value);
            if( i >= 20 ) break;
        }
*/
        // retrieve the most similar words for the input word
        /*ReturnDataBN simResult;
        try {
            simResult = disco.similarWords(word);
        } catch (WrongWordspaceTypeException ex) {
            System.err.println("Error retrieving most similar words: "+ex);
            return -1.0;
        }
        // and print the first 20 of them to stdout
        System.out.println("Most similar words:");
        for(int i = 1; i < simResult.words.length; i++){
            System.out.println("\t"+simResult.words[i]+"\t"+simResult.values[i]);
	    if( i >= 20 ) break;
        }*/
	}

	public double calculate(String x, String y) {
		
		
		
		
		
		// TODO Auto-generated method stub
		return compute(x, y, "C:\\Users\\tclem\\Documents\\gits\\travistorrent-eda\\data\\outputdirectory\\cc.de.300.col.denseMatrix\\cc.en.300-COL.denseMatrix");
	}

}
