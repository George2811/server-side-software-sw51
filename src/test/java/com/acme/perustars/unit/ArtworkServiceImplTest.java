package com.acme.perustars.unit;

import com.acme.perustars.domain.model.Artwork;
import com.acme.perustars.domain.repository.ArtistRepository;
import com.acme.perustars.domain.repository.ArtworkRepository;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.domain.service.ArtworkService;
import com.acme.perustars.exception.ResourceNotFoundException;
import com.acme.perustars.service.ArtistServiceImpl;
import com.acme.perustars.service.ArtworkServiceImpl;
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
public class ArtworkServiceImplTest {
    @MockBean
    private ArtworkRepository artworkRepository;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private HobbyistRepository hobbyistRepository;

    @Autowired
    private ArtworkService artworkService;

    @TestConfiguration
    static class ArtworkServiceImplTestConfiguration{
        @Bean
        public ArtworkService artworkService(){
            return new ArtworkServiceImpl();
        }
    }

    @Test
    @DisplayName("When getArtworkByIdAndArtistId With Valid Ids Then Returns Artwork")
    public void whenGetArtworkByIdAndArtistIdWithValidIdsThenReturnsArtwork(){
        // Arrange
        Long artworkId =  1L;
        Long artistId =  1L;
        Artwork artwork = new Artwork()
                .setId(artworkId);
        when(artworkRepository.findByIdAndArtistId(artworkId,artistId)).thenReturn(Optional.ofNullable(artwork));
        // Act
        Artwork foundArtwork = artworkService.getArtworkByIdAndArtistId(artistId, artworkId);
        // Assert
        assertThat(foundArtwork.getId()).isEqualTo(artworkId);
    }
    @Test
    @DisplayName("When getArtworkByIdAndArtistId With Invalid Ids Then Returns ResourceNotFoundExceptions")
    public void whenGetArtworkByIdAndArtistIdWithInvalidIdsThenReturnsResourceNotFoundExceptions(){
        // Arrange
        Long artworkId =  1L;
        Long artistId =  1L;
        String template = "Artwork not found with Id %s and ArtistId %s";
        Artwork artwork = new Artwork()
                .setId(artworkId);
        when(artworkRepository.findByIdAndArtistId(artworkId,artistId)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, artistId, artworkId);
        // Act
        Throwable exception = catchThrowable(()->{
            Artwork foundArtwork = artworkService.getArtworkByIdAndArtistId(artistId, artworkId);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
