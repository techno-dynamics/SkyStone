[ftc-core](../../index.md) / [io.arct.ftccore.device](../index.md) / [ContinuousServo](./index.md)

# ContinuousServo

`class ContinuousServo : `[`Device`](../-device/index.md)

A hardware continuous rotation servo

### Inherited Properties

| Name | Summary |
|---|---|
| [connectionInfo](../-device/connection-info.md) | `val connectionInfo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!`<br>The connection information of the device |
| [manufacturer](../-device/manufacturer.md) | `val manufacturer: Manufacturer!`<br>The manufacturer of the device |
| [name](../-device/name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!`<br>The name of the device |
| [version](../-device/version.md) | `val version: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The devices version number |

### Functions

| Name | Summary |
|---|---|
| [move](move.md) | `fun move(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Move the servo a particular distance |
| [power](power.md) | `fun power(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Apply power to the servo |

### Inherited Functions

| Name | Summary |
|---|---|
| [close](../-device/close.md) | `fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Close the device |
