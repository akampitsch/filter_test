import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions (
    plugin = {"pretty", "json:target/jsonreports/cucumber.json", "junit:target/junit_reports/cucumberjunit.xml"},
    glue = {"com.cyansecurity.filter"},
    features
        = "src/test/resources/com/cyansecurity/filter/dns/SimpleDnsLookup.feature",
    tags = {"not @ignored", "not @wip"})
public class RunSimpleDnsLookup_Test
    extends AbstractTestNGCucumberTests
{
    private static final Logger logger = LogManager.getLogger ();

    /*
    this can be used to have scenarios executed multi-threaded, but it jumbles the console output
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios()
    {
        return super.scenarios ();
    }*/

    @AfterSuite
    public void generateReports ()
    {
        logger.info ("***** Generating Reports *****");

        File reportOutputDirectory = new File ("target");

        List<String>  jsonFiles     = new ArrayList<> ();
        File          e             = new File ("target/jsonreports/cucumber.json");
        Configuration configuration = new Configuration (reportOutputDirectory, "CyanSecurity Filter Tests");
        configuration.addPresentationModes (PresentationMode.EXPAND_ALL_STEPS);

        //configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));

        jsonFiles.add (e.getAbsolutePath ());
        ReportBuilder reportBuilder = new ReportBuilder (jsonFiles, configuration);
        reportBuilder.generateReports ();

        logger.info ("***** Finished generating Reports *****");
    }
}