plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "it.wldt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://git.informatik.uni-hamburg.de/api/v4/groups/sane-public/-/packages/maven")
    }
    maven {
        url = uri("https://maven.pkg.github.com/Web-of-Digital-Twins/wldt-wodt-adapter")
        credentials {
            username = project.findProperty("ghPackagesUsername")?.toString()
            password = project.findProperty("ghPackagesPwd")?.toString()
        }
    }
}

dependencies {
    implementation("io.github.wldt:wldt-core:0.4.0")
    implementation("io.github.webbasedwodt:wldt-wodt-adapter:5.2.1")
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:latest.release")
}

application {
    mainClass = "it.wldt.MainKt"
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}