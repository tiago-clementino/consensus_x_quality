package consensus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import mooc_forum_analytics.CSVPost;

public class Consensus implements Comparable<Consensus>{
	
	private Double[] dimensions;

	public Consensus() {
		this.dimensions = new Double[4];//aponta a proximidade entre duas opiniões para um dado estudante//sentiment,confusion,urgency,time distance
	}

	public Consensus(Double scalar) {
		this();
		this.setDimensions(0.0);
	}
	
	public Consensus(Double[] dimensionsResult) {
		this(0.0);
		if(dimensionsResult!=null) {
			if(dimensionsResult.length==this.dimensions.length) {
				this.dimensions=dimensionsResult;
			}else {
				if(dimensionsResult.length>this.dimensions.length) {
					this.dimensions=Arrays.copyOfRange(dimensionsResult, 0, this.dimensions.length);
				}else {
					for (int i = 0; i < dimensionsResult.length; i++) {
						this.dimensions[i]=dimensionsResult[i];
					}
				}
			}
		}
	}

	private void setDimensions(double d) {
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i]=d;
		}
	}

	public static Double distanceCalculation(Double[] a, Double[] b,String type){
		Double d = null; 
		if (type.equalsIgnoreCase("manhattan")){
			d=manhattan(a,b);
		}else if (type.equalsIgnoreCase("euclidean")) {
			d=euclidean(a,b);
		}else if (type.equalsIgnoreCase("cosine")) {
			//d=cosine(a,sqrt(1-a*a),b,sqrt(1-b*b));
		}else if(type.equalsIgnoreCase("dice")) {
			//d=dice(a,sqrt(1-a*a),b,sqrt(1-b*b));
		}else if(type.equalsIgnoreCase("jacard")) {
			//d=jacard(a,sqrt(1-a*a),b,sqrt(1-b*b));
		}
		return(d);

	}

	public static Double manhattan(Double[] a, Double[] b){
		//Double d = Consensus.sum(Consensus.abs(Consensus.subtraction(a,b)));
		Double[] d = Consensus.abs(Consensus.subtraction(a,b));
		return(Consensus.getSum(d));
	}


	public static Double getSum(Double[] a) {
		//Double[] dimensions = this.getDimenssions();
		Double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum+=a[i];
		}
		return sum / a.length;//para normalizar, podemos remover depois
	}

	private static Double[] abs(Double[] a) {
		//Double[] dimensions = a.getDimenssions();
		for (int i = 0; i < a.length; i++) {
			if(a[i]<0.0) {
				a[i]=a[i]*-1.0;
			}
		}
		return a;
	}

	public static Double euclidean(Double[] a, Double[] b){
		//d=sqrt(sum((a-b)*(a-b)));
		Double[] d = Consensus.abs(Consensus.subtraction(a,b));
		return Math.sqrt(Consensus.getSum(Consensus.multiply(d,d)));
	}
	
	
	public static Double weight(Double r,String type){

		Double a = 0.0;
		Double b = 0.0;
		Double q = 0.0;
		if( type.equalsIgnoreCase("leasthalf")){
			a=0.0;
			b=0.5;
		}else if(type.equalsIgnoreCase("most")){
			a=0.3;
			b=0.8;
		}else if(type.equalsIgnoreCase("asmany")){
			a=0.5;
			b=1.0;
		}
		if (r>=0 && r<a){
			q=0.0;
		}else if(r>=a && r<=b){
			q=(r-a)/(b-a);
		}else 
			q=1.0;
		return(q);
	}

	
	public static Double owa(Double[] a,Double[] w){
		Arrays.sort(a, Collections.reverseOrder());
		Double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum+=a[i]*w[i];
		}
		return sum;
	}
	
	/*public static Consensus owa(Consensus[] a,Double[] w){
		Arrays.sort(a, Collections.reverseOrder());
		Consensus sum = new Consensus(0.0);
		for (int i = 0; i < a.length; i++) {
			sum=Consensus.sum(sum,Consensus.multiplyByScalar(a[i],w[i]));
		}
		return sum;
	}*/
	
	
	public static Double[][] similarityLevel(Integer m, Integer n, Double[][] preferences, Double[] agregated, 
			String distance){//, String quantifier) {
		
		//Integer npref=Math.floorDiv(n*n-n,2);//n*n-n;
		//Double numMa=(m*m-m)/2.0;//simplified from #factorial(M)/(factorial(2)*factorial(M-2));
		//Double[][] level1= new Double[m][npref];//number of paired expert comparisons X number of paired alternatives comparisons (all possible preferences for each expert)
		Double[][] level1= new Double[m][n];//number of paired expert comparisons X number of paired alternatives comparisons (all possible preferences for each expert)
		//Double[][] similarityLevel1= new Double[npref][m];//Consensus[][] similarityLevel1= new Consensus[npref][m];//
		Double[][] similarityLevel1= new Double[n][m];//Consensus[][] similarityLevel1= new Consensus[npref][m];//
		for (int mi = 0; mi < m; mi++) {
			//for (int i = 0; i < npref; i++) {
			for (int i = 0; i < n; i++) {
				Double a = preferences[mi][i];//.getConsensusDimension();
				Double b = agregated[i];//.getConsensusDimension();
				level1[mi][i] = distanceCalculation(new Double[]{a},new Double[]{b},distance);
				similarityLevel1[i][mi] = 1.0-level1[mi][i];
			}
		}
		
		return similarityLevel1;
	}
	
	
	public static Double[] consensusLevel1(Integer m, Integer n, Double[][] similarityLevel, String quantifier) {
		
		//Integer npref=Math.floorDiv(n*n-n,2);//n*n-n;
		
		Double[] w = new Double[m];
		for (int i = 0; i < m; i++) {
			//w[i]=Consensus.weight((double)((i+1)/m),quantifier)-Consensus.weight((double)((i)/m),quantifier);
			w[i]=Consensus.weight((i+1.0)/m,quantifier)-Consensus.weight(((i+0.0)/m),quantifier);
		}
		//Double[] consensusLevel1= new Double[npref];
		Double[] consensusLevel1= new Double[n];
		//for (int i = 0; i < npref; i++) {
		for (int i = 0; i < n; i++) {
			consensusLevel1[i]=owa(similarityLevel[i], w);
		}
		
		return consensusLevel1;
	}
	
	
	/*public static Double[] consensusLevel2(Integer n, String quantifier, Double[] consensusLevel1) {
		
		Double[] consensusLevel2= new Double[n];
		
		//consensus_level2=data.frame(matrix(0,1,N));
		
		Double[] w = new Double[n-1];
		for (int i = 0; i < n-1; i++) {
			w[i]=Consensus.weight((double)((i+1)/(n-1)),quantifier)-Consensus.weight((double)((i)/(n-1)),quantifier);
		}  
		
		Integer count = 1;
		for (int i = 0; i < n*n-n; i=i+n-1) {
			consensusLevel2[count]=owa(Arrays.copyOfRange(consensusLevel1, i, (i+n-1)),w);
			//consensus_level2[count]=owa(consensus_level1[i:(i+N-2)],w);//creio que use -2 para eliminar o 1 de i na soma i+N-2, mas em java i começa com zero, não precisando
			count++;
			
		}
		
		return consensusLevel2;
	}*/

	//public static Double consensusLevel3(Integer n, String quantifier, Double[] consensusLevel2) {
	public static Double consensusLevel2(Integer n, String quantifier, Double[] consensusLevel1) {
		Double[] w = new Double[n];
		for (int i = 0; i < n; i++) {
			//w[i]=Consensus.weight((double)((i+1)/n),quantifier)-Consensus.weight((double)((i)/n),quantifier);
			w[i]=Consensus.weight((i+1.0)/n,quantifier)-Consensus.weight(((i+0.0)/n),quantifier);
		}  
		
		return owa(consensusLevel1,w);
	}
	
	
	public static Double consensus(Integer m, Integer n, Double[][] preferences, Double[] agregated, 
			String distance, String quantifier, Integer level) {//
		
		Double[][] similarityLevel1=similarityLevel(m, n, preferences, agregated, distance);//, quantifier);

		Double[] consensusLevel1 = Consensus.consensusLevel1(m, n, similarityLevel1, quantifier);

		//Double[] consensusLevel2=Consensus.consensusLevel2(n, quantifier, consensusLevel1);// new Consensus[n];
		Double consensusLevel2_=Consensus.consensusLevel2(n, quantifier, consensusLevel1);// new Consensus[n];
		
		//Double consensusLevel3=Consensus.consensusLevel3(n, quantifier, consensusLevel2);//  owa(consensusLevel2,w);

//		if(level==2){
//			for (int i = 0; i < consensusLevel2.length; i++) {
//				if(i==0) {
//					consensusLevel3=consensusLevel2[i];
//				}
//				consensusLevel3=(consensusLevel3+consensusLevel2[i])/2.0;
//			}
//		}else 
		if(level==1){
			for (int i = 0; i < consensusLevel1.length; i++) {
				if(i==0) {
					consensusLevel2_=consensusLevel1[i];
				}
				consensusLevel2_=(consensusLevel2_+consensusLevel1[i])/2.0;
			}
		}
		  
		  
		return consensusLevel2_;
	}
	
	public Double[] getDimenssions() {
		return this.dimensions;
	}
	
	public static Double[] sum(Double[] a, Double[] b) {
		//Double[] dimensionsA = a.getDimenssions();
		//Double[] dimensionsB = b.getDimenssions();
		Double[] dimensionsResult = new Double[a.length];
		for (int i = 0; i < a.length; i++) {
			dimensionsResult[i] = a[i]+b[i];
		}
		return dimensionsResult;
	}
	
	public static Double[] subtraction(Double[] a, Double[] b) {
		return Consensus.sum(a, Consensus.invert(b));
	}

	private static Double[] invert(Double[] b) {
		return Consensus.multiplyByScalar(b,-1.0);
	}

	private static Double[] multiplyByScalar(Double[] a, double d) {
		Double[] b = new Double[a.length];
		for (int i = 0; i < b.length; i++) {
			b[i]=d;
		}
		return Consensus.multiply(a,b);
	}

	private static Double[] multiply(Double[] a, Double[] b) {
		// TODO Auto-generated method stub
		Double[] d = new Double[a.length];
		for (int i = 0; i < a.length; i++) {
			d[i]=a[i]*b[i];//vamos usar uma multiplicação simples à princípio
		}
		return d;
	}

	public int compareTo(Consensus o) {
		Double[] d = o.getDimenssions();
		Double resultO = 00.0;
		for (int i = 0; i < d.length; i++) {
			resultO += d[i];
		}
		resultO = resultO/d.length;
		d = this.getDimenssions();
		Double resultThis = 00.0;
		for (int i = 0; i < d.length; i++) {
			resultThis += d[i];
		}
		resultThis = resultThis/d.length;
		if(resultO > resultThis) {
			return 1;
		}
		if(resultO < resultThis) {
			return -1;
		}
		return 0;
	}

	public static Double[][] preferences(TreeMap<CSVPost, Double> opinions, TreeSet<CSVPost> thread) {
		Integer n = opinions.size();
		Integer m = thread.size();

		//Double[][] prefs = new Double[m][Math.floorDiv(n*n-n,2)];
		Double[][] prefs = new Double[m][n];

		CSVPost[] keys = opinions.keySet().toArray(new CSVPost[opinions.size()]);

		Integer[] positions = new Integer[n];
		int count=0;
		int countOpinions=0;
		for (Iterator<CSVPost> iterator = thread.iterator(); iterator.hasNext();) {
			CSVPost csvPost = iterator.next();
			if(csvPost.getOpinion()) {
				positions[countOpinions]=count+1;
				countOpinions++;
			}
			count++;
		}
		//int count = 0;
		int countPosts = 0;
		//Arrays.sort(keys, Collections.reverseOrder());
		//LocalDateTime firstOpition= thread.first().getCreatedAt_();
		//LocalDateTime lastOpition= thread.last().getCreatedAt_();
		//Double timeDistanceLast = Consensus.distanceCalculation(new Double[] {lastOpition.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000.0}, new Double[] {firstOpition.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000.0}, "euclidean");
		Double previousLower = 1.0;
		int previousLowerPosition = 0;
		for (Iterator<CSVPost> iterator = thread.iterator(); iterator.hasNext();) {
			CSVPost csvPost = iterator.next();
			boolean validProximity = false;
			Double currentLower = 1.0;
			int currentLowerPosition = 0;
			for (int i = 0; i < n; i++) {
				//for (int j = 0; j < n; j++) {
					//if(j>i) {
						//prefs[countPosts][count]=csvPost.getPreference(keys[i],keys[j]);sort
				
				prefs[countPosts][i]=csvPost.getPreference(keys[i],positions[i],countPosts+1,m);//,timeDistanceLast);
				if(prefs[countPosts][i] <= 0.70) {
					validProximity = true;
				}
				if(currentLower>prefs[countPosts][i]) {
					currentLower=prefs[countPosts][i];
					currentLowerPosition=i;
				}
				//count++;
					//}
				//}
			}
			double sentiment=csvPost.getSentiment();
			if(!validProximity&&sentiment>=5.0) {
				if(prefs[countPosts][previousLowerPosition]>previousLower) {
					prefs[countPosts][previousLowerPosition]=
							(prefs[countPosts][previousLowerPosition]*(CSVPost.maxSentiment_-sentiment)/(CSVPost.maxSentiment_-1.0))+
							(previousLower*(sentiment-1.0)/(CSVPost.maxSentiment_-1.0));
				}
			}
			previousLower=currentLower;
			previousLowerPosition=currentLowerPosition;
			countPosts++;
		}
		
		return prefs;
	}

	public static Double[] agregation(Integer m, Integer n, Double[][] preferences, String quantifier) {
		//Integer npref = Math.floorDiv(n*n-n,2);
		//Double[] agregated = new Double[npref];
		Double[] agregated = new Double[n];

		Double[] w = new Double[m];
		for (int i = 0; i < m; i++) {
			w[i]=Consensus.weight((i+1.0)/m,quantifier)-Consensus.weight(((i+0.0)/m),quantifier);
		} 
		
		//Double[][] prefs = new Double[npref][m];
		Double[][] prefs = new Double[n][m];
		
		//for (int i = 0; i < npref; i++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				prefs[i][j]=preferences[j][i];
			}
		}
		//agregated=apply(preferences,2,owa,w= w);

		//for (int i = 0; i < npref; i++) {
		for (int i = 0; i < prefs.length; i++) {
			agregated[i]=owa(prefs[i], w);
		}
		
		return agregated;
	}

	public static Double opinionDistance(Integer n, TreeSet<CSVPost> thread,
			Double[][] preferences, String quantifier) {


		Double[][] agregated = new Double[n][n];

		Double[] w = new Double[n];
		for (int i = 0; i < n; i++) {
			w[i]=Consensus.weight((i+1.0)/n,quantifier)-Consensus.weight(((i+0.0)/n),quantifier);
		} 
		
		//Double[][] prefs = new Double[npref][m];
		//Double[][] agregatedtrasp = new Double[n][n];
		

		Double[] agregatedLinear = new Double[n];
		
		//for (int i = 0; i < npref; i++) {
		int j = 0;
		for (int i = 0; i < n; i++) {
			j = 0;
			for (Iterator<CSVPost> iterator = thread.iterator(); iterator.hasNext();) {
				//for (int j = 0; j < m; j++) {
				CSVPost csvPost = iterator.next();
			
				if(csvPost.getOpinion()) {
					agregated[j][i]=preferences[j][i];
					//agregatedtrasp[i][j]=preferences[j][i];
					j++;
				}

				
			}
		}
		//agregated=apply(preferences,2,owa,w= w);

		for (int i = 0; i < n; i++) {
			agregatedLinear[i]=owa(agregated[i], w);
		}
		
		return owa(agregatedLinear, w);
	}
	
	
//	agregation=function(preferences,M,N,quantifier)
//			{
//			  w=array(0,M);
//			  agregated=data.frame(matrix(0,1,N*N-N));
//			  a=matrix(0,1,M);
//
//			  #print('Estoy en agregacion GDMR2')
//			  for(i in 1:M){
//			    w[i]=weight(i/M,quantifier)-weight((i-1)/M,quantifier);
//			  }
//
//			  preferences=preferences;
//			  #colnames(preferences) <- paste('X',1:(N*N-N));
//			  agregated=apply(preferences,2,owa,w= w);
//			  return(agregated);
//			}
	
//	generatePref=function(n,consistent)
//			{
//			  #pref= matrix(0,n,n);
//			  pref= matrix(0,1,n*n-n);
//
//			  for (i in 1:n){
//			    for(j in 1:n){
//			      if (i==j)
//			      {
//			        #pref[i,j]=0.5;
//			      }
//			      else
//			      {
//			        if(j>i)
//			        {
//
//			          #pref[i,j]=runif(1, 0, 1);
//			          pref[(i-1)*(n-1)+j-1]=runif(1, 0, 1);
//			          if (consistent)
//			          {
//			            #pref[j,i]=1-pref[i,j];
//			            pref[(j-1)*(n-1)+i]=1-pref[(i-1)*(n-1)+j-1];
//			          }
//
//			          else
//			          {
//			            #pref[j,i]=runif(1, 0, 1);
//			            pref[(j-1)*(n-1)+i]=runif(1, 0, 1);
//			          }
//
//			        }
//			      }
//			    }
//
//			  }
//			  return (pref);
//			}

}
