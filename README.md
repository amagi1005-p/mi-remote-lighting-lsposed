# Mi Remote Lighting — LSPosed Module

Xiaomi **Mi Remote 7.1.3G** に、通常のデバイス一覧では非表示になっている赤外線リモコンの「電球 / Lighting」（機器種別 **ID 14**）を表示するLSPosedモジュールです。

This LSPosed module enables the built-in **lighting / bulb (device type ID 14)** category in Xiaomi **Mi Remote 7.1.3G**.

## Status / 動作状況

- **v2 (0.2.0)**: User-tested: the Lighting/Bulb category appeared. An additional user report says an IR room light could be turned off using the category.
- Scope is limited to Mi Remote (`com.duokan.phone.remotecontroller`). **Does not modify Mi Home**, the whole-device region, or the Android IR HAL.
- **Not verified on every ROM, version, brand, or device**. Other versions of Mi Remote may use different classes/methods. No warranty that Toshiba lighting protocols or every IR database record will work.

## Download / ダウンロード

- The signed v2 APK has not yet been uploaded to this repository. It is available as a conversation attachment to the project author.
- [SHA-256 checksum of the signed v2 APK](releases/SHA256SUMS.txt)

## How to use / 導入

1. Install Mi Remote **7.1.3G** and have a working LSPosed environment.
2. Install the signed v2 module APK obtained from the project author.
3. In LSPosed, enable **IR Light LSPosed** and scope it to **Mi Remote** (`com.duokan.phone.remotecontroller`) only.
4. Force-stop Mi Remote and open it again (reboot if your LSPosed manager requires it).
5. Open the Add Remote menu and look for **電球 / Lighting**.
6. Choose the corresponding brand/model and test it while pointing the device's IR blaster at the appliance.

If the category is absent, look at the LSPosed log for `[IRLightLSPosed]`.

## How it works / 仕組み

The module hooks Mi Remote's `DKDeviceType` support check and the returned category lists in `AddDeviceActivityV52` / `MiUIAddDeviceActivityV52`, after application filtering. If an existing category ID 14 is present, it keeps it. Otherwise, it inserts a fallback Lighting category entry.

This **reveals an existing category; it does not add IR protocols**. Mi Remote's local/online IR codes and the custom ROM's IR transmitter support are separate requirements.

## Build / ビルド

Open the repository in Android Studio with an Android SDK 35 installation, JDK 17+, and Gradle compatible with Android Gradle Plugin 8.7.3, then build `:app:assembleDebug`.

This is a legacy Xposed API 82 module. The dependency is `compileOnly` so Xposed API classes are **not** bundled into the APK. The historical API artifact is resolved from the specified public Maven repository. The provided signed v2 APK was built separately and may not be byte-for-byte identical to a Gradle debug build.

## Scope and disclaimer

Only for compatible devices and Mi Remote 7.1.3G. Requires a functional infrared emitter / HAL. No third-party proprietary Mi Remote APK, firmware, or IR code database is redistributed in this repository. Xiaomi, Mi Remote, and TOSHIBA are trademarks of their respective owners. This is an unofficial independent compatibility module.
