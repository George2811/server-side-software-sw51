package com.acme.perustars.unit;
import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.repository.SpecialtyRepository;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.exception.ResourceNotFoundException;
import com.acme.perustars.service.SpecialtyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SpecialtyServiceImplTest {
    @MockBean
    private SpecialtyRepository specialtyRepository;

    @MockBean
    private HobbyistRepository hobbyistRepository;

    @Autowired
    private SpecialtyService specialtyservice;

    @TestConfiguration
    static class SpecialtyServiceImplTestConfiguration {
        @Bean
        public SpecialtyService specialtyService(){
            return new SpecialtyServiceImpl();}
    }

    @Test
    @DisplayName("When getSpecialtyByName With Valid Title Then Returns Specialty")
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

    @Test
    @DisplayName("when GetSpecialtyByName With Invalid Title Then Returns ResourceNotFoundExceptions")
    public void whenGetSpecialtyByNameWithInvalidTitleThenReturnsResourceNotFoundExceptions() {
        //Arrange
        String name = "Musica";
        String template = "Resource Specialty not found for Name with value %s";
        Specialty specialty = new Specialty()
                .setId(1L)
                .setName(name);
        when(specialtyRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, name);
        //Act
        Throwable exception = catchThrowable(()->{
            Specialty foundSpecialty = specialtyservice.getSpecialtyByName(name);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When GetSpecialtiesBySpecialtyId With Valid Id Then Returns Specialty")
    public void WhenGetSpecialtiesBySpecialtyIdWithValidIdThenReturnsSpecialty() {
        // Arrange
        Long id = 1L;
        Specialty specialty = new Specialty();
        specialty.setId(id);
        when(specialtyRepository.findById(id))
                .thenReturn(Optional.of(specialty));

        // Act
        Specialty foundSpecialty = specialtyservice.getSpecialtyById(id);

        // Assert
        assertThat(foundSpecialty.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When Get SpecialtyBySpecialtyId With Invalid Id The ReturnsException")
    public void WhenGetSpecialtyBySpecialtyIdWithInvalidIdTheReturnsException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(specialtyRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Specialty", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Specialty specialty = specialtyservice.getSpecialtyById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
