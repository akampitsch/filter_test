import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith (Cucumber.class)
@CucumberOptions (plugin = "pretty",
                  features = "src/test/resources/com/cyansecurity/filter/dns/SimpleDnsLookup.feature")
public class RunSimpleDnsLookup_Test
{
}