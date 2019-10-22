# Build It Bigger
In this project, you will create an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

# Rubric

### Required Components

* Project contains a Java library for supplying jokes
* Project contains an Android library with an activity that displays jokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task.
* Project contains connected tests to verify that the async task is indeed loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.
* More information on the Rubric/Required components can be found [here](https://classroom.udacity.com/nanodegrees/nd801/parts/5a4a1676-de82-4954-9fb9-ebe492ba6142/modules/19458818-7ecf-49b3-8dc9-28e68510a53f/lessons/a99bbdcb-da71-4b09-bc5d-feb1a51ac334/concepts/42732622150923)

### Required Behavior

* App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

### Optional Components

Once you have a functioning project, consider adding more features to test your Gradle and Android skills. Here are a few suggestions:

* Make the free app variant display interstitial ads between the main activity and the joke-displaying activity.
* Have the app display a loading indicator while the joke is being fetched from the server.
* Write a Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.


## AdMob ID
The app uses the AdMob Id defined for your app in [AdMob UI console](https://apps.admob.com/v2/home).
To run the ads, you have to enter your own Admob ID into `AndroidManifest.xml` file.
Check the procedure of creating one [here](https://developers.google.com/admob/android/quick-start)

```strings.xml
<resources>
    ...
    <string name="admob_app_id">ENTER YOUR ID FROM ADMOB CONSOLE</string> // or else you can use sample ID mentioned in the documentation
</resources>

AndroidManifest.xml
 <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="@string/admob_app_id"/>
```

# Screenshots

| ![Free App-1](https://i.postimg.cc/zBJF0T9d/Screenshot-2019-07-10-at-6-13-08-PM.png) | ![Free App-2](https://i.postimg.cc/8zMdGyGK/Screenshot-2019-07-10-at-6-13-27-PM.png)| ![Paid App](https://i.postimg.cc/ZK1PXGGv/Screenshot-2019-07-10-at-6-13-56-PM.png) |
|:---:|:---:|:---:|
