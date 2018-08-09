import java.sql.*;

public class DataBaseMssql {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://210.115.230.145:1433";
	private String user = "vUser";
	private String password = "1234";
	private MovieAPI api = new MovieAPI();
	
	public DataBaseMssql() {
		
	}
	
	public Object Main_List(String num) {
		String result = "";
		switch(num) {
		case "0":
			//System.out.println(api.DailyMovie());
			result = api.DailyMovie();
			break;
		case "1":
			result = Box17_sel("Sheet1","상영횟수 desc");
			break;
		case "2":
			result = Box17_sel("Sheet1","매출액 desc");
			break;
		case "3":
			result = Box17_sel("Sheet1","스크린수 desc");
			break;
		case "4":
			result = Box17_sel("역대_박스오피스","순위");
			break;
		case "6":
			result = genre(num);
			break;
		case "7":
			result = genre(num);
			break;
		case "8":
			result = genre(num);
			break;
		case "9":
			result = genre(num);
			break;
		case "10":
			result = genre(num);
			break;
		case "5":
			result = Reviewing();
			break;
		default: ReView(num.split("/")[0],num.split("/")[1]);
		}
		
		return result;
	}
	
	public void ReView(String title,String body) {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			
			Statement stmt = con.createStatement();
			NaverAPI naver = new NaverAPI();
			
			/*String qry ="insert into Review"
					+"values('꾼','개꿀잼인정하는각?어 인정')";
				*/
			String qry = "USE Movie;"+
					"insert into Review " + 
					"values('"+title+"','"+body+"')";
			stmt.executeUpdate(qry);
			
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e) {
			System.out.println("class");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String Reviewing() {
		String value = "";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			
			Statement stmt = con.createStatement();
			NaverAPI naver = new NaverAPI();
			
			String qry = "use Movie; " 
					+"select * from Review ";
					
			ResultSet rs = stmt.executeQuery(qry);
			
			while(rs.next())
			{
				String titleqry = rs.getString("title");
				
				String bodyqry = rs.getString("body");
				value += titleqry+"/"+bodyqry+"/";
				
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e) {
			System.out.println("class");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(value);
		return value;
	}
	
	public String Box17_sel(String tableName,String rank) {
		String value = "";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			
			Statement stmt = con.createStatement();
			NaverAPI naver = new NaverAPI();
			
			String qry = "use Movie; " 
					+"select top 10 * from "
					+ tableName
					+ "$"
					+ " where 순위 is not null"
					+ " order by "
					+ rank;
					
			ResultSet rs = stmt.executeQuery(qry);
			
			while(rs.next())
			{
				String name = rs.getString("영화명")+"/"+rs.getString("개봉일").split("-")[0];
				//System.out.print(name+"\t");
				//System.out.print(gender+"\t");
				value +=name + "- " +naver.naverParser(name)+"- " ;
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e) {
			System.out.println("class");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public String genre(String rank) {
		String value = "";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			
			Statement stmt = con.createStatement();
			NaverAPI naver = new NaverAPI();
			
			String whild = "";
			switch(rank) {
			case "6":
				whild = " 장르 like '%액션%' or 장르 like '%SF%' or 장르 like '%범죄%' or 장르 like '%스릴러%' ";
				break;
			case "7":
				whild = " 장르 like '%멜로%' or 장르 like '%로멘스%' ";
				break;
			case "8":
				whild = " 장르 like '%드라마%' or 장르 like '%사극%' ";
				break;
			case "9":
				whild = " 장르 like '%공포%' or 장르 like '%호러%' ";
				break;
			case "10":
				whild = " 장르 like '%에니%' ";
				break;
			}
			
			String qry = "use Movie; " 
					+"select top 10 * FROM [Movie].[dbo].[장르1$]"
					+ " where "
					+ whild
					+ " order by 순번";
					
			ResultSet rs = stmt.executeQuery(qry);
			
			while(rs.next())
			{
				String name = rs.getString("영화명");
				System.out.println(name);
				//System.out.print(name+"\t");
				//System.out.print(gender+"\t");
				value +=name + "- " +naver.naverParser(name+ "/ ") +"- " ;
				
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e) {
			System.out.println("class");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(value);
		return value;
	}
}
