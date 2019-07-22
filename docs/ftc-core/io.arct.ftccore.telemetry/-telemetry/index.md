[ftc-core](../../index.md) / [io.arct.ftccore.telemetry](../index.md) / [Telemetry](./index.md)

# Telemetry

`class Telemetry`

Sends information to the Driver Station

**See Also**

[io.arct.ftccore.robot.Robot](../../io.arct.ftccore.robot/-robot/index.md)

### Properties

| Name | Summary |
|---|---|
| [autoClear](auto-clear.md) | `var autoClear: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Automatic log clearing on [Telemetry.update](update.md) |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(line: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Telemetry`](./index.md)<br>Add a line to telemetry`fun add(data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Telemetry`](./index.md)<br>Add lines to telemetry |
| [update](update.md) | `fun update(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Update/Send queued telemetry data |
