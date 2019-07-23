[ftc-core](../../index.md) / [io.arct.ftccore.controller](../index.md) / [MotorController](./index.md)

# MotorController

`class MotorController : `[`Controller`](../-controller/index.md)

A controller that controls an array of motors

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MotorController(robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)`, motors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>`MotorController(robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)`, motors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Motor`](../../io.arct.ftccore.device/-motor/index.md)`>)`<br>Initialize a [MotorController](./index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [robot](../-controller/robot.md) | `var robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)<br>The robot this controller controls |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(identifier: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun add(motor: `[`Motor`](../../io.arct.ftccore.device/-motor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add a motor |
| [clear](clear.md) | `fun clear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove all motors |
| [motors](motors.md) | `fun motors(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Motor`](../../io.arct.ftccore.device/-motor/index.md)`>`<br>A [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of all of the motors |
| [move](move.md) | `fun move(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, wait: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Move all the motors a specific distance |
| [power](power.md) | `fun power(power: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the power of all the motors |
| [remove](remove.md) | `fun remove(motor: `[`Motor`](../../io.arct.ftccore.device/-motor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove a motor |
