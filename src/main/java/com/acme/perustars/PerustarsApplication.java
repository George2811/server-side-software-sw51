package com.acme.perustars;

import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PerustarsApplication {
    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PerustarsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup(){
        System.out.printf("Application Started");
        specialtyService.InitializeSpecialties();
        userService.InitializeUsers();

    }
}
