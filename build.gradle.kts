import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.8.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("io.gitlab.arturbosch.detekt") version "1.0.0.RC6-4"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	compile("org.springframework.boot:spring-boot-starter-data-jpa")
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("com.h2database:h2")
	compile("org.postgresql:postgresql:42.2.5")
	compile("org.springframework.boot:spring-boot-starter-data-jpa")
	compile("org.modelmapper.extensions:modelmapper-spring:2.3.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

detekt {
	version = "1.0.0.RC6-4"
	defaultProfile {
		input = "$projectDir/src"
		config = "$projectDir/default-detekt-config.yml" // Code style rules file.
		filters = ".*/res/.*,.*build/.*"
	}
}