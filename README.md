# Mi Remote Lighting — LSPosedモジュール

**言語 / Language: [日本語](README.md) | [English](README.en.md)**

Mi リモート **7.1.3G** に隠されている赤外線リモコンの **「電球／照明」カテゴリ（機器タイプID: 14）** を表示するLSPosedモジュールです。

## 対応状況

- **初回公開版: v1.0.0**（動作確認されたv2実装を公開）
- Mi リモートの機器追加画面に「電球」が表示されたことを確認
- 赤外線による室内照明の操作実績あり
- **Vectorでも利用可能**（環境・バージョン別の検証は未実施）
- 対象は **Mi リモート 7.1.3G**（`com.duokan.phone.remotecontroller`）のみ

※ Mi Home全体や端末の地域設定は変更しません。すべての機器・メーカー・ROMでの動作を保証するものではありません。

## インストール

1. **Mi リモート 7.1.3G** と動作する **LSPosed** を用意します。
2. [v1.0.0リリースページ](https://github.com/amagi1005-p/mi-remote-lighting-lsposed/releases/tag/v1.0.0)からAPKをダウンロードし、インストールします。
3. LSPosedで **IR Light LSPosed** を有効化し、適用先を **Mi リモート**（`com.duokan.phone.remotecontroller`）だけに指定します。
4. Mi リモートを強制停止して開き直します（必要に応じて端末を再起動）。
5. 「リモコンを追加」から **電球／照明** を選択し、メーカーとリモコンパターンを探して動作を確認します。

表示されない場合は、LSPosedログの `[IRLightLSPosed]` を確認してください。

## 動作の仕組み

Mi リモート内部の `DKDeviceType` のサポート判定と、`AddDeviceActivityV52` / `MiUIAddDeviceActivityV52` のカテゴリ一覧生成処理にフックします。アプリの絞り込み後に照明カテゴリ（ID 14）が存在しなければ追加します。

**赤外線コードを新規追加するわけではありません。** 赤外線コードの有無や、カスタムROMのIR送信機能（IR HAL）によって操作可否は異なります。

## 開発・ビルド

Android Studio、Android SDK 35、JDK 17以上、Android Gradle Plugin 8.7.3に対応するGradle環境を使用し、`:app:assembleDebug` でビルドします。

Xposed API 82は `compileOnly` として指定し、APKには同梱しません。GitHubで配布する署名済みAPKは別手順でビルドしたもので、Gradleのdebugビルドと同一バイナリになるとは限りません。

## 注意事項・ライセンス

このリポジトリは非公式の互換性モジュールです。Mi リモートのAPK、独自ファームウェア、第三者の赤外線コードDBは再配布しません。Xiaomi、Mi Remote、TOSHIBAなどの名称はそれぞれの権利者に帰属します。

ライセンス: [MIT](LICENSE)
