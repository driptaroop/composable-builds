plugins {
    id("org.springframework.boot")
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(project(":lib:shout-quotes"))
}