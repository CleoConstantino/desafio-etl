package com.desafioetl.desafioetl.services;

import com.desafioetl.desafioetl.models.NumberList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransformService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String transform() throws InterruptedException {
        List<BigDecimal> numbers = new ArrayList<>();

        int x = 1;

        do  {
            System.out.println("http://challenge.dienekes.com.br/api/numbers?page=" + x);
                        NumberList response = restTemplate.getForObject(
                    "http://challenge.dienekes.com.br/api/numbers?page=" + x,
                    NumberList.class);


            numbers.addAll(response.getNumbers());
            x++;

            Thread.sleep(1000);

        } while (!numbers.isEmpty());


        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (numbers.get(i).compareTo(numbers.get(j)) < 0) {
                    BigDecimal temp = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, temp);
                }
            }
        }

        System.out.println(numbers);
        return "Olá";

    }
}
