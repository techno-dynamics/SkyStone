package io.arct.ftclogic

import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motor.Motor
import kotlin.math.roundToInt

@OperationMode.Bind(OperationMode.Type.Operated, name = "Precision Debug", group = "Debug")
class PrecisionDebug : OperationMode() {
    private var motors: Array<Motor>? = null
    private var selected = 0
    private var speed = 1.0

    override fun init() {
        motors = arrayOf(robot.map("m1"), robot.map("m2"), robot.map("m3"), robot.map("m4"))

        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun loop() {
        log.add(listOf("Current Speed: " + (speed * 100).roundToInt() / 100, "Selected Motors: $selected")).update()

        val gamepad = robot.gamepad[0]

        // SELECT A MOTOR
        when {
            gamepad.a -> selected = 0
            gamepad.b -> selected = 1
            gamepad.x -> selected = 2
            gamepad.y -> selected = 3
        }

        // SET MOTOR SPEED
        if (gamepad.rb) {
            speed += 0.1

            try {
                Thread.sleep(100)
            } catch (ignore: Exception) {
            }

        } else if (gamepad.lb) {
            speed -= 0.1

            try {
                Thread.sleep(100)
            } catch (ignore: Exception) {
            }
        }

        // DEACTIVATE OTHER MOTORS
        for (i in motors!!.indices)
            if (i != selected)
                motors!![i].power = 0.0

        // ACTIVATE MOTOR
        when {
            gamepad.lt > 0.5 ->
                motors!![selected].power = -speed
            gamepad.rt > 0.5 ->
                motors!![selected].power = speed
            else ->
                motors!![selected].power = 0.0
        }
    }
}
