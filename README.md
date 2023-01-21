# Android-Loading-Dots
simple Library to have Progresses dots loading
## Developed by Mahdi Razzaghi Ghaleh

# Step1. Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

# Step 2. Add the dependency
	dependencies {
	        implementation 'com.github.razaghimahdi:Android-Loading-Dots:1.2.0'
	}

# Step 3. How to use
```xml

        <com.razzaghimahdi78.dotsloading.LoadingDotsGetWave
            android:layout_width="65dp"
            android:layout_height="65dp"
            app:wave_dots_color="@color/colorPrimaryDark"
            app:wave_dots_count="3"
            app:wave_dots_duration="500" />

        <com.razzaghimahdi78.dotsloading.LoadingDotsGetFade
            android:layout_width="65dp"
            android:layout_height="65dp"
            app:fade_dots_color="@color/colorPrimaryDark"
            app:fade_dots_count="3"
            app:fade_dots_duration="500" />

        <com.razzaghimahdi78.dotsloading.LoadingDotsGetBigger
            android:layout_width="65dp"
            android:layout_height="65dp"
            app:bigger_dots_color="@color/colorPrimaryDark"
            app:bigger_dots_count="3"
            app:bigger_dots_duration="500"/>
```

Done !!!


https://user-images.githubusercontent.com/61207818/213888322-69fdb878-56c5-496a-be8e-1760d8d16c97.mp4



