 package activities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.text.html.HTMLDocument.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
 

public class Activity {
	
	public static void createHtml(ArrayList<groceryInfo> groceryInformation) throws IOException {
		
		String header = new String();
		
		for(groceryInfo g :groceryInformation) {
			String fileName=new String(g.getGtin13());
			String desc = new String(g.getDescription());
			String str1 = g.getBreadcrumbs();
			String str2 = str1.replace("~",File.separator);
			File f = new File("D:\\activity\\"+str2+fileName+".html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(desc);
			bw.close();
		}
	}
	
	public static List<groceryInfo> latest_crr(List<groceryInfo> groceryInformation){
		List<groceryInfo> latest = new ArrayList<>();
		latest=groceryInformation;
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
	
	 

	public static void main(String[] args) throws IOException,ParseException, org.json.simple.parser.ParseException, InterruptedException {
		
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
		
		 List<groceryInfo> groceryInformation = new ArrayList<>();
		
		for (int i = 0; i < json.length(); i++){
			  JSONObject obj = json.getJSONObject(i);
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
		
		List<groceryInfo> top_rated = top_rated_items(groceryInformation);

		List<groceryInfo> latests=latest_crr(groceryInformation);
	 
		Read1 read1 = new Read1();
		Read2 read2 = new Read2();
		Read3 read3 = new Read3();
		read1.start();
		read2.start();
		read3.start();
		System.out.println("check..");
	
	}
	
	public static void createDirectory(List<groceryInfo> groceryInformation) throws IOException {
	int temp = 1;
	int count = 1;
		for(groceryInfo g:groceryInformation) {
			String str1 = g.getBreadcrumbs();
			String html = g.getDescription();
			
		
			if((count/temp)>=10) { 
				temp++;
				System.out.println(temp);
			}
			
			File theDir =new File ("D:\\activity\\"+temp+".html");
			theDir.createNewFile();
			
			
			
			
			
			if (!theDir.exists()){
			   theDir.mkdirs();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(theDir));
			
			bw.write(html);
			bw.close();
			
			count++;
			
		
			if(temp==27) break;
			temp++;	
		}
		
	}
	

}
class Read1 extends Thread {
	public void run() {
		  StringBuilder des = new StringBuilder();
		  	for(int i=1;i<=9;i++) {
		  		
		  		StringBuilder html = new StringBuilder();
		  		
		        try {
		        	  FileReader fr = new FileReader("D:\\activity\\"+i+".html");
		            
		            BufferedReader br = new BufferedReader(fr);
		 
		            String val;
		 
		        
		            while ((val = br.readLine()) != null) {
		                html.append(val);
		            }
		 
		          
		            String result = html.toString();
		            
		            try {
						BufferedWriter fWriter= new BufferedWriter(new FileWriter("D:\\activity\\final.html.txt", true));
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
						LocalDateTime now = LocalDateTime.now();  
						String str = "file NO. "+i;
						fWriter.write(dtf.format(now));
						fWriter.write(" -> Thread 1 -> ");
						fWriter.write(str);
						fWriter.newLine();
						fWriter.close();
						
					} catch (IOException e) {
				
						e.printStackTrace();
					}
		 
		            
		            br.close();
		        }    
		        catch (Exception ex) {
		 
		            
		            System.out.println(ex.getMessage());
		        }
		        des.append(html);
		        
		  		
		  	}
		  
		   String s=des.toString();		   
	}
}
class Read2 extends Thread {
	public void run() {
		
		  StringBuilder des = new StringBuilder();
		  	for(int i=10;i<=18;i++) {
 		StringBuilder html = new StringBuilder();
		  		
		        try {
		        	  FileReader fr = new FileReader("D:\\activity\\"+i+".html");
		            
		            BufferedReader br = new BufferedReader(fr);
		 
		            String val;
		 
		        
		            while ((val = br.readLine()) != null) {
		                html.append(val);
		            }
		 
		          
		            String result = html.toString();
		            try {
						BufferedWriter fWriter= new BufferedWriter(new FileWriter("D:\\activity\\final.html.txt", true));
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
						LocalDateTime now = LocalDateTime.now();  
						String str = "file NO. "+i;
						fWriter.write(dtf.format(now));
						fWriter.write(" -> Thread 2 -> ");
						fWriter.write(str);
						fWriter.newLine();
						fWriter.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
		            br.close();
		        }
		 
		        
		        catch (Exception ex) {
		 
		            
		            System.out.println(ex.getMessage());
		        }
		        des.append(html);
		  	}
		  
		   String s=des.toString();
		   
	}
}
class Read3 extends Thread {
	public void run() {
		
		  StringBuilder des = new StringBuilder();
		 
		  	for(int i=19;i<=27;i++) {
 		StringBuilder html = new StringBuilder();
		  		
		        try {
		        	  FileReader fr = new FileReader("D:\\activity\\"+i+".html");
		            
		            BufferedReader br = new BufferedReader(fr);
		 
		            String val;
		 
		        
		            while ((val = br.readLine()) != null) {
		                html.append(val);
		            }
		 
		          
		            String result = html.toString();
		      
		            try {
						BufferedWriter fWriter= new BufferedWriter(new FileWriter("D:\\activity\\final.html.txt", true));
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
						LocalDateTime now = LocalDateTime.now();  
						String str = "file NO. "+i;
						fWriter.write(dtf.format(now));
						fWriter.write(" -> Thread 3 -> ");
						fWriter.write(str);
						fWriter.newLine();
						fWriter.close();
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
		 
		            
		            br.close();
		        }
		        catch (Exception ex) {
		 
		            
		            System.out.println(ex.getMessage());
		        }
		        des.append(html);
		  	
		  	}
		  
		  	String s=des.toString();
				   
	}
}

