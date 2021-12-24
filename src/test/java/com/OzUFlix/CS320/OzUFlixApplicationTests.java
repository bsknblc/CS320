package com.OzUFlix.CS320;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.OzUFlix.CS320.Model.*;
import com.OzUFlix.CS320.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OzUFlixApplicationTests {

	@Autowired
	AvailableService availableService;

	@Autowired
	DirectorService directorService;

	@Autowired
	TopicService topicService;

	@Autowired
	MovieService movieService;

	@Autowired
	UserService userService;

	@Autowired
	RentService rentService;

	@Autowired
	Return_MovieService return_movieService;

	@Autowired
	PenaltyService penaltyService;

	@Test
	public void testService() {
		Available available = new Available();
		available.setInfo(1);
		Available notAvailable = new Available();
		notAvailable.setInfo(0);

		assertTrue(available.getInfo()==1);
		assertTrue(notAvailable.getInfo()==0);

		Director director = new Director();
		director.setName("Nolan");

		assertTrue(director!=null);
		assertTrue(director.getName()=="Nolan");

		Topic topic = new Topic();
		topic.setName("scific");

		assertTrue(topic!=null);
		assertTrue(topic.getName()=="scific");

		Movie movie = new Movie();
		movie.setName("Inception");
		movie.setTopic(topic);
		movie.setDirector(director);

		assertTrue(movie!=null);
		assertTrue(movie.getName()=="Inception");
		assertTrue(movie.getTopic()==topic);
		assertTrue(movie.getDirector()==director);


		User manager = new User();
		manager.setName("Aylin");
		manager.setPassword("123");
		manager.setUserType(1);

		assertTrue(manager!=null);
		assertTrue(manager.getName()=="Aylin");
		assertTrue(manager.getPassword()=="123");
		assertTrue(manager.getUserType()==1);

		User user = new User();
		user.setName("Başak");
		user.setPassword("123");
		user.setUserType(0);

		assertTrue(user!=null);
		assertTrue(user.getName()=="Başak");
		assertTrue(user.getPassword()=="123");
		assertTrue(user.getUserType()==0);

		Rent rent = new Rent();
		rent.setUser(user);
		rent.setMovie(movie);
		java.util.Date date = new java.util.Date();
		rent.setDate(date); //todo: bunu sql date e çevirmek gerekebilir

		assertTrue(rent!=null);
		assertTrue(rent.getUser()==user);
		assertTrue(rent.getMovie()== movie);
		assertTrue(rent.getDate()== date);

		Return_Movie returnM = new Return_Movie();
		returnM.setRent(rent);
		returnM.setUser(user);
		returnM.setDate(date);

		assertTrue(returnM!=null);
		assertTrue(returnM.getRent()==rent);
		assertTrue(returnM.getUser()==user);
		assertTrue(returnM.getDate()==date);

		Penalty penalty = new Penalty();
		penalty.setRent(rent);
		penalty.setReturn_movie(returnM);

		assertTrue(penalty!=null);
		assertTrue(penalty.getRent()==rent);
		assertTrue(penalty.getReturn_movie()==returnM);

		availableService.save(available);
		availableService.save(notAvailable);
		directorService.save(director);
		topicService.save(topic);
		movieService.save(movie);
		userService.save(manager);
		userService.save(user);
		rentService.save(rent);
		return_movieService.save(returnM);
		penaltyService.save(penalty);

	}

}
