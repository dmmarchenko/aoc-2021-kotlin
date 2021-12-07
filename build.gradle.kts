plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.nield:kotlin-statistics:1.2.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks {

    wrapper {
        gradleVersion = "7.3"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
