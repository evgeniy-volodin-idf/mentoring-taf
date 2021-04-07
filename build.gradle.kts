plugins {
  kotlin("jvm") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("com.fasterxml.jackson.core:jackson-databind:2.12.2")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.2")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.2")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0-M1")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0-M1")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0-M1")
}
