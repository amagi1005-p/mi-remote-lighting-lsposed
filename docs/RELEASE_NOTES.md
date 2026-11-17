# v1.0.0 — First Public Release / 初回公開版

[日本語](#日本語) | [English](#english)

## 日本語

Mi リモート **7.1.3G** で通常は表示されない **「電球／照明」カテゴリ（ID 14）** を追加表示するLSPosedモジュールの初回公開版です。

- カスタムROM環境で照明カテゴリの表示を確認
- 室内照明の赤外線操作実績あり
- **Vectorでも使用可能**（個別構成の検証は未実施）
- LSPosedの適用先は `com.duokan.phone.remotecontroller` のみ
- v2として試験された実装を、初回正式版 `v1.0.0` として公開しています。署名済みAPKのバイナリは変更していません。

**[日本語README](https://github.com/amagi1005-p/mi-remote-lighting-lsposed/blob/main/README.md)**

## English

First public release of the LSPosed module that reveals the hidden **Lighting / Bulb category (ID 14)** in **Mi Remote 7.1.3G**.

- Lighting category confirmed visible on a custom ROM
- Infrared room-light control has been demonstrated
- **Also works with Vector** (individual device/build combinations not independently tested)
- LSPosed scope: `com.duokan.phone.remotecontroller` only
- The signed APK is the same tested v2 binary, published under the first public release tag `v1.0.0`.

**[English README](https://github.com/amagi1005-p/mi-remote-lighting-lsposed/blob/main/README.en.md)**

### Notes / 注意事項

The module reveals a built-in category; it does not include additional infrared codes or repair an Android IR HAL. Compatibility with all models and versions is not guaranteed.

このモジュールは既存カテゴリを表示させるもので、赤外線コードやIR HALそのものを追加・修復するものではありません。
