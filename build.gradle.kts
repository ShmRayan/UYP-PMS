plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.5"
    id("java")
}

group = "com.uypms"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}


repositories {
    mavenCentral()
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security") 
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("io.cucumber:cucumber-java:7.11.2")
    testImplementation("io.cucumber:cucumber-junit:7.11.2")
    testImplementation("io.cucumber:cucumber-core:7.11.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}


configurations {
    all {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}


sourceSets {
    main {
        java.setSrcDirs(listOf("src/main/java"))
        resources.setSrcDirs(listOf("src/main/resources"))
    }
    test {
        java.setSrcDirs(listOf("src/test/java"))
        resources.setSrcDirs(listOf("src/test/resources"))
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
    useJUnitPlatform()
    exclude("**/steps/**")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    jvmArgs = listOf("--enable-preview")
}
