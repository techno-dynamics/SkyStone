package io.arct.ftclogic

import io.arct.ftc.Rectangle
import io.arct.ftc.StoneDetector
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode

@OperationMode.Bind(OperationMode.Type.Operated, group = "SkyStone Calibration")
class SkystoneCalibration : LinearOperationMode() {

    private val stone = StoneDetector(robot, BuildConfig.VUFORIA_KEY)
    private val gamepad = robot.gamepad[0]
    private var alreadyPessed = false

    init {
        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun run() {
        var rectNum = 0

        var x = 0
        var y = 0
        var w = 0
        var h = 0

        while (active) {
            val pressed = gamepad.a || gamepad.b || gamepad.dpad.up || gamepad.dpad.down || gamepad.dpad.left || gamepad.dpad.right || gamepad.rb || gamepad.lb || gamepad.rt > 0.5 || gamepad.lt > 0.5

            if (!pressed)
                alreadyPessed = false

            if (pressed && alreadyPessed)
                continue

            StoneDetector.stones[rectNum] = Rectangle(x, y, w, h)

            if (gamepad.a)
                stone.state

            if (gamepad.b)
                rectNum = (rectNum + 1) % 3

            if (gamepad.dpad.up)
                y += 10

            if (gamepad.dpad.down)
                y -= 10

            if (gamepad.dpad.left)
                x -= 10

            if (gamepad.dpad.right)
                x += 10

            if (gamepad.rb)
                w += 10

            if (gamepad.lb)
                w -= 10

            if (gamepad.rt > 0.5)
                h -= 10

            if (gamepad.lt > 0.5)
                h += 10
        }
    }
}