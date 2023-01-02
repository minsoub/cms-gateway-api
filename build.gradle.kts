import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

group = "com.bithumbsystems"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.3"

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:3.1.0")

    implementation("io.prometheus:simpleclient:0.14.1")
    implementation("io.prometheus:simpleclient_hotspot:0.14.1")
    implementation("io.prometheus:simpleclient_httpserver:0.14.1")
    implementation("io.micrometer:micrometer-registry-prometheus:1.9.0")
    implementation("net.logstash.logback:logstash-logback-encoder:7.1.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = true
        mainClass.set("com.bithumbsystems.cms.CmsGatewayApiApplication")
    }

    jar {
        enabled = false
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}