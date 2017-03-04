# AdMob Integration

### configure dependencies using Gradle

*android:*

```javascript
dependencies {
    compile 'com.google.android.gms:play-services-ads:10.0.1'
}
```

### configuration

```javascript
<uses-permission android:name="android.permission.INTERNET"/>

minSdkVersion >= 9
```


# RevMob Integration

- Get Media Id after signup to RevMob
- Download SDK(basically revmob.jar)
- Add .jar file into your IDE and integrate/compile
- Add com.revmob.FullscreenActivity into Manifest file for Interstitial or Video Ad

```javascript
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

# Chartboost Integration

ChartBoost does not support banner ads. You can use Static Interstitial and Video Interstitial Ad.

- Get appId and appSignature after signup to Chartboost and create Publishing Campaigns.
- Download SDK(basically chartboost.jar)
- Add .jar file into your IDE and integrate/compile
- Add com.chartboost.sdk.CBImpressionActivity into Manifest file

```javascript
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
```


