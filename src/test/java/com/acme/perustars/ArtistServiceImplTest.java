package com.acme.perustars;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.repository.ArtistRepository;
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

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ArtistServiceImplTest {
    @MockBean
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @TestConfiguration
    static class ArtistServiceImplTestConfiguration{
        @Bean
        public ArtistService artistService(){
            return new ArtistServiceImpl();
        }
    }

    public void WhenGetAllArtistsByHobbyistIdWithValidHobbyistIdThenReturnsArtists(){
        //Arrange
        Long hobbyistId = 1L;
//        Hobbyist hobbyist = new Hobbyist()
//                .setId(hobbyistId);
        //Act

        //Assert
    }

    @Test
    public void WhenGetArtistByIdWithValidArtistIdThenReturnsArtist(){
        //Arrange
        Long artistId = 1L;
//        Artist artist = new Artist()
//                .setId(artistId);

//        when(artistRepository.findById(artistId)).thenReturn(Optional.of(artist));

        //Act
        Artist foundArtist = artistService.getArtistById(artistId);

        //Assert
        assertThat(foundArtist.getId()).isEqualTo(artistId);
    }



}
