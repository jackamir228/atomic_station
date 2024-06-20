package com.example.atomic_station.deportations.security;

import com.example.atomic_station.station.NuclearStation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(exclude = "nuclearStation")
public class SecurityDepartment {
    //fields
    private int accidentCountPeriod;
    @Lazy
    @Autowired
    private @Qualifier("moroccoStation") NuclearStation nuclearStation;

    //method1
    public void addAccident() {
        accidentCountPeriod++;
    }

    //method2
    public int reset() {
        nuclearStation.setAccidentCountAllTime(accidentCountPeriod);
        setAccidentCountPeriod(0);
        return accidentCountPeriod;
    }
}
