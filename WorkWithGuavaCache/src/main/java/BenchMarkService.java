
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;
import static spark.Spark.get;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 8)

public class BenchMarkService {

    @Benchmark
    public void BenchMark() {
        ExecutorService executorService = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    get("/prime",(request, response)->{
                        int result = Integer.parseInt(request.queryParams("n"));
                        return result*result;
                    });
                }
            });
        }
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder().include(BenchMarkService.class.getSimpleName()).forks(1).build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
