package pl.sda.intermediate16;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONExample {
    @Test
    void serializedToJSON() {
        SomeObject someObject = new SomeObject();
        someObject.setAge(22);
        someObject.setName("Adam");
        someObject.setSalary(BigDecimal.valueOf(20));
        OtherObject otherObject = new OtherObject();
        otherObject.setId(3);
        otherObject.setText("o");
        OtherObject otherObject1 = new OtherObject();
        otherObject1.setId(0);
        otherObject1.setText("k");
        List<OtherObject> objects = new ArrayList<>();
        objects.add(otherObject);
        objects.add(otherObject1);
        someObject.setObjectList(objects);
        String s = new Gson().toJson(someObject);
        System.out.println(s);

        SomeObject someObject1 = new Gson().fromJson(s, SomeObject.class);
    }

    @Data
    private class SomeObject {
        private String name;
        private Integer age;
        private BigDecimal salary;
        private List<OtherObject> objectList;
    }

    @Data
    private class OtherObject {
        private Integer id;
        private String text;
    }


    @Test
    void nbpAPI() {
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result = "";
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                result = result + inputLine;
            }
            bufferedReader.close();

            RatesWrapper[] ratesWrapper = new Gson().fromJson(result, RatesWrapper[].class);
            System.out.println(Arrays.toString(ratesWrapper));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Data
    private class RatesWrapper {
        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;
    }

    @Data
    private class Rate {
        private String currency;
        private String code;
        private String mid;
    }

}
