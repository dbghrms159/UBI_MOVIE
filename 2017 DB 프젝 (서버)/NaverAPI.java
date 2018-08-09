import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NaverAPI {
	private StringBuilder sb;
	private String movies, day;
	public String naverParser(String movie) {
		String clientId = "wleG4lkpLc0m3T_gYRw6";
        String clientSecret = "gxhV2rXqtU";
        movies = movie.split("/")[0];
        day = movie.split("/")[1];
        try {
                String text = URLEncoder.encode(movies, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+text ;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            return parser(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "";
	}
	
	public String parser(String son) {
		String result = "";
		String day = "";
		
		JSONParser pars = new JSONParser();
		try {
			JSONObject obj = (JSONObject)pars.parse(son);
			
			JSONArray item = (JSONArray)obj.get("items");
			JSONObject tmp;
			for(int i = 0; i < item.size(); i++) {
				tmp = (JSONObject)item.get(i);
				//System.out.println((String)tmp.get("image"));
				result = (String)tmp.get("title");
				day = (String)tmp.get("pubDate");
				result = result.replace("<b>", "");
				result = result.replace("</b>", "");
				
				if(result.equals(movies)) {
					return (String)tmp.get("image");
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "x";
	}
	
	
	public String naverParser2(String movie) {
		String clientId = "wleG4lkpLc0m3T_gYRw6";
        String clientSecret = "gxhV2rXqtU";
        movies = movie.split("/")[0];
        
        day =  movie.split("/")[1].split("-")[0];
        try {
                String text = URLEncoder.encode(movies, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+text ;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            return parser2(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "";
	}
	
	public String parser2(String son) {
		String result = "";
		String day;
		
		JSONParser pars = new JSONParser();
		try {
			JSONObject obj = (JSONObject)pars.parse(son);
			
			JSONArray item = (JSONArray)obj.get("items");
			JSONObject tmp;
			for(int i = 0; i < item.size(); i++) {
				tmp = (JSONObject)item.get(i);
				//System.out.println((String)tmp.get("image"));
				result = (String)tmp.get("title");
				day = (String)tmp.get("pubDate");
				result = result.replace("<b>", "");
				result = result.replace("</b>", "");
				if(result.equals(movies)&&this.day.equals(day)) {
					//System.out.println((String)tmp.get("image"));
					return (String)tmp.get("image")+"-"+(String)tmp.get("link");
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "x";
	}
}
