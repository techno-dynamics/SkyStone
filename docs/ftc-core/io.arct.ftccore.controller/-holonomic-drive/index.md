[ftc-core](../../index.md) / [io.arct.ftccore.controller](../index.md) / [HolonomicDrive](./index.md)

# HolonomicDrive

`class HolonomicDrive : `[`DriveController`](../-drive-controller/index.md)

A drive controller for a robot using holonomic drive

### Parameters

`robot` - The robot this controller is controlling

`motors` - A [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of motors in the holonomic drive array

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HolonomicDrive(robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)`, motors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>A drive controller for a robot using holonomic drive |

### Functions

| Name | Summary |
|---|---|
| [move](move.md) | `fun move(direction: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [rotate](rotate.md) | `fun rotate(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [use](use.md) | `fun use(gamepad: `[`Gamepad`](../../io.arct.ftccore.gamepad/-gamepad/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [distanceConstant](distance-constant.md) | `var distanceConstant: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>The ratio between a holonomic drive unit and a centimeter |
| [rotationConstant](rotation-constant.md) | `var rotationConstant: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>The ratio between a golonomic drive rotation unit and a degree |
