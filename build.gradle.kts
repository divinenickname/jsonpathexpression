import net.thebugmc.gradle.sonatypepublisher.PublishingType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.9.22"
    id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.2.2"
    signing
    `java-library`
    jacoco
}

description = "Library for mathematical and logical operations on JSON paths."
version = System.getenv("GIT_TAG") ?: "1.0.0-SNAPSHOT"
group = "org.ilinykh.kotlin.logic-json-path"
java.targetCompatibility = JavaVersion.VERSION_17
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("com.jayway.jsonpath:json-path:2.9.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")
    testImplementation("org.mockito:mockito-core:5.11.0")
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
}

tasks.withType<Jar>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        csv.required.set(true)
        xml.required.set(true)
    }
}

signing {
    useGpgCmd()
}

centralPortal {
    publishingType.set(PublishingType.USER_MANAGED)

    username.set(System.getenv("SONATYPE_USER"))
    password.set(System.getenv("SONATYPE_PASSWORD"))

    pom {
        name.set("Logic Json-Path")
        url.set("https://github.com/divinenickname/logic-json-path")

        developers {
            developer {
                name.set("Alexander Ilinykh")
                email.set("public@ilinykh.org")
            }
        }

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://opensource.org/license/apache-2-0")
            }
        }

        scm {
            connection.set("scm:git:git@github.com:divinenickname/logic-json-path.git")
            developerConnection.set("scm:git:git@github.com:divinenickname/logic-json-path.git")
            url.set("https://github.com/divinenickname/logic-json-path")
        }

        withXml {
            asNode().appendNode("dependencies").let {
                for (dependency in configurations["api"].dependencies) {
                    it.appendNode("dependency").apply {
                        appendNode("groupId", dependency.group)
                        appendNode("artifactId", dependency.name)
                        appendNode("version", dependency.version)
                    }
                }
            }
        }
    }
}
