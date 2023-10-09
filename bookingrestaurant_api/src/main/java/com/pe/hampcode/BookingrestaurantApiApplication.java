package com.pe.hampcode;

import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.repository.CustomerRepository;
import com.pe.hampcode.entity.Reservation;
import com.pe.hampcode.entity.Restaurant;
import com.pe.hampcode.repository.ReservationRepository;
import com.pe.hampcode.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BookingrestaurantApiApplication {

	private final CustomerRepository customerRepository;
	private final RestaurantRepository restaurantRepository;
	private final ReservationRepository reservationRepository;

	@Autowired
	public BookingrestaurantApiApplication(
			CustomerRepository customerRepository,
			RestaurantRepository restaurantRepository,
			ReservationRepository reservationRepository) {
		this.customerRepository = customerRepository;
		this.restaurantRepository = restaurantRepository;
		this.reservationRepository = reservationRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingrestaurantApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// Guardar algunos clientes
			Customer customer1 = new Customer(1l, "Jack", "Bauer","HHH");
			Customer customer4 = new Customer(1l, "Jack", "Bauer","HHH");
			customerRepository.save(customer1);

			Restaurant restaurant1 = new Restaurant(1l,"Restaurante A","Restaurante A");
			restaurantRepository.save(restaurant1);

			Reservation reservation1 = new Reservation(1L,LocalDateTime.now(), 4, customer1, restaurant1);
			reservationRepository.save(reservation1);

			// Consultar todos los clientes
			System.out.println("Clientes encontrados con findAll():");
			System.out.println("-------------------------------");
			customerRepository.findAll().forEach(System.out::println);
			System.out.println("");

			// Consultar una reserva por ID
			System.out.println("Reserva encontrada con findById(1L):");
			System.out.println("--------------------------------");
			Reservation foundReservation = reservationRepository.findById(1L).orElse(null);
			if (foundReservation != null) {
				System.out.println(foundReservation);
			}
			System.out.println("");

			// Consultar reservas por cliente
			System.out.println("Reservas encontradas para el cliente 'Jack Bauer':");
			System.out.println("--------------------------------------------");
			customerRepository.findByFirstName("Jack").ifPresent(customer -> {
				reservationRepository.findByCustomer(customer).forEach(System.out::println);
			});
			System.out.println("");
		};
	}
}
