package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import org.junit.Assert;
import starter.navigation.NavigateTo;
import starter.search.LookForInformation;
import starter.search.SearchForm;
import starter.search.TheOpenWeatherTemperature;
import starter.search.TheTemperatureOnGoogle;

import java.time.Duration;

public class SearchStepDefinitions {

    @Given("{actor} is researching things on the internet")
    public void researchingThings(Actor actor) {
        actor.wasAbleTo(NavigateTo.theGooglePage());
    }

    @And("{actor} searches for {string}")
    public void heSearchesFor(Actor actor, String term) {
        actor.attemptsTo(
                Wait.until(
                        WebElementQuestion.the(SearchForm.SEARCH_FIELD) , WebElementStateMatchers.isPresent()).forNoMoreThan(Duration.ofSeconds(30)),
                LookForInformation.about(term)
        );
    }

    @When("{actor} verifies the temperature in the location that he searched")
    public void heVerifiesTheTemperatureInTheLocationThatHeSearched(Actor actor) {
        String temperatureOnGoogle = actor.asksFor(
                TheTemperatureOnGoogle.forCurrentLocation()
        );
        actor.remember("googleTemperature", temperatureOnGoogle);
    }

    @Then("{actor} should see that the temperature is the same as the existant in the OpenWeatherMap for {string}")
    public void heShouldSeeThatTheTemperatureIsTheSameAsTheExistantInTheOpenWeatherMapFor(Actor actor, String city) {
        String googleTemperature = actor.recall("googleTemperature");
        String theOpenWeatherTemperatureForCity = actor.asksFor(TheOpenWeatherTemperature.forCityOf(city));
        System.out.println(theOpenWeatherTemperatureForCity);

        Assert.assertTrue("The temperature for google is: "+googleTemperature+" and the temperature from OpenWeatherAPI is: "+theOpenWeatherTemperatureForCity+""
                ,theOpenWeatherTemperatureForCity.contains(googleTemperature));

    }
}
