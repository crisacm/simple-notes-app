import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
  // Ksp
  alias(libs.plugins.kspCompose)
  // Room
  alias(libs.plugins.room)
  // Serialization
  alias(libs.plugins.serialization)
  // Ktlint & Detekt
  alias(libs.plugins.ktlint)
  alias(libs.plugins.detekt)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)

      // Ktor
      implementation(libs.ktor.client.okhttp)

      // Koin
      implementation(libs.koin.android)
      implementation(libs.koin.androidx.compose)

      // Room
      implementation(libs.room.runtime.android)

      // Accompanist
      implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtime.compose)
      // Add other dependencies here

      // Coil
      implementation(libs.coil.compose)
      implementation(libs.coil.network.ktor)

      // Ktor
      implementation(libs.ktor.client.core)

      // Koin
      api(libs.koin.core)
      implementation(libs.koin.compose)
      implementation(libs.koin.composeVM)

      // Navigation
      implementation(libs.navigation.compose)

      // Room
      implementation(libs.room.runtime)
      // SQLiteBundled
      implementation(libs.sqlite)
      implementation(libs.sqlite.bundled)

      // Data Store
      implementation(libs.data.store)
      implementation(libs.data.store.preferences)

      // JSON Serialization
      implementation(libs.kotlinx.serialization.json)

      // Kotlinx DateTime
      implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
    }
    iosMain.dependencies {
      // Ktor
      implementation(libs.ktor.client.darwin)
    }
  }
}

android {
  namespace = "com.github.crisacm"
  compileSdk =
    libs.versions.android.compileSdk
      .get()
      .toInt()

  defaultConfig {
    applicationId = "com.github.crisacm.justnotes"
    minSdk =
      libs.versions.android.minSdk
        .get()
        .toInt()
    targetSdk =
      libs.versions.android.targetSdk
        .get()
        .toInt()
    versionCode = 1
    versionName = "1.0"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  debugImplementation(compose.uiTooling)

  add("kspAndroid", libs.room.compiler)
  add("kspIosSimulatorArm64", libs.room.compiler)
  add("kspIosX64", libs.room.compiler)
  add("kspIosArm64", libs.room.compiler)
}

room {
  schemaDirectory("$projectDir/schemas")
}

ksp {
  arg("room.schemaLocation", "$projectDir/schemas")
}
