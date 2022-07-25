plugins {
	id(Dependencies.Plugins.ANDROID_LIBRARY)
	id(Dependencies.Plugins.KOTLIN_ANDROID)
	`android-kotlin-convention`
}

dependencies {

	implementation(Dependencies.Core.CORE)
	implementation(Dependencies.Core.APPCOMPAT)
	implementation(Dependencies.Core.MATERIAL)

	implementation(Dependencies.Navigation.UI)
	implementation(Dependencies.Navigation.FRAGMENT)
	implementation(Dependencies.Navigation.DYNAMIC_FEATURES)

	implementation(Dependencies.Layout.CONSTRAINT)
}