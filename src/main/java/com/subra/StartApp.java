package com.subra;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.subra.model.Customer;
import com.subra.model.CustomerRepository;

@SpringBootApplication
public class StartApp implements CommandLineRunner {

	private CustomerRepository cusomerRepository;
	
	
	@Autowired
	public StartApp(CustomerRepository cusomerRepository) {
		this.cusomerRepository = cusomerRepository;
	}

	public static void main(String ... args) {	
		SpringApplication.run(StartApp.class, args);

	}

	public void run(String... arg0) throws Exception {

		//cusomerRepository.save(new Customer( "John", "Doe"));
		//cusomerRepository.save(new Customer( "Abrams", "David"));
		System.out.println("---------------retrieved----------");
		//List<Customer> l = cusomerRepository.findAll();
		List<Customer> l = cusomerRepository.findByLname("Doe");
		
		l.forEach(System.out::println);
		System.out.println("count=" + cusomerRepository.count() );
		System.out.println(". . . . . .paging now  . . . . . ");
		Pageable pageable = new PageRequest(0, 5);
		Page<Customer> page =  cusomerRepository.findByLname("Doe", pageable);
		page.forEach(c -> System.out.println("on "  + c));
		pageTheResult(pageable, page);
		
	}
	
	private void pageTheResult(Pageable pageable, Page<Customer> page){
		Scanner scan = new Scanner(System.in);
		while(true){
			String asked = scan.nextLine();
			if(asked.equals("end")) {break;}
			else if(asked.equals("pre")){
				pageable =  pageable.previousOrFirst();
			} else if(asked.equals("next")){
				pageable =  pageable.next();
			}
			page =  cusomerRepository.findByLname("Doe", pageable);
			System.out.println("--------on " + pageable.getOffset() + "------");
			page.forEach(c -> System.out.println( ": "  + c));
		}
	}

}//class ends
