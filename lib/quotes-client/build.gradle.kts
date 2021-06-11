dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(platform("io.strikt:strikt-bom:0.31.0"))
    implementation("io.strikt:strikt-core")
}