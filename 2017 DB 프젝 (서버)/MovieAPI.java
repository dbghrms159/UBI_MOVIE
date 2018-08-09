import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.event.TreeWillExpandListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MovieAPI {

	public String DailyMovie() {
		Calendar yester = new GregorianCalendar();
		yester.add(Calendar.DATE, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String day = format.format(yester.getTime());
		String key = "7ae5f84ced62cf4e544f4df63e728f7a" ;
        
        try {
            
            String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="+key+"&targetDt="+day ;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            
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
            //System.out.println( parser(response.toString()));
            return parser(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
	}
	
	public String parser(String son) {
		String result = "";
		NaverAPI api = new NaverAPI();
		JSONParser pars = new JSONParser();
		String name = "", day = "";
		try {
			JSONObject obj = (JSONObject)pars.parse(son);
			
			JSONObject objs = (JSONObject)obj.get("boxOfficeResult");
			JSONArray item = (JSONArray)objs.get("dailyBoxOfficeList");
			
			JSONObject tmp;
			//System.out.println(item.size());
			for(int i = 0; i < item.size(); i++) {
				tmp = (JSONObject)item.get(i);
				name = (String)tmp.get("movieNm");
				day = (String)tmp.get("openDt");
				day = name+"/"+day;
				//day = day.split("-")[0];
				
				result += name +"-"+api.naverParser2(day)+"-";
			}
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
