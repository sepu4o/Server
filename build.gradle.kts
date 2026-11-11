plugins {
    kotlin("jvm") version "2.0.21"
    id("application")
}

group = "ru.netology.pusher"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.firebase:firebase-admin:9.3.0")
}

application {
    mainClass.set("ru.netology.pusher.MainKt")
}