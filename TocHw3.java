/*
姓名：王煥智
學號：F74006195
說明：本程式用以計算要求條件下的土地交易的平均價格
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class TocHw3 
{
    public static void main(String[] args) throws JSONException, IOException
    {
	int avg_price, count, years; //count is used to count how many data fit the condition
	String str;
	URL url = new URL(args[0]);
	InputStream reader = url.openStream();
	JSONArray add = new JSONArray(new JSONTokener(reader)); //put json data into jsonarray

	Pattern pattern = Pattern.compile(".*"+args[1]+args[2]+".*"); 
	Matcher matcher;

	avg_price = count = 0;
	years = Integer.parseInt(args[3])*100;

	for(int i = 0; i < add.length(); i++)
	{
	    JSONObject obj = add.getJSONObject(i);
	    str = obj.getString("土地區段位置或建物區門牌");
	    matcher = pattern.matcher(str);
	    if(matcher.find() && (obj.getString("鄉鎮市區").equals(args[1])))
		if((obj.getInt("交易年月")) > years) //if the year is older than the input year
		{
		    count++;
		    avg_price += obj.getInt("總價元");
		}
	}

	if(count == 0) //if no data matches
	    System.out.println((int)(avg_price));
	else
	    System.out.println((int)(avg_price/count));

    }
}
