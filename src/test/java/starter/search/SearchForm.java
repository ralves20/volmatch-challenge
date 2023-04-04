package starter.search;

import net.serenitybdd.screenplay.targets.Target;

public class SearchForm {
    public static Target SEARCH_FIELD = Target.the("search field").locatedBy("//textarea[@name='q']");

    static Target TEMPERATURE_RESULT = Target.the("'temperature result' number").locatedBy("//span[@id='wob_tm']");

}
