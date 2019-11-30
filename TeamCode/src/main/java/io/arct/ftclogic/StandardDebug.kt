package io.arct.ftclogic

import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motor.Motor

@OperationMode.Bind(OperationMode.Type.Operated, name = "Standard Debug", group = "Debug")
class StandardDebug : OperationMode() {
    private var motors: Array<Motor>? = null

    override fun init() {
        motors = arrayOf(robot.map("m1"), robot.map("m2"), robot.map("m3"), robot.map("m4"))

        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun loop() {
        val gamepad = robot.gamepad[0]

        motors!![0].power = gamepad.left.x
        motors!![1].power = gamepad.right.x
        motors!![2].power = gamepad.left.y
        motors!![3].power = gamepad.right.y
    }
}
