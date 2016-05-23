# StaggeredTextGridView [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-StaggeredTextGridView-green.svg?style=true)](https://android-arsenal.com/details/1/3543)[ ![Download](https://api.bintray.com/packages/vaghelamithun/maven/staggeredtextgridview/images/download.svg) ](https://bintray.com/vaghelamithun/maven/staggeredtextgridview/_latestVersion)

A simple and attractive custom text GridView design based on material concepts. 
* Auto adjustable column based on device width and height. 
* Supported with portrait and landscape orientations. 
* Extends [ScrollView](http://developer.android.com/reference/android/widget/ScrollView.html)

<br>
<img src="https://github.com/riontech-xten/StaggeredTextGridView/blob/master/Screenshot_2016-04-28-19-52-08.png" height="600" alt="StaggeredTextGridView"/>
</br>

### Setup
To use **StaggeredTextGridView** in your projects, simply add the library as a dependency to your build.

##### Maven
```
<dependency>
  <groupId>com.riontech.staggeredtextgridview</groupId>
  <artifactId>staggeredtextgridview</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
##### Gradle
```
dependencies {
  compile 'com.riontech.staggeredtextgridview:staggeredtextgridview:1.0.1'
}
```

##### Updated
1.Added some custom attributes listed below
* `horizontalSpace` - dimension (add space between coloumn)
* `verticalSpace`   - dimension (add space between row)
* `isFitToScreen`   - boolean (if true, last row set to fit into screen)

2.Resolved issue of Word not completely visible

Alternatively you can directly import the /library project into your Android Studio project and add it as a dependency in your build.gradle.

The library is currently configured to be built via Gradle only. It has the following dependencies:

* Android Gradle plugin 2.1.0 - com.android.tools.build:gradle:2.1.0
* Android Support appcompt-v7 - com.android.support:appcompat-v7:23.1.1
* Compiled SDK Version        - lollipop-23
* Supported Version           - >= 2.1

### Usage
For more detailed code example to use the library, Please refere the `/sample` app.

`StaggeredTextGridView` can be added as a custom view to any layout.

```
<com.riontech.staggeredtextgridview.StaggeredTextGridView
        custom:horizontalSpace="2dp"
        custom:verticalSpace="2dp"
        custom:isFitToScreen="false"
        android:background="@color/white"
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

### TODO
1. Item selector drawable
2. Item click listener

### Licence
```
Copyright (c) 2016 riontech-xten

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
