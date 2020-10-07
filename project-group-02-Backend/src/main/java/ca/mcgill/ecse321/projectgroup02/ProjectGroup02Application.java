package ca.mcgill.ecse321.projectgroup02;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class ProjectGroup02Application {

  public static void main(String[] args) {
    SpringApplication.run(ProjectGroup02Application.class, args);
  }

  @RequestMapping("/")
  public String greeting(){
    return "Welcome to projectgroup02";
  }

}
