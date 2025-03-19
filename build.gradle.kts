plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.composeMultiplatform) apply false
  alias(libs.plugins.composeCompiler) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  // Ksp
  alias(libs.plugins.kspCompose)
  // Room
  alias(libs.plugins.room)
  // Serialization
  alias(libs.plugins.serialization) apply false
  // Ktlint & Detekt
  alias(libs.plugins.ktlint) apply false
  alias(libs.plugins.detekt) apply false
}

allprojects {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
    maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
  }
}
