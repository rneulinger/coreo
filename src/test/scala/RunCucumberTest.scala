import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources"),
  glue = Array("steps"),
  plugin = Array("pretty")
)
class RunCucumberTest {
  println( "TEST "* 10)
}