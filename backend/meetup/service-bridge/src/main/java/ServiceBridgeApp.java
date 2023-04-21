import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceBridgeApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBridgeApp.class, args);
    }
}
