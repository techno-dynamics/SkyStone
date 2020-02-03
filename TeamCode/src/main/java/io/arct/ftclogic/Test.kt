package io.arct.ftclogic

import io.arct.ftc.Vuforia
import io.arct.ftc.Vuforia.Constants.mmPerInch
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.robotcore.external.matrices.VectorF
import org.firstinspires.ftc.robotcore.external.navigation.*

@OperationMode.Bind(OperationMode.Type.Autonomous, group = "Calibration")
class Test : LinearOperationMode() {
    private var lastLocation: OpenGLMatrix? = null
    private var targetVisible: Boolean = false

    override fun run() {
        val tracker = Vuforia.build(BuildConfig.VUFORIA_KEY, robot)

        tracker.targets.activate()

        while (active && !stopRequested) {
            targetVisible = false

            for (trackable in tracker.trackables) {
                if ((trackable.listener as VuforiaTrackableDefaultListener).isVisible) {
                    log.add("Visible Target: ${trackable.name}")
                    targetVisible = true

                    val robotLocationTransform = (trackable.listener as VuforiaTrackableDefaultListener).updatedRobotLocation

                    if (robotLocationTransform != null)
                        lastLocation = robotLocationTransform

                    break
                }
            }

            if (targetVisible) {
                val translation: VectorF = lastLocation!!.translation

                val x = translation[0] / mmPerInch
                val y = translation[1] / mmPerInch
                val z = translation[2] / mmPerInch

                log.add("Pos (in) {X, Y, Z} = $x, $y, $z")

                val rotation = Orientation.getOrientation(lastLocation, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES)

                log.add("Rot (deg) {Roll, Pitch, Heading} = ${rotation.firstAngle}, ${rotation.secondAngle}, ${rotation.thirdAngle}")
            } else {
                log.add("Visible Target: none")
            }

            log.update()
        }

        tracker.targets.deactivate()
    }
}