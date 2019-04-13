package pl.sda.intermediate16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Function;

public class CompletableFutureExample {

    private Function<Long, String> addInfoToString = c -> c + ".";
    private Function<BigDecimal, String> priceToString = c -> c.toPlainString();
    private Function<String, String> photosToString = c -> c + "!";
    private Function<String, String> descriptionToString = c -> c + ".";

    @Test
    void oneByOne() {

        String additionalInfoString = transform(downloadAdditionalInfo(), addInfoToString);
        String photosString = transform(downloadPhotos(), photosToString);
        String priceString = transform(downloadPrice(), priceToString);
        String descriptionString = transform(downloadDescription(), descriptionToString);
        ProductForTest productForTest = new ProductForTest(descriptionString, priceString, photosString, additionalInfoString);
        System.out.println(productForTest);


    }

    @Test
    void threads() {
        Thread thread1 = new Thread(() -> transform(downloadAdditionalInfo(), addInfoToString));
        Thread thread2 = new Thread(() -> transform(downloadPhotos(), photosToString));
        Thread thread3 = new Thread(() -> transform(downloadPrice(), priceToString));
        Thread thread4 = new Thread(() -> transform(downloadDescription(), descriptionToString));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void futures() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Long> submit2 = executorService.submit(() -> downloadAdditionalInfo());
        Future<String> submit1 = executorService.submit(() -> downloadPhotos());
        Future<String> submit = executorService.submit(() -> downloadDescription());
        Future<BigDecimal> submit3 = executorService.submit(() -> downloadPrice());
        executorService.submit(() -> transform(submit.get(), descriptionToString));
        executorService.submit(() -> transform(submit1.get(), photosToString));
        executorService.submit(() -> transform(submit2.get(), addInfoToString));
        executorService.submit(() -> transform(submit3.get(), priceToString));
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
    }

    @Test
    void completableFutures() {

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> downloadAdditionalInfo()).thenApplyAsync(e -> transform(e, addInfoToString));
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> downloadDescription()).thenApplyAsync(e -> transform(e, descriptionToString));
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> downloadPhotos()).thenApplyAsync(e -> transform(e, photosToString));
        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(() -> downloadPrice()).thenApplyAsync(e -> transform(e, priceToString));

        Arrays.asList(cf1, cf2, cf3, cf4).forEach(CompletableFuture::join);
    }


    private <T> String transform(T value, Function<T, String> function) {
        simulateDelay(2000);
        return function.apply(value);
    }


    private String downloadDescription() {
        simulateDelay(3000);
        return "opis";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        return BigDecimal.valueOf(1000);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        return "zdjecia";
    }

    private Long downloadAdditionalInfo() {
        simulateDelay(3300);
        return 30L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalInfo;
    }
}
