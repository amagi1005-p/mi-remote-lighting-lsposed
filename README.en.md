# Mi Remote Lighting / Bulb Category Enabler — LSPosed Module

**Language / 言語: [日本語](README.md) | [English](README.en.md)**

**Missing the Lighting or Bulb category in Xiaomi Mi Remote?** This unofficial LSPosed module makes the built-in **Lighting / Bulb infrared remote category (device type ID 14)** visible in **Mi Remote 7.1.3G**, so compatible room-light remotes can be selected. The category and infrared control have been demonstrated on a custom ROM.

**Important:** This is a Mi Remote category-visibility fix, **not** a new infrared-code database, universal region unlock, or infrared hardware driver. A working IR transmitter, supported remote profiles, LSPosed, and Mi Remote 7.1.3G are required.

An LSPosed compatibility module that reveals the built-in **Lighting / Bulb** infrared remote category (**device type ID 14**) in **Mi Remote 7.1.3G**.

## Compatibility

- **First public release: v1.0.0** (based on the user-tested v2 implementation)
- Confirmed to reveal the Lighting/Bulb category in Mi Remote's Add Remote screen
- Infrared room-light control has been demonstrated
- **Also works with Vector**; individual configurations and versions have not been independently verified
- Hooks **Mi Remote 7.1.3G** (`com.duokan.phone.remotecontroller`) only

**Note:** This module does not modify Mi Home or device-wide region settings. Compatibility with other Mi Remote versions, custom ROMs, and appliance brands is not guaranteed.

## Installation

1. Install **Mi Remote 7.1.3G** and ensure **LSPosed** is working.
2. Download and install the signed APK from the [v1.0.0 release page](https://github.com/amagi1005-p/mi-remote-lighting-lsposed/releases/tag/v1.0.0).
3. Enable **IR Light LSPosed** in LSPosed and select only **Mi Remote** (`com.duokan.phone.remotecontroller`) as the scope.
4. Force-stop and reopen Mi Remote (reboot if your setup requires it).
5. Open **Add Remote → Lighting / Bulb**, select your appliance brand and try the available remote profiles.

If the Lighting category is missing, search your LSPosed logs for `[IRLightLSPosed]`.

## How it works

The module hooks Mi Remote's `DKDeviceType` support checks and category-list creation in `AddDeviceActivityV52` / `MiUIAddDeviceActivityV52`. After the application's category filtering, a fallback Lighting entry (ID 14) is inserted if none is present.

**The module does not supply new infrared codes.** Control depends on Mi Remote's locally stored or online IR profiles and your custom ROM's working infrared transmitter / IR HAL.

## Building

Use Android Studio with Android SDK 35, JDK 17+, and a Gradle version compatible with Android Gradle Plugin 8.7.3. Build with `:app:assembleDebug`.

Xposed API 82 is a `compileOnly` dependency and is not bundled into the APK. The signed APK attached to the release was built separately and is not necessarily binary-identical to the Gradle debug output.

## Disclaimer and license

This is an unofficial independent compatibility module. No proprietary Mi Remote APK, firmware, or third-party infrared-code database is distributed. Xiaomi, Mi Remote, and TOSHIBA are trademarks of their respective owners.

License: [MIT](LICENSE)
