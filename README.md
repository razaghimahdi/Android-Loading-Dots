# Android-Loading-Dots
simple Library to have Progresses dots loading
## Developed by Mahdi Razzaghi Ghaleh

https://jitpack.io/#razaghimahdi/Android-Loading-Dots

# Step1. Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

# Step 2. Add the dependency
	dependencies {
	        implementation 'com.github.razaghimahdi:Android-Loading-Dots:Tag'
	}
	Current Ver:"1.0.0"

Done !!!

# NOTE:
To change width and height:
android:layout_width="50dp"
android:layout_height="30dp"
To change dot color:
android:background="@color/exampleColor"


<img src="screenshots/Screenshot_1603010713.png" width="300">
<img src="screenshots/Screenshot_1603010714.png" width="300">


        <com.razzaghimahdi78.dotsloading.LoadingDotsGetWave
            android:layout_width="65dp"
            android:layout_height="65dp"
            android:background="@color/colorPrimary" />

        <com.razzaghimahdi78.dotsloading.LoadingDotsGetFade
            android:layout_width="65dp"
            android:layout_height="65dp"
            android:background="@color/colorPrimary" />

        <com.razzaghimahdi78.dotsloading.LoadingDotsGetBigger
            android:layout_width="65dp"
            android:layout_height="65dp"
            android:background="@color/colorPrimary" />

