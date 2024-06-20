package com.example.atomic_station.deportations.economic;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public abstract class EconomicDepartment {

    private String currency;
    private String countryName;
    private BigDecimal price;

    public BigDecimal computeYearIncomes(long countElectricity) {
        return null;
    }
}
