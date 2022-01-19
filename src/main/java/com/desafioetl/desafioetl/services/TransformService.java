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

    public List<BigDecimal> transform() {
        List<BigDecimal> numbers = new ArrayList<>();
        int page = 1;

        do {
            String url = "http://challenge.dienekes.com.br/api/numbers?page=" + page;

            try {
                NumberList response = restTemplate.getForObject(
                        url,
                        NumberList.class);

                numbers.addAll(response.getNumbers());
                System.out.println("Página " + page);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao carregar a página " + page);
            }

            page++;
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

        return numbers;
    }
}
