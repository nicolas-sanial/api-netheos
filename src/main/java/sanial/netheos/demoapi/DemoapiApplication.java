package sanial.netheos.demoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sanial.netheos.demoapi.core.model.Faq;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.model.Tag;
import sanial.netheos.demoapi.core.repository.FaqRepository;
import sanial.netheos.demoapi.core.repository.FaqTagRepository;
import sanial.netheos.demoapi.core.repository.TagRepository;

@SpringBootApplication
public class DemoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoapiApplication.class, args);
	}

	//uncomment this following lines to auto generate data in database
	/*
	@Bean
	CommandLineRunner initDatabase(FaqRepository faqRepository, TagRepository tagRepository, FaqTagRepository faqTagRepository) {
		return args -> {
			faqTagRepository.save(new FaqTag(new Faq("Pouvez vous garantir la robustesse du produit ?", "Nous sommes certains d'utiliser des technos à jour et nous respectons les normes de code"), new Tag("Sécurité")));
			faqTagRepository.save(new FaqTag(new Faq("Comment sont gérés les informations utilisateurs au sein de votre produit ?", "Notre produit est RGPD compliant, nous respectons lse normes imposées par la CNIL"), new Tag("Robustesse")));
		};
	}
	*/
}
