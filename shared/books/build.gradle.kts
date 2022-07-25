plugins {
	id(Dependencies.Plugins.ANDROID_LIBRARY)
	id(Dependencies.Plugins.KOTLIN_ANDROID)
	kotlin(Dependencies.Plugins.KAPT)
	kotlin(Dependencies.Plugins.SERIALIZATION) version Dependencies.Versions.SERIALIZATION
	id(Dependencies.Plugins.HILT)
	`android-kotlin-convention`
	id(Dependencies.Plugins.PARCELIZE)
}

dependencies {

	kapt(Dependencies.Hilt.COMPILER)
	implementation(Dependencies.Hilt.ANDROID)

	implementation(Dependencies.Core.CORE)
	implementation(Dependencies.Core.APPCOMPAT)
	implementation(Dependencies.Core.MATERIAL)

	implementation(Dependencies.Network.OKHTTP)
	implementation(Dependencies.Network.RETROFIT)
	implementation(Dependencies.Network.KOTLINX_SERIALIZATION)

	implementation(Dependencies.Paging.PAGING)

	implementation(Dependencies.Room.RUNTIME)
	annotationProcessor(Dependencies.Room.ANNOTATION_COMPILER)
	kapt(Dependencies.Room.KAPT_COMPILER)
	implementation(Dependencies.Room.KTX)
	implementation(Dependencies.Room.PAGING)

	implementation(project(Modules.Core.CORE))
}