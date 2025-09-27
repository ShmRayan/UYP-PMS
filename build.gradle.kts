plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
testImplementation("io.cucumber:cucumber-java:7.11.2")
    testImplementation("io.cucumber:cucumber-junit:7.11.2")
    testImplementation("io.cucumber:cucumber-core:7.11.2")

    // JUnit
    testImplementation("junit:junit:4.13.2")
    
}
sourceSets {
    main {
        java {
            srcDirs("src/main")
        }
    }
    test {
        java {
            srcDirs("src/test")
        }
    }
}


tasks.register<JavaExec>("cucumber") {
    group = "verification"
    description = "Run Cucumber features"
    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("io.cucumber.core.cli.Main")
    args = listOf(
    "--plugin", "pretty",
    "--plugin", "html:build/reports/cucumber.html",
    "--glue", "steps",
    "src/test/resources/contracts"
)
}
tasks.test {
    exclude("**/steps/**")
}
