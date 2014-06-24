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
	int avg_price, count, years;
	String str;
	URL url = new URL(args[0]);
	InputStream reader = url.openStream();
	JSONArray add = new JSONArray(new JSONTokener(reader));

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
		if((obj.getInt("交易年月")) > years)
		{
		    count++;
		    avg_price += obj.getInt("總價元");
		}
	}

	if(count == 0)
	    System.out.println((int)(avg_price));
	else
	    System.out.println((int)(avg_price/count));

    }
}
