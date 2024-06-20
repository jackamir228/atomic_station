package com.example.atomic_station.deportations.reactor;

import com.example.atomic_station.deportations.security.SecurityDepartment;
import com.example.atomic_station.exceptions.NuclearFuelIsEmptyException;
import com.example.atomic_station.exceptions.ReactorWorkException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class ReactorDepartment {
    private boolean isWork;
    private long energy;
    private int countStarts;
    private SecurityDepartment securityDepartment;

    public ReactorDepartment(SecurityDepartment securityDepartment) {
        this.securityDepartment = securityDepartment;
    }

    public long run() throws ReactorWorkException {
        if (this.isWork) {
            throw new ReactorWorkException("Реактор уже работает\n");
        } else {
            setWork(true);
            countStarts++;
        }
        if (countStarts == 100) {
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException("Кончилось топливо в реакторе\n");
        }
        setEnergy(10_000_000);
        return energy;
    }

    public void stop() throws ReactorWorkException {
        if (!this.isWork) {
            throw new ReactorWorkException("Реактор уже выключен\n");
        } else {
            setWork(false);
            System.out.println("Реактор выключен\n");
        }
    }

}




