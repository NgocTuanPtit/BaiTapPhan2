import spark.Service;

import static spark.Service.ignite;

public class RestfullWebService {
    public void GetMethod(){
        Service port = ignite().port(8080);
        port.get("/prime", (request, response) -> {
            return Integer.parseInt(request.queryParams("n"));
        });
    }
}
