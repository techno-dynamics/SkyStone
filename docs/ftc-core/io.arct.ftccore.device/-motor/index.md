[ftc-core](../../index.md) / [io.arct.ftccore.device](../index.md) / [Motor](./index.md)

# Motor

`class Motor : `[`Device`](../-device/index.md)

A hardware DC Motor

### Types

| Name | Summary |
|---|---|
| [ZeroPowerBehavior](-zero-power-behavior/index.md) | `enum class ZeroPowerBehavior`<br>The behavior of a motor when a power of zero is applied |

### Properties

| Name | Summary |
|---|---|
| [busy](busy.md) | `val busy: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Is the motor currently busy |
| [position](position.md) | `val position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The current position of the motor |
| [zeroPower](zero-power.md) | `var zeroPower: `[`Motor.ZeroPowerBehavior`](-zero-power-behavior/index.md)<br>The [ZeroPowerBehavior](-zero-power-behavior/index.md) of the motor when a power of zero is applied |

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
| [move](move.md) | `fun move(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, position: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, wait: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Move the motor to a position |
| [power](power.md) | `fun power(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set a particular power value to the motor |
| [stop](stop.md) | `fun stop(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Stop the motor |

### Inherited Functions

| Name | Summary |
|---|---|
| [close](../-device/close.md) | `fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Close the device |

### Companion Object Properties

| Name | Summary |
|---|---|
| [distanceConstant](distance-constant.md) | `var distanceConstant: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>The ratio between a motor encoder step and a degree |
