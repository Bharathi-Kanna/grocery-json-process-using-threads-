package activities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.text.html.HTMLDocument.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.format.DateTimeFormatter;  
 

public class Activity {
	
	public static List<groceryInfo> latest_crr(List<groceryInfo> groceryInformation){
		List<groceryInfo> latest = new ArrayList<>();
		latest=groceryInformation;
		
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
//		
//		for(groceryInfo g:latest){
//			System.out.println(g.getCrrawled_at().formatted(dateTimeFormatter));
//		} 
		Collections.sort(latest, new Comparator<groceryInfo>() {  
			DateFormat f = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss");  
			@Override  
			public int compare(groceryInfo o1, groceryInfo o2) {  
			try {  
			  return f.parse(o2.getCrrawled_at()).compareTo(f.parse(o1.getCrrawled_at()));  
			    } catch (ParseException e) {  
			      throw new IllegalArgumentException(e);  
			    }  
			}  
			});
		return latest;
	}
	
	public static void createCSV(List<groceryInfo> groceryInformation) throws FileNotFoundException {
		PrintWriter pw =new PrintWriter(new File("C:\\Users\\bhara\\OneDrive\\Desktop\\z900\\activity.csv"));
		StringBuilder sb = new StringBuilder();
		int temp=0;
		for(groceryInfo g:groceryInformation) {
			if(temp==25) break;
			sb.append(g.getName());
			sb.append(",");
			sb.append(g.getGtin13());
			sb.append(",");
			sb.append(g.getSku());
			sb.append(",");
			sb.append(g.getPrice());
			sb.append(",");
			sb.append(g.getCondition());
			sb.append(",");
			sb.append(g.getAvailabilty());
			sb.append(",");
			sb.append(g.getCurrency());
			sb.append(",");
			sb.append(g.getBrand());
			sb.append(",");
			sb.append(g.getBreadcrumbs());
			sb.append(",");
			sb.append(g.getDescription());
			sb.append(",");
			sb.append(g.getImage());
			sb.append(",");
			sb.append(g.getAvg_rating());
			sb.append(",");
			sb.append(g.getReviews_count());
			sb.append(",");
			sb.append(g.getUrl());
			sb.append(",");
			sb.append(g.getId());
			sb.append(",");
			sb.append(g.getCrrawled_at());
			sb.append(",");
			sb.append(g.getSource());
			sb.append(",");
			sb.append("\r\n");
			temp++;
		}
		pw.write(sb.toString());
		pw.close();
		//System.out.print("completed.....");
	}
	
	public static  List<groceryInfo> top_rated_items(List<groceryInfo> groceryInformation){
		List<groceryInfo> top_rated = new ArrayList<>();
		top_rated=groceryInformation;
		
		Collections.sort(top_rated,new Comparator<groceryInfo>() {
			@Override
			public int compare(groceryInfo o1,groceryInfo o2) {
				
				return  o2.getAvg_rating().compareTo(o1.getAvg_rating());
			}
			
		});
		return top_rated;
	}
	
	public static void printGin(List<groceryInfo> groceryInformation,int n) {
		int t=0;
		for(groceryInfo g:groceryInformation) {
			System.out.println(g.getName());
			System.out.println(g.getGtin13());
			System.out.println(g.getSku());
			System.out.println(g.getPrice());
			System.out.println(g.getCondition());
			System.out.println(g.getAvailabilty());
			System.out.println(g.getCurrency());
			System.out.println(g.getBrand());
			System.out.println(g.getBreadcrumbs());
			System.out.println(g.getDescription());
			System.out.println(g.getImage());
			System.out.println(g.getAvg_rating());
			System.out.println(g.getReviews_count());
			System.out.println(g.getUrl());
			System.out.println(g.getId());
			System.out.println(g.getCrrawled_at());
			System.out.println(g.getSource());
			t++;
			if(n==t) break;
			
		}
	}
	
	 

	public static void main(String[] args) throws IOException,ParseException, org.json.simple.parser.ParseException {
		
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\bhara\\OneDrive\\Desktop\\tescoSample.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
		    stringBuilder.append(line);
		    stringBuilder.append(ls);
		}
		
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();
		
		String content = stringBuilder.toString();
		
		JSONArray json = new JSONArray(content);
		
//		JSONObject firstJsonObject = json.getJSONObject(1);
		
		 List<groceryInfo> groceryInformation = new ArrayList<>();
		
		for (int i = 0; i < json.length(); i++){
			  JSONObject obj = json.getJSONObject(i);
			 // System.out.println(obj.get("avg_rating"));
			  groceryInfo gin = new groceryInfo();
			  gin.setName(obj.getString("name"));
			  gin.setGtin13(obj.getString("gtin13"));
			  gin.setSku(obj.getString("sku"));
			  gin.setPrice(obj.getFloat("price"));
			  gin.setCondition(obj.getString("condition"));
			  gin.setAvailabilty(obj.getString("availability"));
			  gin.setCurrency(obj.getString("currency"));
			  gin.setBrand(obj.getString("brand"));
			  gin.setBreadcrumbs(obj.getString("breadcrumbs"));
			  gin.setDescription(obj.getString("description"));
			  gin.setImage(obj.getString("images"));
			  try {
				  gin.setAvg_rating(obj.getFloat("avg_rating"));
			  }
			  catch(Exception e){
				  gin.setAvg_rating(0.0f);
			  }
			  try {
				  gin.setReviews_count(obj.getInt("reviews_count"));
			  }
			  catch(Exception e) {
				  gin.setReviews_count(0);
			  }
			  gin.setUrl(obj.getString("url"));
			  gin.setId(obj.getString("_id"));
			  gin.setCrrawled_at(obj.getString("crawled_at"));
			  gin.setSource(obj.getString("source"));
			  
			  
			 groceryInformation.add(gin);
			}
		
		
		//printGin(groceryInformation);
		
		
		//Q3
		List<groceryInfo> top_rated = top_rated_items(groceryInformation);
	//	printGin(top_rated,10);
		
		//q5;
		List<groceryInfo> latests=latest_crr(groceryInformation);
	  
		//printGin(latests,25);
	//	createCSV(latests);
		
		createDirectory(groceryInformation);
		

	
	}
	
	public static void createDirectory(List<groceryInfo> groceryInformation) throws IOException {
		int temp = 0;
		for(groceryInfo g:groceryInformation) {
			String str1 = g.getBreadcrumbs();
			String str2 = str1.replace("~","\\");
			str2.trim();
			String str3 = g.getGtin13();
//			System.out.println(s2);
			
			File theDir = new File("D:\\activity\\"+str2+" \\ str3 \\");
			//Files.createDirectories(Paths.get("D:\\activity\\"+str2));
		//	System.out.println("D:\\activity\\"+str2+"\\"+str3+".html");
			if (!theDir.mkdir()){
			    System.out.println(theDir.mkdir());
			    theDir.mkdirs();
			}
		
			
			System.out.println("D:\\activity\\"+str2+" \\ str3 \\");
			
			//if(temp==10) break;
			temp++;
			
//			try {
//			     theDir.createNewFile();
//			} catch (IOException ioe) {
//			     System.out.println("Error while Creating File" + ioe);
//			}	
			
			
		}
	//	System.out.println(temp);
		
	
	}
	
	
}
