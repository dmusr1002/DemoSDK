plugins {
    id("java")
}

group = "org.lotr"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // For JSON serialization/deserialization
    implementation("io.reactivex.rxjava3:rxjava:3.1.4")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:4.13.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.12.5")
}

tasks.test {
    useJUnitPlatform()
}