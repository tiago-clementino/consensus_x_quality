package mooc_forum_analytics;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.common.collect.TreeMultimap;

import consensus.Consensus;

public class ForumThread implements Comparable<ForumThread>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1102281946830513387L;

	private TreeSet<CSVPost> thread = null;
	
	private TreeMap<CSVPost, Double> opinions = null;//consensus to opinion
	
	private Integer countOpinions = null;
	
	//private Float confusionAverage = null;

	private Double confusionMedian = null;
	
	private Boolean demandReview = null;//caso haja mais de uma opinião ou a opinião não é o primeiro post
	
	private Boolean staffCandidatePresence = null;

	private String reviewReason = null;

	private Double confusionAverage;

	private Double curgencyAverage;

	private Double sentimentAverage;

	private Double minConfusion;

	private Double maxConfusion;

	private Double urgencyMedian;

	private Double minUrgency;

	private Double maxUrgency;

	private Double maxSentiment;

	private Double minSentiment;

	private Double sentimentMedian;

	private Double consensus;

	private int setStaffPresence = 0;
	
	public ForumThread(){
		this.thread = new TreeSet<CSVPost>();
		this.opinions = new TreeMap<CSVPost, Double>();
		this.countOpinions = 0;
		this.confusionAverage = 0.0;
		this.confusionMedian = 0.0;
		this.demandReview = false;
		this.staffCandidatePresence = false;
	}
	
	public Integer getCountOpinions() {
		return countOpinions;
	}

	public Double getConfusionAverage() {
		return confusionAverage;
	}

	public Double getConfusionMedian() {
		return confusionMedian;
	}

	public Boolean getDemandReview() {
		return demandReview;
	}

	public void setStaffCandidatePresence(boolean presence) {
		this.staffCandidatePresence = presence;
	}

	public Boolean getStaffCandidatePresence() {
		return staffCandidatePresence;
	}

	public int compareTo(ForumThread o) {
		int result = this.thread.first().compareTo(o.first());
		if(result != 0) {
			return result;
		}
		for (Iterator<CSVPost> iterator = this.thread.iterator(); iterator.hasNext();) {
			CSVPost comment = iterator.next();
			CSVPost greater = o.ceiling(comment);
			if((result=comment.compareTo(greater)) != 0){
				return result;
			}
		}
		return 0;
	}

	public CSVPost ceiling(CSVPost comment) {
		return this.thread.ceiling(comment);
	}

	public CSVPost first() {
		return this.thread.first();
	}

	public Iterator<CSVPost> iterator() {
		return this.thread.iterator();
	}

	public CSVPost floor(CSVPost csvPost) {
		if(this.thread == null) {
			this.thread = new TreeSet<CSVPost>();
		}

		return this.thread.floor(csvPost);		
	}

	public void add(CSVPost csvPost) {
		if(this.thread == null) {
			this.thread = new TreeSet<CSVPost>();
		}
		if(csvPost.getOpinion()) {
			this.addOpinion(csvPost);
		}
		this.thread.add(csvPost);
		//this.calculeIndicators();
		
	}

	void calculeIndicators() {
		Integer total = this.thread.size();
		Integer medianPosition1 = Math.floorDiv(total+1, 2);
		Integer medianPosition2 = Math.floorDiv(total+2, 2);
		Double confusionSum = 0.0;
		Double confusionMedian = 0.0;
		Double minConfusion = 0.0;
		Double maxConfusion = 0.0;
		Double urgencySum = 0.0;
		Double urgencyMedian = 0.0;
		Double minUrgency = 0.0;
		Double maxUrgency = 0.0;
		Double sentimentSum = 0.0;
		Double sentimentMedian = 0.0;
		Double minSentiment = 0.0;
		Double maxSentiment = 0.0;
		int count = 0;
		for (Iterator<CSVPost> iterator = this.thread.iterator(); iterator.hasNext();) {
			CSVPost currentPost = iterator.next();
			Double urgency = currentPost.getUrgency();
			Double confusion = currentPost.getConfusion();
			Double sentiment = currentPost.getSentiment();
			confusionSum += confusion;
			urgencySum += urgency;
			sentimentSum += sentiment;
			if(maxConfusion < confusion) {
				maxConfusion = confusion;
			}
			if(minConfusion > confusion) {
				minConfusion = confusion;
			}
			if(maxUrgency > urgency) {
				maxUrgency = urgency;
			}
			if(minUrgency < urgency) {
				minUrgency = urgency;
			}
			if(maxSentiment > sentiment) {
				maxSentiment = sentiment;
			}
			if(minSentiment < sentiment) {
				minSentiment = sentiment;
			}
			count++;
			if(count == medianPosition1) {
				confusionMedian = confusion;
				urgencyMedian = urgency;
				sentimentMedian = sentiment;
			}else if(count == medianPosition2) {
				confusionMedian = (confusionMedian + confusion)/2.0f;
				urgencyMedian = (urgencyMedian + urgency)/2.0f;
				sentimentMedian = (sentimentMedian + sentiment)/2.0f;
			}			
		}
		this.confusionAverage = confusionSum/total;
		this.curgencyAverage = urgencySum/total;
		this.sentimentAverage = sentimentSum/total;
		
		this.confusionMedian = confusionMedian;
		this.minConfusion = minConfusion;
		this.maxConfusion = maxConfusion;
		this.urgencyMedian = urgencyMedian;
		this.minUrgency = minUrgency;
		this.maxUrgency = maxUrgency;
		this.sentimentMedian = sentimentMedian;
		this.minSentiment = minSentiment;
		this.maxSentiment = maxSentiment;
		
		for (Iterator<CSVPost> iterator = this.thread.iterator(); iterator.hasNext();) {
			CSVPost currentPost = iterator.next();
			currentPost.setConfusionAverage(this.confusionAverage.toString());
			currentPost.setConfusionMedian(this.confusionMedian.toString());
			currentPost.setMaxConfusion(this.maxConfusion.toString());
			currentPost.setMinConfusion(this.minConfusion.toString());

			currentPost.setUrgencyAverage(this.curgencyAverage.toString());
			currentPost.setUrgencyMedian(this.urgencyMedian.toString());
			currentPost.setMaxUrgency(this.minUrgency.toString());
			currentPost.setMinUrgency(this.maxUrgency.toString());

			currentPost.setSentimentAverage(this.sentimentAverage.toString());
			currentPost.setSentimentMedian(this.sentimentMedian.toString());
			currentPost.setMaxSentiment(this.minSentiment.toString());
			currentPost.setMinSentiment(this.maxSentiment.toString());
			
			if(this.getDemandReview()) {
				currentPost.setDemandReview(this.getDemandReview());
				currentPost.setDemandReviewReason(this.getReviewReason());
			}
		}
	}

	private void addOpinion(CSVPost csvPost) {
		if(this.countOpinions==0 && this.thread.size()==0) {
			this.opinions.put(csvPost, 1.0);
			this.countOpinions++;
		}else if(this.countOpinions==0 && this.thread.size()!=0) {
			//Float consensus = this.calculeConsensus(csvPost);//calcula consenso entre esta opiniãpo e sentimento das demais postagens
			//this.opinions.put(csvPost, consensus);
			this.opinions.put(csvPost, 0.0);
			this.countOpinions++;
			this.demandReview=true;
			this.setReviewReason("first opinion is not the first comment");
		}else {//if(this.countOpinions!=0 && this.thread.size()!=0) {
			//Double consensus = this.calculeConsensus(csvPost);//aqui este método mede se os sentimentos entre duas opini~oes são opostos ou semelhantes, caso haja mais de duas, revise calculo de consenso.
			//this.opinions.put(csvPost, consensus);
			this.opinions.put(csvPost, 0.0);
			this.countOpinions++;
			this.demandReview=true;
			this.setReviewReason("more than one opinion");
		}
	}

	private Float calculeConsensus(CSVPost csvPost) {
		// TODO Auto-generated method stub
		return 1.0f;
	}

	public void calculeConsensusGeneral() {
		Integer n = this.opinions.size();
		Integer m = this.thread.size();
		Double[][] preferences = Consensus.preferences(this.opinions,this.thread);
		//média da proximidade entre as opiniões
		Double opinionDistance = Consensus.opinionDistance(n,this.thread,preferences, "most");
		Double[] agregated = Consensus.agregation(m,n,preferences,"most");
		Double[][] similarityLevel1 = Consensus.similarityLevel(m, n, preferences, agregated, "euclidean");//, "most");
		Double[] consensusLevel1 = Consensus.consensusLevel1(m, n, similarityLevel1, "most");
		//Double[] consensusLevel2 = Consensus.consensusLevel2(n, "most", consensusLevel1);
		//Double consensus = Consensus.consensusLevel3(n, "most", consensusLevel2);
		Double consensus = Consensus.consensusLevel2(n, "most", consensusLevel1);
		this.consensus=consensus;
		CSVPost[] thread = this.thread.toArray(new CSVPost[this.thread.size()]);
		//CSVPost[] keys = this.opinions.keySet().toArray(new CSVPost[this.opinions.size()]);
		Set<CSVPost> keys = this.opinions.keySet();
		int count = 0;
		for (Iterator<CSVPost> iterator = keys.iterator(); iterator.hasNext();) {
			CSVPost csvPost = iterator.next();
			this.opinions.put(csvPost, consensusLevel1[count]);
			count++;
		}

		int opinionCount = 0;
		for (int i = 0; i < thread.length; i++) {
			int j = 0;
			thread[i].setConsensus(consensus.toString());
			thread[i].setOpinionProximity(opinionDistance.toString());
			if(thread[i].getOpinion()) {
				thread[i].setAgregatedStatus(String.valueOf(agregated[opinionCount]));
				opinionCount++;
			}
			CSVPost opinionWithHigherSimilarity = null;
			Double higherSimilarity = 0.0;
			for (Iterator<CSVPost> iterator = keys.iterator(); iterator.hasNext();) {
				CSVPost csvPost = iterator.next();
			//for (int j = 0; j < similarityLevel1.length; j++) {
				///////////////////////////////////////////
				if(preferences[i][j]<0.0) {
					System.out.println(csvPost.getForumPostID());
					if(higherSimilarity==0.0) {
						higherSimilarity=preferences[i][j];
						opinionWithHigherSimilarity=csvPost;
					}
				}
				///////////////////////////////////////////
				if(preferences[i][j]>=higherSimilarity) {
					higherSimilarity=preferences[i][j];
					opinionWithHigherSimilarity=csvPost;
				}
				//thread[i].addConsensusForOpinion(similarityLevel1[j][i],csvPost);
				thread[i].addConsensusForOpinion(preferences[i][j],csvPost);
				j++;
			}
			if(opinionWithHigherSimilarity==null||thread[i]==null) {
				System.out.println("aqui");
			}
			thread[i].setFirstOpinionID(opinionWithHigherSimilarity.getForumPostID());
			thread[i].setFirstOpinionSimilarity(String.valueOf(higherSimilarity));
		}
	}

	private void setReviewReason(String reviewReason) {
		if(this.reviewReason == null) {
			this.reviewReason  = reviewReason;
		}else {
			this.reviewReason += " / "+reviewReason;
		}
	}

	public String getReviewReason() {
		return this.reviewReason;
	}

	public TreeSet<CSVPost> getThread() {
		return this.thread;
	}

	/*public CSVPost getTitlePost() {
		
		return new CSVPost(
				this.demandReview,
				this.staffCandidatePresence,
				this.countOpinions.toString(),
				this.confusionAverage.toString(),
				this.confusionMedian.toString(),
				this.minConfusion.toString(),
				this.maxConfusion.toString(),
				this.curgencyAverage.toString(),
				this.urgencyMedian.toString(),
				this.minUrgency.toString(),
				this.maxUrgency.toString(),
				this.sentimentAverage.toString(),
				this.sentimentMedian.toString(),
				this.minSentiment.toString(),
				this.maxSentiment.toString(),
				this.consensus.toString(),
				this.reviewReason);
	}*/

	/*public CSVPost getBlankPost() {
		return new CSVPost(this.demandReview,this.reviewReason);
	}*/

	public boolean isEmpty() {
		return this.thread.isEmpty();
	}

	public void incrementStaffPresence() {
		this.setStaffPresence++;
	}

	public int getStaffPresence() {
		return this.setStaffPresence;
	}

}
