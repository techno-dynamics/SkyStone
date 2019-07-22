[ftc-core](../../index.md) / [io.arct.ftccore.robot](../index.md) / [HardwareMap](./index.md)

# HardwareMap

`class HardwareMap`

A map of hardware devices

**See Also**

[Robot](../-robot/index.md)

### Functions

| Name | Summary |
|---|---|
| [continuousServo](continuous-servo.md) | `fun continuousServo(identifier: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ContinuousServo`](../../io.arct.ftccore.device/-continuous-servo/index.md)<br>Get a [ContinuousServo](../../io.arct.ftccore.device/-continuous-servo/index.md) |
| [get](get.md) | `operator fun <T> get(type: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`T`](get.md#T)`>, identifier: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](get.md#T)<br>Get a hardware device |
| [motor](motor.md) | `fun motor(identifier: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Motor`](../../io.arct.ftccore.device/-motor/index.md)<br>Get a [Motor](../../io.arct.ftccore.device/-motor/index.md) |
| [servo](servo.md) | `fun servo(identifier: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Servo`](../../io.arct.ftccore.device/-servo/index.md)<br>Get a [Servo](../../io.arct.ftccore.device/-servo/index.md) |
