pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "SCIDTest"
include(
	":app",
	":core",
	":features:list",
	":features:detail",
	":shared:books",
)