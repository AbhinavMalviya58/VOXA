pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url  = uri("https://jitpack.io") }
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

rootProject.name = "My Application"
include(":app")
