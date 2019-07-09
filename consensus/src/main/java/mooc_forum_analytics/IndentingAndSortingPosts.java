package mooc_forum_analytics;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.common.collect.TreeMultimap;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class IndentingAndSortingPosts {

	private static boolean checkList(CSVPost csvPost) {
		if(csvPost == null) {
			return false;
		}
		String text = csvPost.getText();
		
		if(!csvPost.getAnswer() && !csvPost.getOpinion()) {
			return false;
		}
		
		return (text.toLowerCase().contains("professor"))||
				(text.toLowerCase().contains("prof "))||
				(text.toLowerCase().contains("staff"))||
				(text.toLowerCase().contains("certificate"))||
				(text.toLowerCase().contains("instructor"));
	}

	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_11.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_10.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_9.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_8.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_7.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_6.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_5.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_4.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_3.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste_2.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out_teste.csv";
	private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out.csv";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/stanfordMOOCForumPostsSet_teste.2.txt";
	//private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/stanfordMOOCForumPostsSet.2.txt";
	
	//private static final String CSV_WRITE_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out";
	//private static final String CSV_WRITE_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out-DISCO";
	private static final String CSV_WRITE_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/out-wordnet";

	private static Writer writerWithoutOpitions = null;
	private static Writer writerDemandReview = null;
	private static Writer writer1 = null;
	private static Writer writer2_5 = null;
	private static Writer writer6_10 = null;
	private static Writer writer11_ = null;
	private static Writer writer2_5_staff_candidates = null;
	private static Writer writer6_10_staff_candidates = null;
	private static Writer writer11__staff_candidates = null;
	
	private static StatefulBeanToCsv<CSVPost> beanToCsvWithoutOpitions = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsvDemandReview = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv1 = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv2_5 = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv6_10 = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv11_ = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv2_5_staff_candidates = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv6_10_staff_candidates = null;
	private static StatefulBeanToCsv<CSVPost> beanToCsv11__staff_candidates = null;
	
	private static final int countThreadsStart = 2541;//1881;//1651;//1291;
	private static final int countThreadsEnd = 4000;
	
	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");

	private static final boolean moreLegible = true;
	
	public static void main(String[] args) {
		
		System.out.println((new java.util.Date()).toString());
		try {
			
			String[] columns = CSVPost.getColumns();//new String[] { "productCode", "MFD", "EXD" };
	        
			ColumnPositionMappingStrategy<CSVPost> mappingStrategy = new ColumnPositionMappingStrategy<CSVPost>() {
				@Override
			    public String[] generateHeader() {
			        return CSVPost.getColumns();
			    }
			};
	        // Set mappingStrategy type to Product Type
	        mappingStrategy.setType(CSVPost.class);
	        // Fields in Product Bean
	        // Setting the colums for mappingStrategy
	        mappingStrategy.setColumnMapping(columns);

			
			
			writerWithoutOpitions = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"_without_opitions.csv"));
			//writerWithoutOpitions.append(columns.toString());
			
			beanToCsvWithoutOpitions = new StatefulBeanToCsvBuilder<CSVPost>(writerWithoutOpitions)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writerDemandReview = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"_demand_review.csv"));
			//writerDemandReview.append(columns.toString());
			
			beanToCsvDemandReview = new StatefulBeanToCsvBuilder<CSVPost>(writerDemandReview)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writer1 = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"_1.csv"));
			//writer1.append(columns.toString());
			
			beanToCsv1 = new StatefulBeanToCsvBuilder<CSVPost>(writer1)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writer2_5 = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"2_5.csv"));
			//writer2_5.append(columns.toString());
			
			beanToCsv2_5 = new StatefulBeanToCsvBuilder<CSVPost>(writer2_5)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writer2_5_staff_candidates = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"2_5_staff_candidates.csv"));
			//writer2_5.append(columns.toString());
			
			beanToCsv2_5_staff_candidates = new StatefulBeanToCsvBuilder<CSVPost>(writer2_5_staff_candidates)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writer6_10 = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"6_10.csv"));
			//writer6_10.append(columns.toString());
			
			beanToCsv6_10 = new StatefulBeanToCsvBuilder<CSVPost>(writer6_10)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
			
			writer6_10_staff_candidates = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"6_10_staff_candidates.csv"));
			//writer6_10_staff_candidates.append(columns.toString());
			
			beanToCsv6_10_staff_candidates = new StatefulBeanToCsvBuilder<CSVPost>(writer6_10_staff_candidates)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();

			writer11_ = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"11_.csv"));
			//writer11_.append(columns.toString());
			
			beanToCsv11_ = new StatefulBeanToCsvBuilder<CSVPost>(writer11_)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();

			writer11__staff_candidates = Files.newBufferedWriter(Paths.get(CSV_WRITE_FILE_PATH+"11__staff_candidates.csv"));
			//writer11__staff_candidates.append(columns.toString());
			
			beanToCsv11__staff_candidates = new StatefulBeanToCsvBuilder<CSVPost>(writer11__staff_candidates)
					.withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();

			
			/*String[] headerRecord = {"Text", "Opinion(1/0)", "Question(1/0)", "Answer(1/0)", 
					"Sentiment(1-7)", "Confusion(1-7)", "Urgency(1-7)", "CourseType", "forum_post_id", 
					"course_display_name", "forum_uid", "created_at", "post_type", "anonymous", "anonymous_to_peers", 
					"up_count", "comment_thread_id", "reads"};
			csvWriter.writeNext(headerRecord);*/

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Reader reader;

		int simpleCount = 0;
		int running = 0;
		int sucessCount = 0;
		CsvToBean<CSVPost> csvToBean = null;
		CSVPost csvPost = null;
		
		TreeMap<String, ForumThread> finalList = new TreeMap<String, ForumThread>();

		try {
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH), UTF8_CHARSET);
			
			
			csvToBean = new CsvToBeanBuilder<CSVPost>(reader)
                    .withType(CSVPost.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    //.withOrderedResults(true)
                    //.withSkipLines(1)
                    .build();
			
			
			//Iterator<CSVPost> csvPostIterator = csvToBean.iterator();
						
			//TreeSet<CSVPost> finalList = new TreeSet<CSVPost>();
			//forum_id, post with subposts
			//TreeMultimap<String, ForumThread> finalList = TreeMultimap.create();
			//TreeMap<String, ForumThread> finalList = new TreeMap<String, ForumThread>();
			//ForumThread thread = null;
			String threadID = null;
			String forumID = null;
			String postID = null;
			running=0;
			NavigableSet<ForumThread> forum = null;
			for (Iterator<CSVPost> iterator = csvToBean.iterator(); iterator.hasNext();) {
				
				csvPost = iterator.next();
				forumID = csvPost.getForumPostID();
				
				if((csvPost.getCommentThreadID() == null)&&(csvPost.getPostType().equalsIgnoreCase("CommentThread"))) {
					ForumThread thread = new ForumThread();
					if(checkList(csvPost)) {
		        		csvPost.setStaffCandidate(true);
						if(csvPost.getStaffCandidate())
							thread.setStaffCandidatePresence(true);
		        		sucessCount++;
		        	}
					
					thread.add(csvPost);
					
					/*if(moreLegible && csvPost.isStaff()) {
						thread.incrementStaffPresence();
					}*/
					
					finalList.put(forumID, thread);
					
		        	simpleCount++;
				}
				running++;
	        }
			
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH), UTF8_CHARSET);
			
			csvToBean = new CsvToBeanBuilder<CSVPost>(reader)
                    .withType(CSVPost.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    //.withSkipLines(1)
                    .build();
			
			running=0;
			for (Iterator<CSVPost> iterator = csvToBean.iterator(); iterator.hasNext();) {
				CSVPost currentPost = iterator.next();
				ForumThread thread = null;
				if(((threadID = currentPost.getCommentThreadID()) != null)
						&&!(currentPost.getPostType().equalsIgnoreCase("CommentThread"))
						&&((thread = finalList.get(threadID))!=null)
						) {
					
					/*if(moreLegible) {
						if(currentPost.isStaff()) {
							thread.incrementStaffPresence();
						}
						if(thread.getStaffPresence() > 1) {
							continue;
						}
					}*/
					
					if(checkList(currentPost)) {
						currentPost.setStaffCandidate(true);
						if(currentPost.getStaffCandidate())
							thread.setStaffCandidatePresence(true);
		        		sucessCount++;
		        	}
					thread.add(currentPost);
					finalList.put(threadID, thread);
					
					simpleCount++;
					
				}else if(moreLegible)
				//este if é só quando for o resultado final
					if(((threadID = currentPost.getCommentThreadID()) == null)
							&&((threadID = currentPost.getForumPostID())!=null)
							&&((thread = finalList.get(threadID))!=null)) {
						CSVPost equals = thread.floor(currentPost);
						equals.setCommentThreadID(threadID);
					}
				running++;
	        }


			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			
			
			

			/*while (csvPostIterator.hasNext()) {
				
				csvPost = csvPostIterator.next();
				forumID = csvPost.getForumUID();
				
				if(checkList(csvPost)) {
	        		csvPost.setStaffCandidate(true);
	        		sucessCount++;
	        	}
				
				if((threadID = csvPost.getCommentThreadID()) != null) {
					forum = finalList.get(forumID);
					
					if(!forum.isEmpty()){
						boolean add=false;
						for (Iterator<ForumThread> iterator = forum.iterator(); iterator.hasNext();) {
							ForumThread currentThread = iterator.next();
							postID = "";
							for (Iterator<CSVPost> iterator2 = currentThread.iterator(); iterator2.hasNext();) {
								CSVPost currentComment = iterator2.next();
								if((postID = currentComment.getCommentThreadID()) == null) {
									postID = currentComment.getForumPostID();
								}
								break;
							}
							
							if(postID.equals(threadID)) {
								currentThread.add(csvPost);
								add=true;
								break;
							}
						}
						if(!add) {
							ForumThread thread = new ForumThread();
							thread.add(csvPost);
							finalList.put(forumID, thread);
						}
					}else {
						ForumThread currentPost = new ForumThread();
						currentPost.add(csvPost);
						finalList.put(threadID, currentPost);
					}

					
				}else {
					ForumThread thread = new ForumThread();
					thread.add(csvPost);
					finalList.put(forumID, thread);
				}
				
	        	simpleCount++;
	        	
	        }*/
			
	        System.out.println(simpleCount);
	        System.out.println(sucessCount);
	        
	        Set<String> forumIDs = finalList.keySet();
	        String forumID = null;
	        //ForumThread currentThread = null;
	        //ForumThread comments = null;
			int countPosts = 0;
			int countThreads = 0;
			int countActualThreads = 0;
	        for (Iterator<String> iterator = forumIDs.iterator(); iterator.hasNext();) {
	        	forumID = iterator.next();
	        	countThreads++;
	        	if(countThreads < countThreadsStart) {
	        		continue;
	        	}
	        	if(countThreads > countThreadsEnd) {
	        		break;
	        	}
	        	countActualThreads++;
	        	ForumThread currentThread = finalList.get(forumID);
	        	if(currentThread.isEmpty()) {
	        		continue;
	        	}
	        	TreeSet<CSVPost> thread = currentThread.getThread();
	        	int threadSize = thread.size();
	        	StatefulBeanToCsv<CSVPost> beanToCsv = null;
	        	currentThread.calculeIndicators();
	        	if(currentThread.getCountOpinions()<=0) {
	        		beanToCsv = beanToCsvWithoutOpitions;
	        	}else {
	        		if(threadSize<=1) {
		        		beanToCsv = beanToCsv1;
		        	}else {
		        		/*if(currentThread.getDemandReview()) {
			        		beanToCsv = beanToCsvDemandReview;
			        	}else */
			        	if(threadSize > 1 && threadSize <=5) {
			        		//beanToCsv = beanToCsv2_5;
			        		if(currentThread.getStaffCandidatePresence())
			        			beanToCsv = beanToCsv2_5_staff_candidates;
			        		else 
			        			beanToCsv = beanToCsv2_5;
			        	}else if(threadSize > 5 && threadSize <=10) {
			        		//beanToCsv = beanToCsv6_10;
			        		if(currentThread.getStaffCandidatePresence())
			        			beanToCsv = beanToCsv6_10_staff_candidates;
			        		else 
			        			beanToCsv = beanToCsv6_10;
			        	}else if(threadSize > 10) {
			        		//beanToCsv = beanToCsv11_;
			        		//beanToCsv = beanToCsv11__staff_candidates;
			        		if(currentThread.getStaffCandidatePresence())
			        			beanToCsv = beanToCsv11__staff_candidates;
			        		else 
			        			beanToCsv = beanToCsv11_;
			        	}else {
			        		continue;
			        	}
		        		currentThread.calculeConsensusGeneral();
		        	}
	        		//currentThread.calculeConsensusGeneral();
	        	}
	        	
	        	/*if(moreLegible) {
	        		beanToCsv.write(currentThread.getBlankPost());
	        		beanToCsv.write(currentThread.getTitlePost());
	        		beanToCsv.write(currentThread.getBlankPost());
	        	}*/
	        	
				for (Iterator<CSVPost> iterator2 = thread.iterator(); iterator2.hasNext();) {
					CSVPost currentPost = iterator2.next();
					
					beanToCsv.write(currentPost);
					
					countPosts++;
				}
				
				
				
				if(countThreads % 10 == 0) {
					flushAll();
				}

			}
	        
	        System.out.println(countPosts);
	        System.out.println(countThreads);
	        System.out.println(countActualThreads);

	        writerWithoutOpitions.close();
	    	writerDemandReview.close();
	    	writer1.close();
	    	writer2_5.close();
	    	writer6_10.close();
	    	writer11_.close();
	    	writer2_5_staff_candidates.close();
	    	writer6_10_staff_candidates.close();
	    	writer11__staff_candidates.close();
	    		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		System.out.println((new java.util.Date()).toString());
	}

	private static void flushAll() throws IOException {
        writerWithoutOpitions.flush();
    	writerDemandReview.flush();
    	writer1.flush();
    	writer2_5.flush();
    	writer6_10.flush();
    	writer11_.flush();
    	writer2_5_staff_candidates.flush();
    	writer6_10_staff_candidates.flush();
    	writer11__staff_candidates.flush();
	}

}
