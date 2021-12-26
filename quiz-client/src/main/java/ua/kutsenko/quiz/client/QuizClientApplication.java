package ua.kutsenko.quiz.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizClientApplication {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String setActiveUserStatusUrl = "http://localhost:8081/api/";

    public static void main(String[] args) {

        QuizClientApplication clientApplication = new QuizClientApplication();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
                try {
                    try {
                        for (int i = 1001; i <= 1100; i++) {
                            clientApplication.setActiveUserStatus(i);
                        }
                    } finally {
                        clientApplication.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        };

        executor.execute(runnableTask);
        executor.shutdown();
    }

    private void close() throws Exception {
        httpClient.close();
    }


    private void setActiveUserStatus(int userId) throws Exception {
            HttpPut request = new HttpPut(setActiveUserStatusUrl + userId);
            executeRequest(httpClient.execute(request));
    }

    private void executeRequest(CloseableHttpResponse execute) throws IOException {
        try (CloseableHttpResponse response = execute) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        }
    }
}
