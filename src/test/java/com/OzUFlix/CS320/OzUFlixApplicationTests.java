package com.OzUFlix.CS320;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.OzUFlix.CS320.Model.*;
import com.OzUFlix.CS320.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OzUFlixApplicationTests {

	@Autowired
	AvailableService availableService;

	@Test
	public void testService() {
		Available available = new Available();
		available.setInfo(1);
		Available notAvailable = new Available();
		notAvailable.setInfo(0);

		assertTrue(available.getInfo()==1);
		assertTrue(notAvailable.getInfo()==0);

	}

}