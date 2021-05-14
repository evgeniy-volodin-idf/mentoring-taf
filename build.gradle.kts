import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion: String by project
val junitVersion: String by project
val mockkVersion: String by project
val seleniumVersion: String by project
val selenideVersion: String by project
val okhttpVersion: String by project

plugins {
  kotlin("jvm") version "1.5.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
  implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
  testImplementation("io.mockk:mockk:${mockkVersion}")
  implementation("com.codeborne:selenide:$selenideVersion")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
  }
}