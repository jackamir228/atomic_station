package com.example.atomic_station.deportations.economic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;


@Getter
@Setter
@Profile("morocco")

public class MoroccoEconomicDepartment extends EconomicDepartment {
    @Value("${morocco.currency}")
    private String currency;
    @Value("${morocco.price}")
    private BigDecimal price;
    @Value("${morocco.name}")
    private String countryName;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal currentlyPrice = this.price;
        long remainingElectricity = countElectricity;
        int counterElectricity = 0;

        while (remainingElectricity > 0) {
            long currentBillion = Math.min(remainingElectricity, 1_000_000_000L);
            BigDecimal incomeFromPartElectricity = currentlyPrice.multiply(BigDecimal.valueOf(currentBillion));
            totalIncome = totalIncome.add(incomeFromPartElectricity);
            remainingElectricity -= currentBillion;
            counterElectricity++;
            if (counterElectricity >= 5) {
                currentlyPrice = currentlyPrice.add(BigDecimal.ONE);
            }
        }
        return totalIncome;
    }
}
