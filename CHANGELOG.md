# Changelog

## v1.0.0 — First public release (2026-11-17)

- First publicly distributed version, based on the user-tested v2 hook implementation
- Reveal Mi Remote 7.1.3G Lighting / Bulb category (ID 14) after application filtering
- Scope hooks to Mi Remote only; no whole-device region spoofing
- Reported functioning on a custom ROM and with Vector
- Signed APK is identical to the tested v2 binary; public release uses a v1.0.0 filename

## Pre-release development builds (not GitHub Releases)

- **v2 internal build:** Inject Lighting category into the final filtered list, where testing succeeded
- **v1 internal build:** Hook the support check; category stayed hidden on the tested screen
