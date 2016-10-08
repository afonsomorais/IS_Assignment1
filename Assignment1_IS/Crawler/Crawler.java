import org.jcp.xml.dsig.internal.SignerOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


public class Crawler {

    static ArrayList<Integer> medalhas_paises = new ArrayList<Integer>();


    public Crawler() throws IOException {
    }

    public static int get_id_byname(String name) throws IOException {

        int index = 0;

        String url = "https://www.rio2016.com/en/medal-count-country";
        //System.out.println("Fetching "+url+"...");

        Document doc = Jsoup.connect(url).get();

        int contador = 86;

        for(int i=0;i<contador;i++){
            Element tableMedals = doc.select("tr.table-medal-countries__link-table").get(i);
            Element tableMedals2 = tableMedals.child(1);
            //System.out.println( i + " = " + tableMedals2.text().toString());
            if(tableMedals2.text().toString().equals(name)){
                return i;
            }
        }

        return -1;
    }

    public static void find_country_medals(String name) throws IOException {

        String url = "https://www.rio2016.com/en/medal-count-country";
        //System.out.println("Fetching "+url+"...");

        Document doc = Jsoup.connect(url).get();

        int index = get_id_byname(name);

        Element tableMedals = doc.select("tr.table-medal-countries__link-table").get(index);
        Element tableMedals2 = tableMedals.child(1);
        Elements medals = tableMedals2.siblingElements();

        //System.out.println("MEDALHAS: " + medals.text());

        int goldMedals = Integer.parseInt(medals.get(2).text());
        int silverMedals = Integer.parseInt(medals.get(3).text());
        int bronzeMedals = Integer.parseInt(medals.get(4).text());
        int total_medals = goldMedals + silverMedals + bronzeMedals;

        System.out.println("O país " + tableMedals2.text() + " : " + goldMedals + " : " + silverMedals + " : " + bronzeMedals );
    }

    public static void make_arraylist() throws IOException {

        int contador = 86;

        String url = "https://www.rio2016.com/en/medal-count-country";
        //System.out.println("Fetching "+url+"...");

        Document doc = Jsoup.connect(url).get();


        for(int i=0;i<contador;i++){
            Element tableMedals = doc.select("tr.table-medal-countries__link-table").get(i);
            Element tableMedals2 = tableMedals.child(1);
            Elements medals = tableMedals2.siblingElements();

            //System.out.println("MEDALHAS: " + medals.text());

            int goldMedals = Integer.parseInt(medals.get(2).text());
            int silverMedals = Integer.parseInt(medals.get(3).text());
            int bronzeMedals = Integer.parseInt(medals.get(4).text());
            int total_medals = goldMedals + silverMedals + bronzeMedals;

            medalhas_paises.add(i,total_medals);

        }

      /*  for(int a=0;a<medalhas_paises.size();a++){
            System.out.println(medalhas_paises.get(a));
        }*/

    }



    public static void find_medals_athlete(String name) throws IOException {

        int contador = 86;

        String url = "https://www.rio2016.com/en/medal-count-country";
        //System.out.println("Fetching "+url+"...");

        Document doc = Jsoup.connect(url).timeout(100000000).get();

        Elements tableMedals = doc.select("tr.table-expand");


        for(int i=0;i<contador;i++){
            for(int j=0;j<medalhas_paises.get(i);j++){
                Elements athlete_medals = tableMedals.get(i).child(0).child(0).child(0).child(j).child(0).siblingElements();
                if(name.equals(athlete_medals.get(2).text())){
                    String medal = tableMedals.get(i).child(0).child(0).child(0).child(j).child(0).text();
                    System.out.println(medal + " " + athlete_medals.get(1).text() + " " + athlete_medals.get(0).text());
                }
            }
        }

    }




    public static void main(String[] args) throws IOException {

        make_arraylist();
        find_medals_athlete("MONTEIRO Telma");

        //make_arraylist();

       // make_arraylist();

        //System.out.println(rows.get(3).text());

        //System.out.println("FUNCAO: " + get_id_byname("POR",86));
        //System.out.println("Media: "+tableMedals.size());
      /*  int index = get_id_byname("POR");
        find_country_medals(index);*/



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
