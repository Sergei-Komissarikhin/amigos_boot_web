package com.example.demo.student;


import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.time.Month.MAY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student sergei = new Student(
                    "Sergei",
                    LocalDate.of(1984, MAY, 22),
                    "sus@mail.ru"

            );
            Student anna = new Student(
                    "Anna",
                    LocalDate.of(1983, DECEMBER, 9),
                    "anna@mail.ru"

            );
            repository.saveAll(
                    List.of(sergei, anna)
            );
        };
    }
}
