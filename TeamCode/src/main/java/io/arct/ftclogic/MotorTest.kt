package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.arct.ftccore.OperationMode
import io.arct.ftccore.device.Motor

@TeleOp
class MotorTest : OperationMode() {
    private var motor: Motor? = null

    override fun init() {
        motor = robot.map.motor("motor")

        log.autoClear = true

        log
                .add("Ready!")
                .update()
    }

    override fun loop() {
        if (!robot.gamepad[0].a)
            return

        motor!!.move(0.75, 360.0)

        Thread.sleep(1000)
    }
}
