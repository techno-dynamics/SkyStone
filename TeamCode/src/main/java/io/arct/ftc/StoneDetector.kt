package io.arct.ftc

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Environment
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia
import io.arct.ftclib.robot.FtcRobot
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import java.io.FileOutputStream

class StoneDetector(private val robot: FtcRobot, key: String) {
    companion object {
        val stones = listOf(
            Rectangle(750, 100, 100, 200),
            Rectangle(750, 300, 100, 200),
            Rectangle(750, 500, 100, 200)
        )
    }

    private val vuforia: VuforiaLocalizer

    init {
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true)

        val cameraMonitorViewId: Int = robot.__sdk__opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            robot.__sdk__opMode.hardwareMap.appContext.packageName
        )

        val parameters = VuforiaLocalizer.Parameters(cameraMonitorViewId)

        parameters.vuforiaLicenseKey = key
        parameters.cameraDirection = VuforiaTargeting.Preferences.cameraDirection

        vuforia = ClassFactory.getInstance().createVuforia(parameters)

        vuforia.enableConvertFrameToBitmap()
        vuforia.frameQueueCapacity = 5
    }

    val state: StoneState get() {
        val frame = vuforia.frameQueue.take()

        val image = frame.getImage(0)
        val bitmap = vuforia.convertFrameToBitmap(frame)!!

        frame.close()

        val values = stones.mapIndexed { index, it ->
            val bmp = Bitmap.createBitmap(bitmap, it.x, it.y, it.width, it.height)
            val avg = bmp.average

            save(bmp, avg, index)

            avg
        }

        return state(values[0], values[1], values[2])
    }

    private fun save(bitmap: Bitmap, average: Int, label: Int) {
        val telemetry: Telemetry =  robot.__sdk__opMode.telemetry

        telemetry.addLine("Image $label (${bitmap.width}x${bitmap.height}px) -> RGB(${Color.red(average)}, ${Color.green(average)}, ${Color.blue(average)})")
        telemetry.update()

        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            100,
            FileOutputStream(Environment.getExternalStorageDirectory().path + "/FTC/scan_$label")
        )
    }

    private fun state(a: Int, b: Int, c: Int) = when {
        a < b && a < c -> StoneState.One
        b < a && b < c -> StoneState.Two
        c < a && c < b -> StoneState.Three
        else -> throw Exception("Could not determine stone state!")
    }
}

val Bitmap.average: Int get() {
    val total = this.width * this.height

    val pixels = IntArray(this.width * this.height)
    this.getPixels(pixels, 0, width, 0, 0, width, height)

    return pixels.map {
        Triple(Color.red(it), Color.blue(it), Color.green(it))
    }.reduce { acc, triple ->
        Triple(acc.first + triple.first, acc.second + triple.second, acc.third + triple.third)
    }.run {
        Color.rgb(
            this.first / total,
            this.second / total,
            this.third / total
        )
    }
}