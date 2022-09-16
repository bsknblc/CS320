package com.OzUFlix.CS320.ServiceTest;

import com.OzUFlix.CS320.Model.*;
import com.OzUFlix.CS320.Service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ServiceTest {


    @Autowired
    AvailableService availableService;


    @Test
    public void serviceTest() {

        if(availableService.findAll().size()==0){
            Available available = new Available(1);
            Available notAvailable = new Available(0);

            availableService.save(available);
            availableService.save(notAvailable);
            assertNotNull(availableService.findById(available.getId()));
            assertNotNull(availableService.findById(notAvailable.getId()));
        }


    }
}
