package xihe.ji.pet_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = "xihe.ji.pet_springboot.dao")
@SpringBootApplication
public class JchNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JchNettyApplication.class, args);
    }
}
