import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    idea
    jacoco
    checkstyle
    id("io.freefair.lombok") version "8.4"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.hexlet.HelloWorld")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.apache.commons:commons-lang3:3.14.0")
    implementation("com.google.guava:guava:33.0.0-jre")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("com.googlecode.owasp-java-html-sanitizer:owasp-java-html-sanitizer:20220608.1")

    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")




    implementation("io.javalin:javalin:5.6.3")
    implementation("org.slf4j:slf4j-simple:2.1.0-alpha1")
    implementation("io.javalin:javalin-rendering:5.6.3")
    implementation("gg.jte:jte:3.1.6")

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testImplementation("org.assertj:assertj-core:3.25.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.1")


}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}