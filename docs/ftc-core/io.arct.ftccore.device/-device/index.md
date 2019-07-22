[ftc-core](../../index.md) / [io.arct.ftccore.device](../index.md) / [Device](./index.md)

# Device

`open class Device`

Represents a hardware device

### Properties

| Name | Summary |
|---|---|
| [connectionInfo](connection-info.md) | `val connectionInfo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!`<br>The connection information of the device |
| [manufacturer](manufacturer.md) | `val manufacturer: Manufacturer!`<br>The manufacturer of the device |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!`<br>The name of the device |
| [version](version.md) | `val version: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The devices version number |

### Functions

| Name | Summary |
|---|---|
| [close](close.md) | `fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Close the device |

### Inheritors

| Name | Summary |
|---|---|
| [ContinuousServo](../-continuous-servo/index.md) | `class ContinuousServo : `[`Device`](./index.md)<br>A hardware continuous rotation servo |
| [Motor](../-motor/index.md) | `class Motor : `[`Device`](./index.md)<br>A hardware DC Motor |
| [Servo](../-servo/index.md) | `class Servo : `[`Device`](./index.md)<br>A hardware servo |
