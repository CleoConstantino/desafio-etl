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

    public static List<BigDecimal> orderArray(List<BigDecimal> numbers, BigDecimal key) {
        int low = 0;
        int high = numbers.size() - 1;
        int mid;
        BigDecimal element;

        while (low <= high) {
            mid = (int) Math.floor((low + high) / 2);
            element = numbers.get(mid);

            if (element.compareTo(key) == -1) {
                low = mid + 1;
            } else if (element.compareTo(key) == 1) {
                high = mid - 1;
            }
        }

        numbers.add(low, key);

        return numbers;
    }

    public List<BigDecimal> transform() {
        List<BigDecimal> numbers = new ArrayList<>();
        NumberList response = null;
        int page = 1;

        do {
            String url = "http://challenge.dienekes.com.br/api/numbers?page=" + page;

            try {
                response = restTemplate.getForObject(
                        url,
                        NumberList.class);

                numbers.addAll(response.getNumbers());
                System.out.println("Página " + page);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao carregar a página " + page);
            }

            page++;
        } while (!response.getNumbers().isEmpty());

        List<BigDecimal> numbersOrdered = new ArrayList<>();
        for (BigDecimal number : numbers) {
            orderArray(numbersOrdered, number);
            System.out.println(Math.floor((numbersOrdered.size() * 100) / numbers.size()) + "%");
        }

        return numbersOrdered;
    }
}
