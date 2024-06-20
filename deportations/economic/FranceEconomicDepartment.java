package com.example.atomic_station.deportations.economic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Getter
@Setter
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {

    @Value("${france.currency}")
    private String currency;
    @Value("${france.price}")
    private BigDecimal price;
    @Value("${france.name}")
    private String countryName;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal currentPrice = this.price;
        long remainingElectricity = countElectricity;

        while (remainingElectricity > 0) {
            long currentBillion = Math.min(remainingElectricity, 1_000_000_000);
            BigDecimal incomeFromCurrentPartABillion = currentPrice.multiply(BigDecimal.valueOf(currentBillion));
            totalPrice = totalPrice.add(incomeFromCurrentPartABillion);
            remainingElectricity -= currentBillion;
            currentPrice = currentPrice.multiply(BigDecimal.valueOf(0.99));

        }
        return totalPrice;
    }
}
