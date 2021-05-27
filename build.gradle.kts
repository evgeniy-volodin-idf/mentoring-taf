import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion: String by project
val junitVersion: String by project
val mockkVersion: String by project
val seleniumVersion: String by project
val selenideVersion: String by project
val okhttpVersion: String by project
val wiremockVersion: String by project
val kotlinJdbcVersion: String by project
val mysqlConnectorJavaVersion: String by project
val log4j2Version: String by project
val log4j2KotlinVersion: String by project

plugins {
  kotlin("jvm") version "1.5.0"
  id("io.qameta.allure") version "2.8.1"
}

apply(plugin = "io.qameta.allure")

allure {
  autoconfigure = true
  version = "2.14.0"

  useJUnit5 {
    version = "2.14.0"
  }
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
  implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
  implementation("com.codeborne:selenide:$selenideVersion")
  implementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")
  implementation("mysql:mysql-connector-java:$mysqlConnectorJavaVersion")
  implementation("com.vladsch.kotlin-jdbc:kotlin-jdbc:$kotlinJdbcVersion")
  implementation( "org.apache.logging.log4j:log4j-api-kotlin:$log4j2KotlinVersion")
  implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
  implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
  testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
  }

  test {
    useJUnitPlatform()
  }
}