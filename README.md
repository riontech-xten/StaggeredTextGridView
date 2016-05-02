# StaggeredTextGridView
A simple and more attractive custom text gridview designed base on metrial design. 
* Auto adjust columns base on device width and height. 
* Supported with portrait and landscape orientations. 
* Extends [ScrollView](http://developer.android.com/reference/android/widget/ScrollView.html)

<br>
<img src="https://github.com/riontech-xten/StaggeredTextGridView/blob/master/Screenshot_2016-04-28-19-52-08.png" height="600" alt="StaggeredTextGridView"/>
</br>

# Setup
To use **StaggeredTextGridView** in your projects, simply add the library as a dependency to your build.

##### Maven
```
<dependency>
  <groupId>com.riontech.staggeredtextgridview</groupId>
  <artifactId>staggeredtextgridview</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
##### Gradle
```
dependencies {
  compile 'com.riontech.staggeredtextgridview:staggeredtextgridview:1.0.0'
}
```

Alternatively you can directly import the /library project into your Android Studio project and add it as a dependency in your build.gradle.

The library is currently configured to be built via Gradle only. It has the following dependencies:

* Android Gradle plugin 2.1.0 - com.android.tools.build:gradle:2.1.0
* Android Support appcompt-v7 - com.android.support:appcompat-v7:23.1.1
* Compiled SDK Version        - lollipop-23
* Supported Version           - sdk version >= 7

# Usage
For more detailed code example to use the library, Please refere the `/sample` app.

`StaggeredTextGridView` can be added as a custom view to any layout.

```
<com.riontech.staggeredtextgridview.StaggeredTextGridView
        android:id="@+id/staggeredTextView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/toolbar" />
```

Simply create the object of `StaggeredTextGridView` and set your custom `BaseAdapter`
```
StaggeredTextGridView staggeredTextGridView = (StaggeredTextGridView) findViewById(R.id.staggeredTextView);
CustomAdapter adapter = new CustomAdapter(this, wordJsonArray);
staggeredTextGridView.setmAdapter(adapter);
```

# TODO
1. Item selector drawable
2. Item click listener
