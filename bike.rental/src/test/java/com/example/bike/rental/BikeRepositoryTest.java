package com.example.bike.rental;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.bike.rental.controller.MainController;
import com.example.bike.rental.domain.Bike;
import com.example.bike.rental.repository.BikeRepository;
import com.example.bike.rental.service.BikeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration 
public class BikeRepositoryTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Mock
	private MainController mainController;
	
	@Autowired
	private BikeService bikeService;
	
	@MockBean
	private BikeRepository bikeRepo;
	
	@Captor
    private ArgumentCaptor<Bike> bikeCaptor;
	
	@Before
	public void setUp() {
		//MockitoAnnotations.initMocks(this);
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}
	
	@Test
	public void getBikesTest() {
		when(bikeRepo.findAll()).thenReturn(Stream.of(new Bike(1995, "PA56986", "Test bike", 3000, true),
				new Bike(1995, "PA56986", "Test bike", 3000, false)).collect(Collectors.toList()));
		assertEquals(2, bikeService.findBikes().size());
	}
	
	@Test
	public void checkRepoSave() {
		Bike testBike = new Bike(1995, "PA56986", "Test bike", 3000, true);
		bikeRepo.save(testBike);
		Mockito.verify(bikeRepo).save(bikeCaptor.capture());
		assertThat(bikeCaptor.getValue().getBikeType().equals("Test bike"));
		assertThat(bikeCaptor.getValue().getFrameNumber().equals("PA56986"));
		assertThat(bikeCaptor.getValue().getDailyPrice() == 3000);
		assertThat(bikeCaptor.getValue().getManufactureDate() == 1995);
	}
	
	@Test
	public void saveBikeTest() {
		Bike bike = new Bike();
		bike.setId(1L);
		bike.setBikeType("Test Bike");
		bike.setDailyPrice(3000);
		bike.setEnabled(true);
		bike.setFrameNumber("ABC1234");
		bike.setManufactureDate(1995);
		bikeRepo.save(bike);
		Mockito.verify(bikeRepo).save(bikeCaptor.capture());
		Mockito.verify(bikeRepo, times(1)).save(bike);
		assertThat(bikeCaptor.getValue().getBikeType().equals(bike.getBikeType()));
		assertThat(bikeCaptor.getValue().getId() == bike.getId());
		assertThat(bikeCaptor.getValue().getDailyPrice() == bike.getDailyPrice());
		assertThat(bikeCaptor.getValue().getFrameNumber().equals(bike.getFrameNumber()));
		assertThat(bikeCaptor.getValue().getManufactureDate() == bike.getManufactureDate());
		assertThat(bikeCaptor.getValue().isEnabled() == bike.isEnabled());
	}
	
	@Test
	public void getBikeByFrameNumberTest() {
		String frameNumber = "PA56986";
		when(bikeRepo.findByFrameNumber(frameNumber)).thenReturn(new Bike(1995, "PA56986", "Test bike", 3000, true));
		assertEquals(frameNumber, bikeService.findBikeByFrameNumber(frameNumber).getFrameNumber());
	}
	
	@Test
	public void getEnabledBikesTest()  {
		when(bikeRepo.findEnabledBikes()).thenReturn(Stream.of(new Bike(1995, "PA56986", "Test bike", 3000, true),
				new Bike(1995, "PA56986", "Test bike", 3000, true)).collect(Collectors.toList()));
		assertEquals(2, bikeService.findEnabledBikes().size());
	}
	
	@Test
	public void getDisabledBikesTest()  {
		when(bikeRepo.findDisabledBikes()).thenReturn(Stream.of(new Bike(1995, "PA56986", "Test bike", 3000, false),
				new Bike(1995, "PA56986", "Test bike", 3000, false)).collect(Collectors.toList()));
		assertEquals(2, bikeService.findDisabledBikes().size());
	}
	
	
}
	
	
	
	
	
