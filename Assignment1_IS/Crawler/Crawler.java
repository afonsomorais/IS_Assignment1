import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


public class Crawler {

    public static void main(String[] args) throws IOException {
        String url = "https://www.rio2016.com/en/medal-count-country";
        //System.out.println("Fetching "+url+"...");

        Document doc = Jsoup.connect(url).get();
        Element tableMedals = doc.select("table").get(2);
        Elements rows = tableMedals.select("tr");
        Elements cenas = tableMedals.select("td");
        //System.out.println(rows.get(3).text());
        for(int i = 1; i < rows.size(); i++){
            /*String [] token = rows.get(i).text().split(" ");
            int position = Integer.parseInt(token[0]);
            String abrv = token[1];
            int nMedals = Integer.parseInt(token[1]);*/
            int nGold;
            int nSilver;
            int nBronze;
            ArrayList <String> winners = new ArrayList<String>();

            System.out.println(cenas.get(1).text());
        }
        //System.out.println("ola: "+ tableMedals.text().toString());
        //System.out.println("Media: "+tableMedals.size());



        /*print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                    src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                    trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }*/

    /*private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    */
    }
}
