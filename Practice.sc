import $ivy.`com.microsoft.playwright:playwright:1.53.0`
import $cp.`./build/libs/coreo.jar`
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.*

class Practice extends practice.PracticeApp{
}

lazy val `/` = new Practice()

//P.home