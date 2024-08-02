plugins {
    id("java")
}

group = "ru.silicium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.25.0"
// Define the version of AspectJ
val aspectJVersion = "1.9.21"

// Define configuration for AspectJ agent
val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    // Add aspectjweaver dependency
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.23.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Import allure-bom to ensure correct versions of all the dependencies are used
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    // Add necessary Allure dependencies to dependencies section
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.test {
    useJUnitPlatform()
    // Configure javaagent for test execution
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}