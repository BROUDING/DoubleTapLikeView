# DoubleTapLikeView [![Platform](https://img.shields.io/badge/Platform-Android-green.svg) ]()[![Build Status](https://travis-ci.org/BROUDING/DoubleTapLikeView.svg?branch=master)](https://travis-ci.org/BROUDING/DoubleTapLikeView) [![Download](https://api.bintray.com/packages/brouding/maven/android-doubletaplikeview/images/download.svg) ](https://bintray.com/brouding/maven/android-doubletaplikeview/_latestVersion)[![GitHub license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/brouding/doubletaplikeview/blob/master/LICENSE.txt)

DoubleTap-Like-View by BROUDING

![Sample Video](https://github.com/BROUDING/DoubleTapLikeView/blob/master/sample/sample_video.gif?raw=true)

---
# Sample .apk
You can download the latest sample APK from this repo here: https://github.com/brouding/DoubleTapLikeView/blob/master/sample/DoubleTapLikeViewSample.apk

---
# Gradle Dependency
### Repository
The Gradle dependency is available via [jCenter](https://bintray.com/brouding/maven/android-doubletaplikeview).
jCenter is the default Maven repository used by Android Studio.

The minimum API level supported by this library is API 14, Android 4.0 (ICE_CREAM_SANDWICH)


### Import to your project
add below code in `build.gradle (Module: app)`
```gradle
dependencies {
	// ... other dependencies here
    compile 'com.brouding:android-doubletaplikeview:0.1.5'
}
```
---
# How to use (programatically, xml)
### DoubleTapLikeView - programatically
```java
private DoubleTapLikeView mDoubleTapLikeView;

// DoubleTapLikeView is an extension of RelativeLayout
mDoubleTapLikeView = findViewById(R.id.layout_double_tap_like);

// Possible Methods
// mDoubleTapLikeView.disableAnimation(false);                              // You wouldn't use this...right?
// mDoubleTapLikeView.setAdjustViewBounds(true);
// mDoubleTapLikeView.setImageResource(R.drawable.ic_launcher_background);
// mDoubleTapLikeView.setIconResource(R.drawable.ic_heart);
// mDoubleTapLikeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
// mDoubleTapLikeView.setDoubleTapGap(200);                                // Keep this time short ( 200~400 )^

// Set Image to imageView directly
// Glide.with(this).load( IMAGE_URL ).into(mDoubleTapLikeView.imageView);

// So basically, you can use ImageView methods into "mDoubleTapLikeView.imageView"

mDoubleTapLikeView.setOnTapListener(new DoubleTapLikeView.OnTapListener() {
    @Override
    public void onDoubleTap(View view) {
        // If you want to customize animation, use method below and start your Animation here.
        // mDoubleTapLikeView.disableAnimation(false);

        Toast.makeText(thisActivity, "Double TAPPED !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTap() {
        // This method will be called if user didn't tap again after PRESS_TIME_TERM (default is 200)
        // So keep PRESS_TIME_GAP short ( 200~400 )^.
        // Due to Thread for single Tap, if you want to change UI through "onTap()", you should use Activity.runOnUiThread()
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(thisActivity, "Single TAPPED !", Toast.LENGTH_SHORT).show();
            }
        });
    }
});

```

### DoubleTapLikeView - xml
```java
<com.brouding.doubletaplikeview.DoubleTapLikeView
        android:id="@+id/layout_double_tap_like"
        android:layout_width="320dp"
        android:layout_height="320dp"
        app:src="@drawable/ic_launcher_background"
        app:icon="@drawable/ic_heart"
        app:scaleType="fitCenter"
        app:disableAnimation="false"
        app:adjustViewBounds="true"
        app:doubleTapIn="200" />
```

### Parameters
- `src`  : Resource `id` of main image
- `icon` : Resource `id` of animation image (default is heart image)
- `disableAnimation` : When the view is double tapped, there is a default animation with `icon`, but you can disable it.
In case you want to show your own animation effect.
- `doubleTapIn` : Millisecond time gap between 'onTap' and 'onDoubleTap', I don't recommend setting this time more than 500
- `scaleType`, `adjustViewBounds` are same parameters as in general ImageView

---
License
-------

    Copyright 2017 DoubleTapLikeView authors.

	- Jeongwon Lee (ssyjk2@gmail.com)

    All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.