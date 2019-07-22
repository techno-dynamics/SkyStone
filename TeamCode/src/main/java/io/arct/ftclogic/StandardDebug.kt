package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.arct.ftccore.OperationMode
import io.arct.ftccore.device.Motor

@TeleOp
class StandardDebug : OperationMode() {
    private var motors: Array<Motor>? = null

    override fun init() {
        motors = arrayOf(robot.map.motor("m1"), robot.map.motor("m2"), robot.map.motor("m3"), robot.map.motor("m4"))

        log.autoClear = true

        log
                .add("Ready!")
                .update()
    }

    override fun loop() {
        val gamepad = robot.gamepad[0]

        motors!![0].power(gamepad.left.x.toDouble())
        motors!![1].power(gamepad.right.x.toDouble())
        motors!![2].power(gamepad.left.y.toDouble())
        motors!![3].power(gamepad.right.y.toDouble())
    }
}
