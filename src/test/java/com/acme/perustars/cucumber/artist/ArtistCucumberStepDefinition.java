package com.acme.perustars.cucumber.artist;

import com.acme.perustars.cucumber.testcommons.TestHttpClient;
import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.resource.ArtistResource;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class ArtistCucumberStepDefinition {
    private final Logger log = LoggerFactory.getLogger(ArtistCucumberStepDefinition.class);

    @Autowired
    private TestHttpClient testHttpClient;
    @Autowired
    private ModelMapper mapper;

    @When("^I register an artist")
    public void RegisterArtist(Artist artist){

        ResponseEntity response = testHttpClient.Post("/artists", testHttpClient.ConvertToJson(artist));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ArtistResource artistResponse = ((ArtistResource) response.getBody());
        Artist artistGenerated = mapper.map(artistResponse, Artist.class);

        assertThat(artistGenerated).isEqualTo(artist);
    }

    @Then("^there should contains {int} artist registered")
    public void ShouldContain(final int quantity) {
        ResponseEntity response = testHttpClient.Get("/artists", Page.class);

        Page<ArtistResource> pageResponse = ((Page<ArtistResource>) response.getBody());
        List<ArtistResource> artistsResponse = pageResponse.getContent();

        try {
            log.info("Expecting {} artists. The registers contains {}", quantity, artistsResponse.get(0).getBrandName());
        }
        catch (Exception err) {
            assertThat(false).isTrue();
        }

        int artistsCount = artistsResponse.size();
        assertThat(artistsCount).isEqualTo(quantity);
    }
}
