[ftc-core](../../index.md) / [io.arct.ftccore.controller](../index.md) / [DriveController](./index.md)

# DriveController

`abstract class DriveController : `[`Controller`](../-controller/index.md)

A controller that controls robot movement

### Parameters

`robot` - The robot this controller controls

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DriveController(robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)`)`<br>A controller that controls robot movement |

### Inherited Properties

| Name | Summary |
|---|---|
| [robot](../-controller/robot.md) | `var robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)<br>The robot this controller controls |

### Functions

| Name | Summary |
|---|---|
| [move](move.md) | `abstract fun move(direction: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Programmatically move the robot |
| [rotate](rotate.md) | `abstract fun rotate(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Programmatically rotate the robot |
| [use](use.md) | `abstract fun use(gamepad: `[`Gamepad`](../../io.arct.ftccore.gamepad/-gamepad/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [HolonomicDrive](../-holonomic-drive/index.md) | `class HolonomicDrive : `[`DriveController`](./index.md)<br>A drive controller for a robot using holonomic drive |
