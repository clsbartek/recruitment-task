package pl.empik.recruitmenttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import pl.empik.recruitmenttask.exception.UserNotFoundException;

import java.io.IOException;

@SpringBootApplication
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.errorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
					throw new UserNotFoundException("Request resource has not been found");
				}
				super.handleError(response);
			}
		}).build();
	}
}
