import spark.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static spark.Service.ignite;
import static spark.Spark.get;


public class HttpAutoRequest {

    private static GuavaCache guava = new GuavaCache();

    public HttpAutoRequest() {
        RestfullWebService webService = new RestfullWebService();
        webService.GetMethod();
    }

    // Khoi tao url voi param ngau nhien.
    public static String SinhParam() {
        Random random = new Random();
        String link = "http://localhost:8080/prime?n=" + random.nextInt(100);
        return link;
    }

    //Khoi tao 4 luong gui request auto
    public static void AutoSendRequest() {
        ExecutorService executorService = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        BufferedReader bufw = null;
                        try {
                            URL url = new URL(SinhParam());
                            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
                            bufw = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
                            StringBuilder stringBuilder = new StringBuilder();
                            int c;
                            while ((c = bufw.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            System.out.println(guava.getParam(Integer.parseInt(stringBuilder.toString())));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                bufw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) {

        HttpAutoRequest autoRequest = new HttpAutoRequest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        autoRequest.AutoSendRequest();
    }
}
