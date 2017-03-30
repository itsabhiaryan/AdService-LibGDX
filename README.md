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

# StartApp Integration

- Get appId after signup to StartApp and create Publishing Campaigns.
- Download SDK(basically StartAppInApp-3.5.6.jar)
- Add .jar file into your IDE and integrate/compile

*Activities and Services required in AndroidManifest.xml file :*

```javascript
<activity android:name="com.startapp.android.publish.ads.list3d.List3DActivity"
          android:theme="@android:style/Theme" />

<activity android:name="com.startapp.android.publish.adsCommon.activities.OverlayActivity"
          android:theme="@android:style/Theme.Translucent"
          android:configChanges="orientation|keyboardHidden|screenSize" />

<activity android:name="com.startapp.android.publish.adsCommon.activities.FullScreenActivity"
          android:theme="@android:style/Theme"
          android:configChanges="orientation|keyboardHidden|screenSize" />

<service android:name="com.startapp.android.publish.common.metaData.PeriodicMetaDataService" />
<service android:name="com.startapp.android.publish.common.metaData.InfoEventService" />
<receiver android:name="com.startapp.android.publish.common.metaData.BootCompleteListener" >
        <intent-filter>
              <action android:name="android.intent.action.BOOT_COMPLETED" />
        </intent-filter>
</receiver>
```

*Addon required Permission  :*

```javascript
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.BLUETOOTH" />
```


