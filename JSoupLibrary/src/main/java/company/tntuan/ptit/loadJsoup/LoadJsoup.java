package company.tntuan.ptit.loadJsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadJsoup {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat formatDate = new SimpleDateFormat("dd-MM-YYYY-HH-mm-ss");
        Connection conn = Jsoup.connect("http://dantri.com.vn");
        BufferedWriter bufw = null;
        try {
            Document doc = conn.get();
            String content = doc.text();
            bufw = new BufferedWriter(new FileWriter(formatDate.format(date)));
            bufw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
