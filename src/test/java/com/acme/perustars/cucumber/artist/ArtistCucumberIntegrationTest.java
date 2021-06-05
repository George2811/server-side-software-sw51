package com.acme.perustars.cucumber.artist;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/artist",
                 plugin = {"pretty", "html:target/cucumber/artist"},
                 extraGlue = "com.acme.perustars.cucumber.testcommons")
public class ArtistCucumberIntegrationTest {
}
