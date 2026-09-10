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
    }
}

rootProject.name = "CreatorOS"
include(":app")
include(":core:model")
include(":core:security")
include(":core:ui")
include(":feature:auth")
include(":feature:feed")
include(":feature:live")
include(":feature:marketplace")
