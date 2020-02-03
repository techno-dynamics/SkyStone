package io.arct.ftc

import io.arct.ftc.Vuforia.Constants.bridgeRotY
import io.arct.ftc.Vuforia.Constants.bridgeRotZ
import io.arct.ftc.Vuforia.Constants.bridgeX
import io.arct.ftc.Vuforia.Constants.bridgeY
import io.arct.ftc.Vuforia.Constants.bridgeZ
import io.arct.ftc.Vuforia.Constants.halfField
import io.arct.ftc.Vuforia.Constants.mmPerInch
import io.arct.ftc.Vuforia.Constants.mmTargetHeight
import io.arct.ftc.Vuforia.Constants.quadField
import io.arct.ftc.Vuforia.Preferences.cameraDirection
import io.arct.ftc.Vuforia.Preferences.cameraForwardDisplacement
import io.arct.ftc.Vuforia.Preferences.cameraLeftDisplacement
import io.arct.ftc.Vuforia.Preferences.cameraVerticalDisplacement
import io.arct.ftc.Vuforia.Preferences.phoneIsPortrait
import io.arct.ftc.Vuforia.Preferences.phoneXRotate
import io.arct.ftc.Vuforia.Preferences.phoneYRotate
import io.arct.ftc.Vuforia.Preferences.phoneZRotate
import io.arct.ftclib.robot.FtcRobot
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.robotcore.external.navigation.*
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection
import java.util.*

object Vuforia {
    object Preferences {
        val cameraDirection = CameraDirection.FRONT

        const val phoneIsPortrait = true

        var phoneXRotate = 0f
        var phoneYRotate = 0f
        var phoneZRotate = 0f

        const val cameraForwardDisplacement = 3.0f * mmPerInch // Camera is 3 Inches in front of robot center
        const val cameraVerticalDisplacement = 7.5f * mmPerInch // Camera is 7.5 Inches above ground
        const val cameraLeftDisplacement = 6.0f // Camera is 6 inches left of the the robot's center line
    }

    object Constants {
        const val mmPerInch = 25.4f
        const val mmTargetHeight = 10 * mmPerInch // The height of the center of the target image above the floor

        const val stoneZ = 2.00f * mmPerInch

        const val bridgeZ = 6.42f * mmPerInch
        const val bridgeY = 23 * mmPerInch
        const val bridgeX = 5.18f * mmPerInch
        const val bridgeRotY = 59f // Units are degrees

        const val bridgeRotZ = 180f

        const val halfField = 72 * mmPerInch
        const val quadField = 36 * mmPerInch
    }

    // ---------------------------------------------------------------------------------------------

    fun build(key: String, robot: FtcRobot): SkystoneTracker {
        val cameraMonitorViewId: Int = robot.__sdk__opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            robot.__sdk__opMode.hardwareMap.appContext.packageName
        )

        val parameters = VuforiaLocalizer.Parameters(cameraMonitorViewId)

        parameters.vuforiaLicenseKey = key
        parameters.cameraDirection = cameraDirection

        val vuforia: VuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters)
        val trackables = buildTrackables(vuforia)

        phoneYRotate = if (cameraDirection == CameraDirection.BACK) -90f else 90f

        if (phoneIsPortrait)
            phoneXRotate = 90f

        val robotFromCamera = OpenGLMatrix
            .translation(cameraForwardDisplacement, cameraLeftDisplacement, cameraVerticalDisplacement)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.YZX, AngleUnit.DEGREES, phoneYRotate, phoneZRotate, phoneXRotate))

        for (trackable in trackables.second)
            (trackable.listener as VuforiaTrackableDefaultListener).setPhoneInformation(robotFromCamera, parameters.cameraDirection)

        return SkystoneTracker(vuforia, trackables.second, trackables.first)
    }

    fun buildTrackables(vuforia: VuforiaLocalizer): Pair<VuforiaTrackables, MutableList<VuforiaTrackable>> {
        val targets = vuforia.loadTrackablesFromAsset("Skystone")

        val names: List<String> = listOf(
            "Stone Target",
            "Blue Rear Bridge", "Red Rear Bridge",
            "Red Front Bridge", "Blue Front Bridge",
            "Red Perimeter 1", "Red Perimeter 2",
            "Front Perimeter 1", "Front Perimeter 2",
            "Blue Perimeter 1", "Blue Perimeter 2",
            "Rear Perimeter 1", "Rear Perimeter 2"
        )

        targets.forEachIndexed { index, trackable ->
            trackable.name = names[index]
        }

        val trackables: MutableList<VuforiaTrackable> = ArrayList(targets)

        // --------------------------------------------------------

        targets[0].location = OpenGLMatrix
            .translation(0f, 0f, Constants.stoneZ)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, -90f))

        targets[4].location = OpenGLMatrix
            .translation(-bridgeX, bridgeY, bridgeZ)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0f, bridgeRotY, bridgeRotZ))

        targets[1].location = OpenGLMatrix
            .translation(-bridgeX, bridgeY, bridgeZ)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0f, -bridgeRotY, bridgeRotZ))

        targets[3].location = OpenGLMatrix
            .translation(-bridgeX, -bridgeY, bridgeZ)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0f, -bridgeRotY, 0f))

        targets[2].location = OpenGLMatrix
            .translation(bridgeX, -bridgeY, bridgeZ)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0f, bridgeRotY, 0f))

        targets[5].location = OpenGLMatrix
            .translation(quadField, -halfField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 180f))

        targets[6].location = OpenGLMatrix
            .translation(-quadField, -halfField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 180f))

        targets[7].location = OpenGLMatrix
            .translation(-halfField, -quadField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 90f))

        targets[8].location = OpenGLMatrix
            .translation(-halfField, quadField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 90f))

        targets[9].location = OpenGLMatrix
            .translation(-quadField, halfField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 0f))

        targets[10].location = OpenGLMatrix
            .translation(quadField, halfField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, 0f))

        targets[11].location = OpenGLMatrix
            .translation(halfField, quadField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, -90f))

        targets[12].location = OpenGLMatrix
            .translation(halfField, -quadField, mmTargetHeight)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 90f, 0f, -90f))

        return targets to trackables
    }
}