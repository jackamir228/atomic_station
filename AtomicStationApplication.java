package com.example.atomic_station;

import com.example.atomic_station.config.NuclearStationConfig;
import com.example.atomic_station.station.NuclearStation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class AtomicStationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NuclearStationConfig.class);
        Map<String, NuclearStation> stations = context.getBeansOfType(NuclearStation.class);
        System.out.println(stations);
        NuclearStation frenchStation = stations.get("frenchStation");
        NuclearStation moroccoStation = stations.get("moroccoStation");
        moroccoStation.start(4);
        moroccoStation.startYear();



    }

}
