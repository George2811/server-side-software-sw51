package com.acme.perustars.unit;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.repository.ArtistRepository;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.repository.SpecialtyRepository;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.exception.ResourceNotFoundException;
import com.acme.perustars.service.ArtistServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ArtistServiceImplTest {

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private SpecialtyRepository specialtyRepository;

    @MockBean
    private HobbyistRepository hobbyistRepository;



    @Autowired
    private ArtistService artistService;



    @TestConfiguration
    static class ArtistServiceImplTestConfiguration {
        @Bean
        public ArtistService artistService() {
            return new ArtistServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Artists By Artist Id With Valid Id Then Returns Artist")
    public void WhenGetArtistsByArtistIdWithValidIdThenReturnsArtist() {
        // Arrange
        Long id = 1000L;
        Artist artist = new Artist();
        artist.setId(id);
        when(artistRepository.findById(id))
                .thenReturn(Optional.of(artist));

        // Act
        Artist foundArtist = artistService.getArtistById(id);

        // Assert
        assertThat(foundArtist.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When Get Artist By Artist Id With Invalid Id The ReturnsException")
    public void WhenGetArtistByArtistIdWithInvalidIdTheReturnsException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(artistRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Artist", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Artist artist = artistService.getArtistById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
