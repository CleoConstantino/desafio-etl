package com.desafioetl.desafioetl.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class NumberList {

    private List<BigDecimal> numbers;

}
