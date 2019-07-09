package mooc_forum_analytics;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomStringUtils;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import consensus.Consensus;
import consensus.DiscoDistance;
import consensus.LevenshteinDistance;
import consensus.WordNetDistance;

public class CSVPost implements Comparable<CSVPost>{
	
	public static final Double maxSentiment_ = 7.0;

	public static final Double maxConfusion_ = 7.0;

	public static final Double maxUrgency_ = 7.0;

	//private TreeSet<CSVPost> comments = null;
	
	public CSVPost() {
		//this.comments = new TreeSet<CSVPost>();
		this.opinions_ = new TreeMap<String, Double>();
		this.postType = "";
	}
	
	public boolean equals(Object csvPost) {
		if(csvPost==null) {
			return false;
		}
		if(!(csvPost instanceof CSVPost)) {
			return super.equals(csvPost);
		}
		return ((CSVPost)csvPost).getForumPostID().equals(this.getForumPostID());
	}
	
	/*public CSVPost(boolean demandReview, String why) {
		this();

		this.text = "                                                 ";
		this.text+= "                                                                  ";
		this.opinion="0";
		this.question="0";
		this.answer="0";
		this.sentiment="0";
		this.confusion="0";
		this.urgency="0";
		this.courseType="-";
		this.forumPostID="-";
		this.courseDisplayName="-";
		this.forumUID="-";
		this.postType="-";
		this.anonymous="0";
		this.anonymousToPeers="0";
		this.commentThreadID="";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		this.createdAt=formatter.format(LocalDateTime.now());
		this.upCount=0;
		this.reads=0;
		this.staffCandidate=false;
		
	}*/
	
	/*public CSVPost(boolean demandReview, //title
			
			boolean staffCandidatePresence,
			String countOpinions,
			String confusionAverage,
			String confusionMedian,
			String minConfusion,
			String maxConfusion,
			String urgencyAverage,
			String urgencyMedian,
			String minUrgency,
			String maxUrgency,
			String sentimentAverage,
			String sentimentMedian,
			String minSentiment,
			String maxSentiment,
			String consensus,
			
			String why) {
		this();

		this.text = "         [OTHER THREAD FROM HERE]";
		if(demandReview) {
			this.text+= ":[DEMAND REVIEW]";
			if((why!=null)?(!why.isBlank()):(false)) {
				this.text+= ":["+why+"]";
			}
		}else {
			this.text+= "                ";
		}
		this.text+= "                                                                  ";
		this.opinion=countOpinions;
		this.question=sentimentAverage;
		this.answer=sentimentMedian;
		this.sentiment=minSentiment;
		this.confusion=maxSentiment;
		this.urgency=confusionAverage;
		this.courseType=confusionMedian;
		this.forumPostID=minConfusion;
		this.courseDisplayName=maxConfusion;
		this.forumUID=urgencyAverage;
		this.postType=urgencyMedian;
		this.anonymous=minUrgency;
		this.anonymousToPeers=maxUrgency;
		this.commentThreadID=consensus;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		this.createdAt=formatter.format(LocalDateTime.now());
		this.upCount=0;
		this.reads=0;
		this.staffCandidate=false;
	}*/

	public static String[] getColumns() {
		return new String[] { "commentthreadid", "forumpostid", "forumuid", "staffcandidate", "staff", "nonstaff", "Sentiment", "Confusion", "Urgency", "Opinion", "Question", "Answer", "demandreview", "createdat", 
				"staffcandidatepresence", "countopinions", "confusionaverage", "confusionmedian", "minconfusion", "maxconfusion", "urgencyaverage", "urgencymedian", "minurgency", "maxurgency", "sentimentaverage", "sentimentmedian", "minsentiment", "maxsentiment", "consensus",
				"agregatedstatus", "firstopinionid", "firstopinionsimilarity", "opinionproximity",
				"Text", "upcount", "reads","CourseType", "coursedisplayname", "posttype", "anonymous", "anonymoustopeers", "opinions", "demandreviewreason" };
	}
	
    public String getFirstOpinionSimilarity() {
		return firstOpinionSimilarity;
	}

	public void setFirstOpinionSimilarity(String firstOpinionSimilarity) {
		this.firstOpinionSimilarity = firstOpinionSimilarity;
	}

	@CsvBindByName(column = "Text", required = true)
    private String text;

    @CsvBindByName(column = "Opinion", required = true)
    private String opinion;

    @CsvBindByName(column = "Question", required = true)
    private String question;

	@CsvBindByName(column = "Answer", required = true)
    private String answer;

    @CsvBindByName(column = "Sentiment", required = true)
    private String sentiment;

    @CsvBindByName(column = "Confusion", required = true)
    private String confusion;

    @CsvBindByName(column = "Urgency", required = true)
    private String urgency;

    @CsvBindByName(column = "CourseType", required = true)
    private String courseType;

    @CsvBindByName(column = "forumpostid", required = true)
    private String forumPostID;

    @CsvBindByName(column = "coursedisplayname", required = true)
    private String courseDisplayName;

    @CsvBindByName(column = "forumuid", required = true)
    private String forumUID;

    @CsvBindByName(column = "createdat", required = true)
    private String createdAt;

    @CsvBindByName(column = "posttype", required = true)
    private String postType;

    @CsvBindByName(column = "anonymous", required = true)
    private String anonymous;

	@CsvBindByName(column = "anonymoustopeers", required = true)
    private String anonymousToPeers;

    @CsvBindByName(column = "upcount", required = true)
    private Integer upCount;

    @CsvBindByName(column = "commentthreadid")
    private String commentThreadID;

    @CsvBindByName(column = "reads", required = true)
    private Integer reads;

    @CsvBindByName(column = "staffcandidate", required = false)
	private boolean staffCandidate;

    @CsvBindByName(column = "staff", required = false)
	private boolean staff;

    @CsvBindByName(column = "nonstaff", required = false)
	private boolean nonStaff;

    //about the thread
    
    @CsvBindByName(column = "demandreview", required = false)
    boolean demandReview;
    
    @CsvBindByName(column = "staffcandidatepresence", required = false)
	boolean staffCandidatePresence;
    
    @CsvBindByName(column = "countopinions", required = false)
	String countOpinions;
    
    @CsvBindByName(column = "confusionaverage", required = false)
	String confusionAverage;
    
    @CsvBindByName(column = "confusionmedian", required = false)
	String confusionMedian;
    
    @CsvBindByName(column = "minconfusion", required = false)
	String minConfusion;
    
    @CsvBindByName(column = "maxconfusion", required = false)
	String maxConfusion;
    
    @CsvBindByName(column = "urgencyaverage", required = false)
	String urgencyAverage;
    
    @CsvBindByName(column = "urgencymedian", required = false)
	String urgencyMedian;
    
    @CsvBindByName(column = "minurgency", required = false)
	String minUrgency;
    
    @CsvBindByName(column = "maxurgency", required = false)
	String maxUrgency;
    
    @CsvBindByName(column = "sentimentaverage", required = false)
	String sentimentAverage;
    
    @CsvBindByName(column = "sentimentmedian", required = false)
	String sentimentMedian;
    
    @CsvBindByName(column = "minsentiment", required = false)
	String minSentiment;
    
    @CsvBindByName(column = "maxsentiment", required = false)
	String maxSentiment;
    
    @CsvBindByName(column = "consensus", required = false)
	String consensus;

    @CsvBindByName(column = "demandreviewreason", required = false)
	String demandReviewReason;

    @CsvBindByName(column = "opinions", required = false)
	private String opinions;

    @CsvBindByName(column = "firstopinionid", required = false)
	private String firstOpinionID;

    @CsvBindByName(column = "firstopinionsimilarity", required = false)
	private String firstOpinionSimilarity;

    @CsvBindByName(column = "agregatedstatus", required = false)
	private String agregatedStatus;

    @CsvBindByName(column = "opinionproximity", required = false)
	private String opinionProximity;
    
    private LocalDateTime createdAt_;

	public String getOpinionProximity() {
		return opinionProximity;
	}

	public void setOpinionProximity(String opinionProximity) {
		this.opinionProximity=opinionProximity;
	}

	public String getFirstOpinionID() {
		return firstOpinionID;
	}

	public void setFirstOpinionID(String firstOpinionID) {
		this.firstOpinionID = firstOpinionID;
	}

	public String getAgregatedStatus() {
		return agregatedStatus;
	}

	public void setAgregatedStatus(String agregatedStatus) {
		this.agregatedStatus = agregatedStatus;
	}

	public TreeMap<String, Double>  opinions_;


	public String getOpinions() {
		return opinions_.toString();
	}

	public void setOpinions(String opinions) {
		this.opinions_ = new TreeMap<String, Double>();
		if(opinions==null||opinions.startsWith("{")||!opinions.endsWith("}"))
			this.opinions_ = new TreeMap<String, Double>();
		opinions=opinions.substring(1, opinions.length()-1);
		String[] opinionSet = opinions.split(", ");
		for (int i = 0; i < opinionSet.length; i++) {
			String[] opinionConsensus = opinionSet[i].split("=");
			if(opinionConsensus.length==2) {
				this.opinions_.put(opinionConsensus[0], Double.valueOf(opinionConsensus[1]));
			}
		}
		//this.opinions = opinions;
	}

    public boolean isStaff() {
		return staff;
	}

	public void setStaff(boolean staff) {
		this.staff = staff;
	}

    public boolean isNonStaff() {
		return nonStaff;
	}

	public void setNonStaff(boolean nonStaff) {
		this.nonStaff = nonStaff;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getOpinion() {
		
		return (this.opinion!=null)?(this.opinion.equals("1")):(false);
	}

	public void setOpinion(String opinion) {
		if(opinion!=null) {
			if(opinion.equalsIgnoreCase("true"))
				opinion="1";
			if(opinion.equalsIgnoreCase("false"))
				opinion="0";
		}
		this.opinion = opinion;
	}

	public boolean getAnswer() {
		return (this.answer!=null)?(this.answer.equals("1")):(false);
	}

	public void setAnswer(String answer) {
		if(answer!=null) {
			if(answer.equalsIgnoreCase("true"))
				answer="1";
			if(answer.equalsIgnoreCase("false"))
				answer="0";
		}
		this.answer = answer;
	}

    public boolean getQuestion() {
		return (this.question!=null)?(this.question.equals("1")):(false);
	}

	public void setQuestion(String question) {
		if(question!=null) {
			if(question.equalsIgnoreCase("true"))
				question="1";
			if(question.equalsIgnoreCase("false"))
				question="0";
		}
		this.question = question;
	}

	public Double getSentiment() {
		if(this.sentiment.contains(",")) {
			return Double.valueOf(this.sentiment.replace(",", "."));
		}else {
			return Double.valueOf(this.sentiment);
		}
		//return Double.valueOf(this.sentiment.replace(".", "").replace(",", "."));
	}

	public void setSentiment(String sentiment) {
		
		//sentiment = sentiment.replace(".", "").replace(",", ".");
		
		this.sentiment = sentiment;//Float.valueOf(sentiment);
	}

	public Double getConfusion() {
		if(this.confusion.contains(",")) {
			return Double.valueOf(this.confusion.replace(",", "."));
		}else {
			return Double.valueOf(this.confusion);
		}
		//return Double.valueOf(this.confusion.replace(".", "").replace(",", "."));
	}

	public void setConfusion(String confusion) {
		this.confusion = confusion;
	}

	public Double getUrgency() {
		if(this.urgency.contains(",")) {
			return Double.valueOf(this.urgency.replace(",", "."));
		}else {
			return Double.valueOf(this.urgency);
		}
		//return Double.valueOf(this.urgency.replace(".", "").replace(",", "."));
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getForumPostID() {
		if(this.forumPostID != null) {
			if(this.forumPostID.equalsIgnoreCase("none")||this.forumPostID.isEmpty()) {
				return null;
			}
		}else {
			return null;
		}
		return this.forumPostID;
	}

	public void setForumPostID(String forumPostID) {
		this.forumPostID = forumPostID;
	}

	public String getCourseDisplayName() {
		return courseDisplayName;
	}

	public void setCourseDisplayName(String courseDisplayName) {
		this.courseDisplayName = courseDisplayName;
	}

	public String getForumUID() {
		if(this.forumUID != null) {
			if(this.forumUID.equalsIgnoreCase("none")||this.forumUID.isEmpty()) {
				return null;
			}
		}else {
			return null;
		}
		return this.forumUID;
	}

	public void setForumUID(String forumUID) {
		this.forumUID = forumUID.trim();
	}

	public String getCreatedAt() {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//LocalDateTime createAtDateTime = LocalDateTime.parse(this.createdAt,formatter);
		
		return this.createdAt;
	}

	public void setCreatedAt(String createdAtToString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		LocalDateTime createAtDateTime = LocalDateTime.parse(createdAtToString,formatter);
		
		this.createdAt = createdAtToString;
		
		this.createdAt_ = createAtDateTime;
	}

	public LocalDateTime getCreatedAt_() {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//LocalDateTime createAtDateTime = LocalDateTime.parse(this.createdAt,formatter);
		
		return this.createdAt_;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		if(postType==null) {
			return;
		}
		this.postType = postType;
	}

	public boolean getAnonymous() {
		return (this.anonymous!=null)?(this.anonymous.equalsIgnoreCase("VERDADEIRO")):(false);
	}

	public void setAnonymous(String anonymous) {
		if(anonymous!=null) {
			if(anonymous.equalsIgnoreCase("true"))
				anonymous="VERDADEIRO";
			if(anonymous.equalsIgnoreCase("false"))
				anonymous="FALSO";
		}
		this.anonymous = anonymous;
	}

	public boolean getAnonymousToPeers() {
		return (this.anonymousToPeers!=null)?(this.anonymousToPeers.equalsIgnoreCase("VERDADEIRO")):(false);
	}

	public void setAnonymousToPeers(String anonymousToPeers) {
		if(anonymousToPeers!=null) {
			if(anonymousToPeers.equalsIgnoreCase("true"))
				anonymousToPeers="VERDADEIRO";
			if(anonymousToPeers.equalsIgnoreCase("false"))
				anonymousToPeers="FALSO";
		}
		this.anonymousToPeers = anonymousToPeers;
	}

	public Integer getUpCount() {
		return upCount;
	}

	public void setUpCount(Integer upCount) {
		this.upCount = upCount;
	}

	public String getCommentThreadID() {
		if(commentThreadID != null) {
			if(commentThreadID.equalsIgnoreCase("none")||this.commentThreadID.isEmpty()) {
				return null;
			}
		}else {
			return null;
		}
		return commentThreadID;
	}

	public void setCommentThreadID(String commentThreadID) {
		this.commentThreadID = commentThreadID.trim();
	}

	public Integer getReads() {
		return reads;
	}

	public void setReads(Integer reads) {
		this.reads = reads;
	}

	public int compareTo(CSVPost o) {
		if(this.getCreatedAt_().isAfter(o.getCreatedAt_())) {
			return -1;
		}else if(this.getCreatedAt_().isBefore(o.getCreatedAt_())) {
			return 1;
		}else {
			return this.getForumPostID().compareTo(o.getForumPostID());
		}
	}

	public String toString() {
		return this.getForumPostID();
	}

	public void setStaffCandidate(boolean staffCandidate) {
		if(staffCandidate) {
			if(!this.isStaff()&&!this.isNonStaff()) {
				this.staffCandidate = staffCandidate;
			}
		}else {
			this.staffCandidate = staffCandidate;
		}
	}

	//public void setOpinions(TreeMap<CSVPost, Double> opinions) {
	//	this.opinions = opinions;
	//}

	public boolean isDemandReview() {
		return demandReview;
	}

	public void setDemandReview(boolean demandReview) {
		this.demandReview = demandReview;
	}

	public boolean isStaffCandidatePresence() {
		return staffCandidatePresence;
	}

	public void setStaffCandidatePresence(boolean staffCandidatePresence) {
		this.staffCandidatePresence = staffCandidatePresence;
	}

	public String getCountOpinions() {
		return countOpinions;
	}

	public void setCountOpinions(String countOpinions) {
		this.countOpinions = countOpinions;
	}

	public String getConfusionAverage() {
		return confusionAverage;
	}

	public void setConfusionAverage(String confusionAverage) {
		this.confusionAverage = confusionAverage;
	}

	public String getConfusionMedian() {
		return confusionMedian;
	}

	public void setConfusionMedian(String confusionMedian) {
		this.confusionMedian = confusionMedian;
	}

	public String getMinConfusion() {
		return minConfusion;
	}

	public void setMinConfusion(String minConfusion) {
		this.minConfusion = minConfusion;
	}

	public String getMaxConfusion() {
		return maxConfusion;
	}

	public void setMaxConfusion(String maxConfusion) {
		this.maxConfusion = maxConfusion;
	}

	public String getUrgencyAverage() {
		return urgencyAverage;
	}

	public void setUrgencyAverage(String urgencyAverage) {
		this.urgencyAverage = urgencyAverage;
	}

	public String getUrgencyMedian() {
		return urgencyMedian;
	}

	public void setUrgencyMedian(String urgencyMedian) {
		this.urgencyMedian = urgencyMedian;
	}

	public String getMinUrgency() {
		return minUrgency;
	}

	public void setMinUrgency(String minUrgency) {
		this.minUrgency = minUrgency;
	}

	public String getMaxUrgency() {
		return maxUrgency;
	}

	public void setMaxUrgency(String maxUrgency) {
		this.maxUrgency = maxUrgency;
	}

	public String getSentimentAverage() {
		return sentimentAverage;
	}

	public void setSentimentAverage(String sentimentAverage) {
		this.sentimentAverage = sentimentAverage;
	}

	public String getSentimentMedian() {
		return sentimentMedian;
	}

	public void setSentimentMedian(String sentimentMedian) {
		this.sentimentMedian = sentimentMedian;
	}

	public String getMinSentiment() {
		return minSentiment;
	}

	public void setMinSentiment(String minSentiment) {
		this.minSentiment = minSentiment;
	}

	public String getMaxSentiment() {
		return maxSentiment;
	}

	public void setMaxSentiment(String maxSentiment) {
		this.maxSentiment = maxSentiment;
	}

	public String getConsensus() {
		return consensus;
	}

	public void setConsensus(String consensus) {
		this.consensus = consensus;
	}

	public String getDemandReviewReason() {
		return demandReviewReason;
	}

	public void setDemandReviewReason(String demandReviewReason) {
		this.demandReviewReason = demandReviewReason;
	}

	public Boolean getStaffCandidate() {
		return this.staffCandidate;
	}

	/*public Consensus getConsensusDimension() {//inclua aqui os pesos das dimmenssões, se quiser
		// tem que ponderar todos os campos em função da data e não colocar a data como mais um campo
		// e normalizar entre zero e 1
		
		return null;
	}*/

	public Double getPreference(CSVPost a, int opinionPosition, int position, int opinionsSize) {//, Double timeDistanceLast) {//LocalDateTime lastOpition) {

		//Double[] dimensions=new Double[4];//sentiment,confusion,urgency,time distance
		//Double[] dimensions=new Double[3];//sentiment,confusion,urgency
		Double[] dimensions=new Double[3];//sentiment,confusion,position
		Double sentiment = Consensus.distanceCalculation(new Double[] {this.getSentiment()-1.0}, new Double[] {a.getSentiment()-1.0}, "euclidean");

		//dimensions[0]=(maxSentiment_-sentiment)/(maxSentiment_);
		dimensions[0]=sentiment/(maxSentiment_-1.0);
		
		//Double confusion = Consensus.distanceCalculation(new Double[] {this.getConfusion()}, new Double[] {a.getConfusion()}, "euclidean");

		//dimensions[1]=(maxConfusion_-confusion)/(maxConfusion_);
		dimensions[1]=(this.getConfusion()-1)/(maxConfusion_-1.0);
		
		//Double urgency = Consensus.distanceCalculation(new Double[] {this.getUrgency()}, new Double[] {a.getUrgency()}, "euclidean");

		//dimensions[2]=(maxUrgency_-urgency)/(maxUrgency_);
		
		//Double timeDistance = Consensus.distanceCalculation(new Double[] {this.getCreatedAt_().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000.0}, new Double[] {a.getCreatedAt_().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000.0}, "euclidean");

		//if(timeDistanceLast==0.0)
		//	dimensions[3]=1.0;
		//else
		//	dimensions[3]=(timeDistanceLast-timeDistance)/(timeDistanceLast);
		
		//return dimensions[0]*0.52+dimensions[1]*0.24+dimensions[2]*0.24;//+dimensions[3]*0.3;
		
		
		
		
		
		
		
		
		
		
		/*
		 * Levenshtein Distance
		 */
		/*org.apache.commons.text.similarity.LevenshteinDistance ld = org.apache.commons.text.similarity.LevenshteinDistance.getDefaultInstance();
		
		Integer textDistance = ld.apply(this.getText(), a.getText());
		Integer maxTextDistance = ld.apply(RandomStringUtils.random(this.getText().length(), true, false), 
				RandomStringUtils.random(this.getText().length(), false, true));
		
		if(textDistance > maxTextDistance) {
			maxTextDistance = ld.apply(RandomStringUtils.random(a.getText().length(), true, false), 
					RandomStringUtils.random(a.getText().length(), false, true));
		}
		
		dimensions[2]=((maxTextDistance-textDistance)*1.0)/maxTextDistance;
		
		if(dimensions[2] < 0.0) {
			dimensions[2] = 0.0;
		}*/
		
		/*
		 * DISCO distance
		 */
		/*DiscoDistance dd = new DiscoDistance();
		
		double textDistance = dd.calculate(this.getText(), a.getText());
		double maxTextDistance = 1.0;
		
		if(textDistance > maxTextDistance) {
			textDistance = 1.0;
		}
		
		dimensions[2]=textDistance/maxTextDistance;
		
		if(dimensions[2] < 0.0) {
			dimensions[2] = 0.0;
		}*/
		
		/*
		 * Wordnet distance
		 */
		WordNetDistance wnd = new WordNetDistance();
		
		double textDistance = wnd.calculate(this.getText(), a.getText());
		double maxTextDistance = 1.0;
		
		if(textDistance > maxTextDistance) {
			textDistance = 1.0;
		}
		
		dimensions[2]=textDistance/maxTextDistance;
		
		if(dimensions[2] < 0.0) {
			dimensions[2] = 0.0;
		}
		
		
		
		
		
		
		
		//Double positionDistance = Consensus.distanceCalculation(new Double[] {position+0.0}, new Double[] {opinionPosition+0.0}, "euclidean");

		//dimensions[2]=positionDistance/opinionsSize;
		
		
		/*if(dimensions[0]>0.5) {
			return dimensions[0]-(dimensions[0]-0.5)*(dimensions[1]+dimensions[2])/2.0;
		}else {
			return dimensions[0]+(0.5-dimensions[0])*(dimensions[1]+dimensions[2])/2.0;
		}*/
		
		
		if(dimensions[0]>0.5) {
			Double result = dimensions[2];//-(dimensions[2]-0.5)*dimensions[1];
			return result-(result-0.5)*dimensions[0]*2;
		}else {
			Double result = dimensions[2];//+(0.5-dimensions[2])*dimensions[1];
			return result+(0.5-result)*dimensions[0]*2;
		}
	}

	public void addConsensusForOpinion(Double o, CSVPost a) {

		//this.opinionA.add(a);
		//this.opinionB.add(b);
		this.opinions_.put(a.toString(), o);
		this.setCountOpinions(String.valueOf(this.opinions_.size()));
	}

	// Getters and Setters (Omitted for brevity)
}
