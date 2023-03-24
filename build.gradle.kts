
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("org.sonarqube") version "3.0"
    id("cz.swsamuraj.fortify") version "0.3.0"
    id("com.jfrog.artifactory") version "4.9.9"
    id("info.solidsoft.pitest") version "1.5.2"
    id("jacoco")
    id("io.micronaut.application") version "1.4.2"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.8.20-RC2"
}

apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

version = "0.1"
group = "com.sistemalima.spot"

val kotlinVersion=project.properties.get("kotlinVersion")

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.sistemalima.spot.*")
    }
}

dependencies {

    // Micronaut
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")


    kapt(platform("io.micronaut:micronaut-bom"))
    kapt("io.micronaut:micronaut-inject-java")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-tracing")

    // Cache
    implementation("io.micronaut.cache:micronaut-cache-core")
    implementation("io.micronaut.cache:micronaut-cache-caffeine")

    // Tracing & Metrics
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    runtimeOnly("io.jaegertracing:jaeger-thrift")

    // Http dependencies
    implementation("io.micronaut:micronaut-http-client")
    implementation("org.json:json:20220320")

    // Mapper
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")

    // Database

    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Tests
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:testcontainers")

    kaptTest("io.micronaut:micronaut-inject-java")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("com.github.tomakehurst:wiremock:2.27.2")
    testImplementation("io.micronaut.test:micronaut-test-junit5")
    testImplementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")

    implementation("io.micronaut.jms:micronaut-jms-activemq-classic")
    implementation("io.micronaut.jms:micronaut-jms-activemq-artemis")

    implementation("io.micronaut.jms:micronaut-jms-sqs")

}

application {
    mainClass.set("com.example.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
