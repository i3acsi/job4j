import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;

public class ParseProf {
    public static void main(String[] args) {
        String url = "http://oot-oos/";
        Document doc = ParseProf.getDoc(url);
        System.out.println(doc.body().text());
    }

    private static Document getDoc(String url) {
        Document result = null;
        try {
            result = Jsoup.connect(url)
                    .get();
            System.out.println(String.format("Connected to %s on Date %s", url, LocalDate.now().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
