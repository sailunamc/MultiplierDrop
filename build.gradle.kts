plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "me.hqnkuh"
version = providers.gradleProperty("version").get()

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

tasks.processResources {
    inputs.properties(mapOf("version" to project.version as String))
    filesMatching("plugin.yml") {
        expand(mapOf("version" to project.version as String))
    }
}

tasks.runServer {
    minecraftVersion("1.21.8")
}

tasks.register("printVersion") {
    doLast { println(project.version as String) }
}