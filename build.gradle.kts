import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.dmitrii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.hexagonkt:core:2.0.2")
    implementation("com.hexagonkt:http_server_jetty:2.0.2")
    implementation("com.hexagonkt:http_client_jetty:2.0.2")
    implementation("com.hexagonkt:templates_freemarker:2.0.2")
    implementation("com.hexagonkt:serialization_jackson_json:2.0.2")

    implementation("com.hexagonkt:http_server_jetty:2.0.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}