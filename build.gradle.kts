import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.10" apply false
    kotlin("plugin.spring") version "1.5.10" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }
    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    val api by configurations
    val implementation by configurations
    val testImplementation by configurations
    dependencies {
        api(platform(BOM_COORDINATES))

        implementation(kotlin("stdlib"))
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
            languageVersion = "1.5"
        }
    }
}
