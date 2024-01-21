package com.example.ecommerce_project;

import com.example.ecommerce_project.dao.CategoryRepository;
import com.example.ecommerce_project.dao.ProductRepository;
import com.example.ecommerce_project.entities.Category;
import com.example.ecommerce_project.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EcommerceProjectApplication implements CommandLineRunner {
	@Autowired
    private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {

		SpringApplication.run(EcommerceProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);

		categoryRepository.save(new Category(null,"Computers",null,null,null));
		categoryRepository.save(new Category(null,"Printers",null,null,null));
		categoryRepository.save(new Category(null,"Smart phones",null,null,null));

		Random rnd = new Random();
		categoryRepository.findAll().forEach(c->{

			for(int i =0; i<10; i++){
				Product p = new Product();
				p.setName(UUID.randomUUID().toString().substring(0, 18));
				p.setCurrentPrice(100+ rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setPhotoName("unknown.png");
				p.setCategory(c);
				productRepository.save(p);
			}
		});

	}
}
