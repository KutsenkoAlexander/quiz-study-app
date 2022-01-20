package ua.kutsenko.quiz.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuizClientApplication {

    public static void main(String[] args) {

        QuizClientApplication clientApplication = new QuizClientApplication();
        ExecutorService executor = Executors.newFixedThreadPool(20);

        int students = 100;
        clientApplication.initTable(students);

        List<Runnable> runnable = IntStream.range(1, students)
                .mapToObj(userId -> ((Runnable) () -> {
                    try (CloseableHttpClient client = HttpClients.createDefault()) {
                        System.out.println(">>> Sending request for userId: " + userId);
                        HttpPut request = new HttpPut("http://localhost:8081/api/" + userId);
                        client.execute(request);
                        System.out.println("    Request is send for userId: " + userId);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }))
                .collect(Collectors.toList());

        runnable.forEach(executor::submit);
        executor.shutdown();
    }

    private void initTable(int students) {
        HttpGet request = new HttpGet("http://localhost:8081/api/init/" + students);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            client.execute(request);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
