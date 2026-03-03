plugins {
    id("java")
    alias(libs.plugins.plugin.yml.paper)
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.licenser)
    alias(libs.plugins.lombok)
}

group = "cc.happyareabean"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.bundles.lamp)
}

tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.runServer {
    minecraftVersion("1.21.11")
    runDirectory(projectDir.resolve(".run"))
}

paper {
    main = "cc.happyareabean.mobduplicate.MobDuplicate"
    name = "MobDuplicate"
    apiVersion = "1.19"
    author = "HappyAreaBean"

    generateLibrariesJson = false
}

tasks.withType(xyz.jpenilla.runtask.task.AbstractRun::class) {
    javaLauncher = javaToolchains.launcherFor {
        vendor = JvmVendorSpec.JETBRAINS
        languageVersion = JavaLanguageVersion.of(21)
    }
    jvmArgs("-XX:+AllowEnhancedClassRedefinition")
}

license {
    rule(file("LICENSE_HEADER"))

    exclude("**/ModrinthUpdateChecker.java")

    include("**/*.java")
    include("**/*.java.peb")
}