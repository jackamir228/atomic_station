package com.example.atomic_station.station;

import com.example.atomic_station.deportations.economic.EconomicDepartment;
import com.example.atomic_station.deportations.reactor.ReactorDepartment;
import com.example.atomic_station.deportations.security.SecurityDepartment;
import com.example.atomic_station.exceptions.ReactorWorkException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@ToString
public class NuclearStation {
    @Lazy
    private ReactorDepartment reactorDepartment;
    private long countGeneratedEnergy = 0;
    private int accidentCountAllTime;
    @Lazy
    @Autowired
    private SecurityDepartment securityDepartment;
    private EconomicDepartment economicDepartment;

    public NuclearStation
            (@Lazy ReactorDepartment reactorDepartment,
             EconomicDepartment economicDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.economicDepartment = economicDepartment;
        //  this.securityDepartment = securityDepartment;
    }


    public long startYear() {
        System.out.println("Атомная станция начала работу\n");
        long totalProducedEnergy = 0;
        long emptyDays = 0;
        for (int i = 0; i <= 365; i++) {
            try {
                reactorDepartment.run();
            } catch (ReactorWorkException e) {
                if (i % 100 == 0) {
                    emptyDays++;
                    securityDepartment.addAccident();
                    System.out.println("Внимание! Происходят работы на атомной станции! Электричества нет!\n");
                }
            }
            totalProducedEnergy += reactorDepartment.getEnergy();
        }
        emptyDays = emptyDays * reactorDepartment.getEnergy();
        totalProducedEnergy = totalProducedEnergy - emptyDays;

        securityDepartment.reset();
        System.out.printf("Атомная станция закончила работу. За год Выработано %s киловатт/часов\n",
                totalProducedEnergy);

        System.out.printf("Количество инцидентов за год: %s \nКоличество инцидентов за период: %s\n"
                , this.getAccidentCountAllTime(), securityDepartment.getAccidentCountPeriod());
        System.out.printf("Доход за год составил %d%s\n", totalProducedEnergy, economicDepartment.getCurrency());
        return totalProducedEnergy;
    }

    public long start(int year) {
        System.out.println("Атомная станция начала работу\n");
        long totalProducedEnergy = 0;
        long emptyDays = 0;
        for (int i = 1; i <= year; i++) {
            try {
                reactorDepartment.run();
            } catch (ReactorWorkException e) {
                if (i % 100 == 0) {
                    emptyDays++;
                    System.out.println("Внимание! Происходят работы на атомной станции! Электричества нет!\n");
                }
            }
            totalProducedEnergy += reactorDepartment.getEnergy();
        }
        emptyDays = emptyDays * reactorDepartment.getEnergy();
        totalProducedEnergy = totalProducedEnergy - emptyDays;
        securityDepartment.reset();
        incrementAccident(securityDepartment.reset());
        System.out.printf("Атомная станция закончила работу. За год Выработано %s киловатт/часов\n", totalProducedEnergy);

        System.out.printf("Количество инцидентов за всю работу станции %s \nКоличество инцидентов за все время: %s\n"
                , this.getAccidentCountAllTime(), securityDepartment.getAccidentCountPeriod());
        System.out.printf("Действие происходит в стране: %s\n", economicDepartment.getCountryName());
        return totalProducedEnergy;
    }

    public int incrementAccident(int count) {
        this.accidentCountAllTime += count;
        return accidentCountAllTime;
    }
}

