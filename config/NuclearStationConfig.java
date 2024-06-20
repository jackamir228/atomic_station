package com.example.atomic_station.config;

import com.example.atomic_station.deportations.economic.EconomicDepartment;
import com.example.atomic_station.deportations.economic.FranceEconomicDepartment;
import com.example.atomic_station.deportations.economic.MoroccoEconomicDepartment;
import com.example.atomic_station.deportations.reactor.ReactorDepartment;
import com.example.atomic_station.deportations.security.SecurityDepartment;
import com.example.atomic_station.station.NuclearStation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {
        "com.example.atomic_station.deportations.reactor",
        "com.example.atomic_station.deportations.security"
})
public class NuclearStationConfig {

    @Bean
    @Qualifier("france")
    public EconomicDepartment franceEconomicDepartment() {
        return new FranceEconomicDepartment();
    }

    @Bean
    @Qualifier("morocco")
    public EconomicDepartment moroccoEconomicDepartment() {
        return new MoroccoEconomicDepartment();
    }

    @Bean
    @Qualifier("frenchStation")
    public NuclearStation frenchStation(
            ReactorDepartment reactorDepartment,
            @Qualifier("france") EconomicDepartment economicDepartment) {
        NuclearStation station = new NuclearStation(reactorDepartment, economicDepartment);
        return station;
    }

    @Bean
    @Qualifier("moroccoStation")
    public NuclearStation moroccoStation(
            ReactorDepartment reactorDepartment,
            @Qualifier("morocco") EconomicDepartment economicDepartment) {
        NuclearStation station = new NuclearStation(reactorDepartment, economicDepartment);
        return station;
    }
}
