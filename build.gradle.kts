buildscript {
    repositories {
        mavenCentral()
        maven("https://packages.confluent.io/maven/")
        gradlePluginPortal()
    }
}


plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "2.3.2"
}

group = "uz.nodir"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.avro:avro:1.12.0")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}


avro {
    setCreateSetters(true)
    setFieldVisibility("private")
    setOutputCharacterEncoding(Charsets.UTF_8)
    setStringType("String")
    setEnableDecimalLogicalType(true)

}

val schemaRegistryURL = "http://localhost:8081" // Replace with your Schema Registry URL

/**
 * I'm used plugin from ImFlog -> https://github.com/ImFlog/schema-registry-plugin
 *
 *     When you install the plugin, four tasks are added under registry group:
 *
 *     downloadSchemasTask
 *     testSchemasTask
 *     registerSchemasTask
 *     configSubjectsTask
 *
 */


schemaRegistry {


    url.set(schemaRegistryURL) // is where the script can reach the Schema Registry.
    quiet.set(true) // is whether you want to disable "INFO" level logs. This can be useful if you test the compatibility of a lot of schema.
    pretty.set(true) //is whether the downloaded Avro or json schemas should be formatted ("pretty-printed") or minified. This is an optional parameter.


    register {
        subject("users-value", "src/main/avro/User.avsc", "AVRO")
    }


}
tasks.withType<Test> {
    useJUnitPlatform()
}
