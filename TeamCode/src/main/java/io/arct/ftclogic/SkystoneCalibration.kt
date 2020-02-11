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
    private var currentpos = 0

    init {
        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun run() {
        var rectNum = 0

        var x = 640
        var y = 500
        var w = 150
        var h = 220

        while (active) {
            log.add("# $rectNum -> ($x, $y, $w, $h)").add("current -> $currentpos").update()

            val pressed = gamepad.a || gamepad.b || gamepad.dpad.up || gamepad.dpad.down || gamepad.dpad.left || gamepad.dpad.right || gamepad.rb || gamepad.lb || gamepad.rt > 0.5 || gamepad.lt > 0.5

            if (!pressed)
                alreadyPessed = false

            if (pressed && alreadyPessed)
                continue

            if (pressed)
                alreadyPessed = true

            StoneDetector.stones[rectNum] = Rectangle(x, y, w, h)

            if (gamepad.a)
                currentpos = stone.state.value

            if (gamepad.b) {
                rectNum = (rectNum + 1) % 3
                val s = StoneDetector.stones[rectNum]
                x = s.x
                y = s.y
                w = s.width
                h = s.height

            }

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