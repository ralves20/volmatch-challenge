package starter.search;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class TheOpenWeatherTemperature implements Question<String> {

    String city;
    public TheOpenWeatherTemperature(String city){
        this.city = city;
    }
    @Override
    public String answeredBy(Actor actor) {

        String apiKey = "982cbda04975e150baea62295cad189d";
        String theRestApiBaseUrl = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey+"&units=metric";
        actor.whoCan(CallAnApi.at(theRestApiBaseUrl));
        actor.attemptsTo(
                Get.resource(theRestApiBaseUrl)
        );
        String openWeatherTemp = SerenityRest.lastResponse().path("main.temp").toString();

        return openWeatherTemp;
    }

    public static TheOpenWeatherTemperature forCityOf(String city){
        return new TheOpenWeatherTemperature(city);
    }
}
