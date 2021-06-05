package com.acme.perustars.unit;
import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.repository.SpecialtyRepository;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.service.SpecialtyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SpecialtyServiceImplTest {
    @MockBean
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private SpecialtyService specialtyservice;

    @TestConfiguration
    static class SpecialtyServiceImplTestConfiguration {
        @Bean
        public SpecialtyService specialtyService(){
            return new SpecialtyServiceImpl();}
    }

    public void whenGetSpecialtyByNameWithValidTitleThenReturnsSpecialty() {
        //Arrange
        String name = "Musica";
        Specialty specialty = new Specialty()
                .setId(1L)
                .setName(name);
        when(specialtyRepository.findByName(name)).thenReturn(Optional.of(specialty));

        //Act
        Specialty foundSpecialty = specialtyservice.getSpecialtyByName(name);

        //Assert
        assertThat(foundSpecialty.getName()).isEqualTo(name);
    }
}
