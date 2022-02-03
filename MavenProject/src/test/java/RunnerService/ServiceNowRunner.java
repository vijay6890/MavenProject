package RunnerService;

import FeaturesStep.BaseClassService;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/FeaturesServiceNow",glue="FeaturesStep",monochrome=true,publish=true)
public class ServiceNowRunner extends BaseClassService {


}
