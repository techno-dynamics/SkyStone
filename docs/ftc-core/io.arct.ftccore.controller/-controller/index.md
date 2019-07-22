[ftc-core](../../index.md) / [io.arct.ftccore.controller](../index.md) / [Controller](./index.md)

# Controller

`abstract class Controller`

A controller responsible for controlling a part of a robot

### Parameters

`robot` - The robot this controller controls

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Controller(robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)`)`<br>A controller responsible for controlling a part of a robot |

### Properties

| Name | Summary |
|---|---|
| [robot](robot.md) | `var robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)<br>The robot this controller controls |

### Functions

| Name | Summary |
|---|---|
| [use](use.md) | `abstract fun use(gamepad: `[`Gamepad`](../../io.arct.ftccore.gamepad/-gamepad/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Code to execute on every iteration (OperationMode only) |

### Inheritors

| Name | Summary |
|---|---|
| [DriveController](../-drive-controller/index.md) | `abstract class DriveController : `[`Controller`](./index.md)<br>A controller that controls robot movement |
| [MotorController](../-motor-controller/index.md) | `class MotorController : `[`Controller`](./index.md)<br>A controller that controls an array of motors |
