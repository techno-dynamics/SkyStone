[ftc-core](../../index.md) / [io.arct.ftccore](../index.md) / [LinearOperationMode](./index.md)

# LinearOperationMode

`abstract class LinearOperationMode : LinearOpMode`

Represents an linear operation mode.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LinearOperationMode()`<br>Represents an linear operation mode. |

### Properties

| Name | Summary |
|---|---|
| [log](log.md) | `val log: `[`Telemetry`](../../io.arct.ftccore.telemetry/-telemetry/index.md)<br>The [Telemetry](../../io.arct.ftccore.telemetry/-telemetry/index.md) instance of this operation mode |
| [robot](robot.md) | `val robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)<br>The [Robot](../../io.arct.ftccore.robot/-robot/index.md) associated with this operation mode |

### Functions

| Name | Summary |
|---|---|
| [_init](_init.md) | `abstract fun _init(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Code to execute when the operation mode is initiated |
| [run](run.md) | `abstract fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Code to execute when the operation mode is run |
| [runOpMode](run-op-mode.md) | `open fun runOpMode(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
