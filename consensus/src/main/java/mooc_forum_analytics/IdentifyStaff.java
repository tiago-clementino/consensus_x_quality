package mooc_forum_analytics;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class IdentifyStaff {

	private static boolean checkList(String start) {
		if(start == null) {
			return false;
		}
		return (start.toLowerCase().contains("professor"))||
				(start.toLowerCase().contains("prof "))||
				(start.toLowerCase().contains("staff"))||
				(start.toLowerCase().contains("instructor"));
	}

	private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/stanfordMOOCForumPostsSet.txt";
	
	private static final String CSV_START_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/start.csv";
	
	private static final String CSV_END_FILE_PATH = "C:/Users/tclem/eclipse-2018-12-workspace/mooc_forum_analytics/data/end.csv";
	
	private static Writer writerStart = null;
	
	private static Writer writerEnd = null;
	
	private static CSVWriter csvWriterStart = null;
	
	private static CSVWriter csvWriterEnd = null;
	
	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	public static void main(String[] args) {
		
		
		//Writer writerStart;
		//Writer writerEnd;
		//CSVWriter csvWriterStart;
		//CSVWriter csvWriterEnd;
		try {
			writerStart = Files.newBufferedWriter(Paths.get(CSV_START_FILE_PATH));
			writerEnd = Files.newBufferedWriter(Paths.get(CSV_END_FILE_PATH));
			csvWriterStart = new CSVWriter(writerStart,
	                CSVWriter.DEFAULT_SEPARATOR,
	                CSVWriter.NO_QUOTE_CHARACTER,
	                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                CSVWriter.DEFAULT_LINE_END);
			csvWriterEnd = new CSVWriter(writerEnd,
	                CSVWriter.DEFAULT_SEPARATOR,
	                CSVWriter.NO_QUOTE_CHARACTER,
	                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                CSVWriter.DEFAULT_LINE_END);
			
			String[] headerRecord = {"Text", "Opinion(1/0)", "Question(1/0)", "Answer(1/0)", 
					"Sentiment(1-7)", "Confusion(1-7)", "Urgency(1-7)", "CourseType", "forum_post_id", 
					"course_display_name", "forum_uid", "created_at", "post_type", "anonymous", "anonymous_to_peers", 
					"up_count", "comment_thread_id", "reads"};
			csvWriterStart.writeNext(headerRecord);
			csvWriterEnd.writeNext(headerRecord);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Reader reader;
		CSVReader csvReader;
		String[] nextRecord;
		String start = "";
		String end = "";
		int simpleCount = 0;
		int sucessCount = 0;
		CSVParser parser =
				new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true)
				.build();
		try {
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH), UTF8_CHARSET);
			
			
			
			
			csvReader =
					new CSVReaderBuilder(
							reader)
					.withSkipLines(1)
					.withCSVParser(parser)
					.build();
			
			
			//csvReader = new CSVReader(reader);
						
			int startEnd = 40;
			int endStart = 60;
	        while ((nextRecord = csvReader.readNext()) != null) {

	        	if(nextRecord[0].length() < startEnd) {
	        		startEnd = nextRecord[0].length();
	        	}
	        	if(nextRecord[0].length() < endStart) {
	        		endStart = nextRecord[0].length();
	        	}
	        	start = nextRecord[0].substring(0, startEnd);
	        	end = nextRecord[0].substring(nextRecord[0].length()-endStart, nextRecord[0].length());
	        	
	        	if(checkList(start)) {
	        		addInStartFile(nextRecord);
	        		sucessCount++;
	        	}else if(checkList(end)) {
	        		addInEndFile(nextRecord);
	        		sucessCount++;
	        	}
	        	simpleCount++;
	        	startEnd = 40;
				endStart = 60;
	        	
	        	
	            //System.out.println("Name : " + nextRecord[0]);
	            //System.out.println("Email : " + nextRecord[1]);
	            //System.out.println("Phone : " + nextRecord[2]);
	            //System.out.println("Country : " + nextRecord[3]);
	            //System.out.println("==========================");
	        }
	        System.out.println(simpleCount);
	        System.out.println(sucessCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
        
		
		

        
        
        
        
        
	}

	private static void addInEndFile(String[] nextRecord) {
		if(nextRecord == null) {
			return;
		}
		csvWriterEnd.writeNext(nextRecord);
	}

	private static void addInStartFile(String[] nextRecord) {
		if(nextRecord == null) {
			return;
		}
		csvWriterStart.writeNext(nextRecord);
	}

}
