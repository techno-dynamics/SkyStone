[ftc-core](../../index.md) / [io.arct.ftccore](../index.md) / [OperationMode](./index.md)

# OperationMode

`abstract class OperationMode : OpMode`

Represents an operation mode.

An operation mode is a program that can be executed on the robot.

Tag a subclass of OperationMode with either [com.qualcomm.robotcore.eventloop.opmode.TeleOp](#) or [com.qualcomm.robotcore.eventloop.opmode.Autonomous](#) to specify the category of this operation mode

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `OperationMode()`<br>Represents an operation mode. |

### Properties

| Name | Summary |
|---|---|
| [log](log.md) | `val log: `[`Telemetry`](../../io.arct.ftccore.telemetry/-telemetry/index.md)<br>The [Telemetry](../../io.arct.ftccore.telemetry/-telemetry/index.md) instance of this operation mode |
| [robot](robot.md) | `val robot: `[`Robot`](../../io.arct.ftccore.robot/-robot/index.md)<br>The [Robot](../../io.arct.ftccore.robot/-robot/index.md) associated with this operation mode |
