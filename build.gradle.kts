// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.gms) apply false

    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.plugin.serialization) apply false

    alias(libs.plugins.ksp.plugin) apply false
    alias(libs.plugins.hilt.plugin) apply false

}