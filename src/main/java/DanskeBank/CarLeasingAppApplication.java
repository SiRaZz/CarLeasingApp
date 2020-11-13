package DanskeBank;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@SpringBootApplication
public class CarLeasingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLeasingAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		var messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}
}
