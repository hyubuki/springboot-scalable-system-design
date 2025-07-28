package app.hyubuki.articleread.health;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

  @Value("${spring.application.name}")
  private String applicationName;

  @GetMapping("/health")
  public String healthCheck() {
    return applicationName + " is running!";
  }
}
