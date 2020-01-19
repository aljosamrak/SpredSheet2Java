plugins {
    kotlin("jvm") version "1.3.61"
}

group = "org.pinko"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // Read offline Excel files
    implementation("org.apache.poi:poi:4.1.1")
    implementation("org.apache.poi:poi-ooxml:4.1.1")

    // Generating Java co'de
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.15.9")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}