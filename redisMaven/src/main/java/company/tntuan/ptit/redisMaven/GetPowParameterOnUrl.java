package company.tntuan.ptit.redisMaven;


import spark.Service;
import spark.route.HttpMethod;


import static spark.Service.ignite;
import static spark.route.HttpMethod.get;

public class GetPowParameterOnUrl {

    public static void main(String[] args) {
        SparkỊnJava();
    }


    static void SparkỊnJava() {
        Service http = ignite().port(8080);
        http.get("/prime", (request, response) -> {
            int result = (int) Math.pow(Integer.parseInt( request.queryParams("n")),2);
            return result;
        });
    }
}
