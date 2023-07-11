package com.example.practica;

import com.example.practica.entities.Laptop;
import com.example.practica.entities.Primito;
import com.example.practica.repositories.LaptopRepository;
import com.example.practica.repositories.PrimitoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootApplication
public class PracticaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PracticaApplication.class, args);
		PrimitoRepository primitoRepository = context.getBean(PrimitoRepository.class);

		Primito kuko = new Primito(null, "Marcos Uriel", 21);
		Primito fufo = new Primito(null, "Diego Aguirre", 22);

		primitoRepository.save(kuko);
		primitoRepository.save(fufo);

		/**
		 * LAPTOPS
		 */

		Laptop Lenovo = new Laptop(null, "Lenovo", "Legion", 19999);
		Laptop Apple = new Laptop(null, "Apple", "Macbook", 29999);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
		laptopRepository.save(Lenovo);
		laptopRepository.save(Apple);

	}

}
