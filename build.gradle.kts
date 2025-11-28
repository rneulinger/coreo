plugins {
    scala
    //java
}

repositories {
    mavenCentral()
}

dependencies {
    // Scala 3 Standardbibliothek
    implementation("org.scala-lang:scala3-library_3:3.7.3")
    implementation("com.microsoft.playwright:playwright:1.53.0")

    // Cucumber für Scala 3
    testImplementation("io.cucumber:cucumber-scala_3:8.31.0")
    testImplementation("io.cucumber:cucumber-junit:7.27.0")
    testImplementation("org.scalatest:scalatest_3:3.2.19")
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnit()
}
