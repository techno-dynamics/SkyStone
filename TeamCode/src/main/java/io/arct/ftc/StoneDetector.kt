package io.arct.ftc

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Environment
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia
import io.arct.ftclib.robot.FtcRobot
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class StoneDetector(
        robot: FtcRobot,
        key: String,
        cameraDirection: VuforiaLocalizer.CameraDirection = VuforiaLocalizer.CameraDirection.FRONT,
        val saveResults: Boolean = false
) {
    companion object {
        val stones = mutableListOf(
            Rectangle(640, 500, 150, 220),
            Rectangle(640, 250, 150, 220),
            Rectangle(640, 0, 150, 220)
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
        parameters.cameraDirection = cameraDirection

        vuforia = ClassFactory.getInstance().createVuforia(parameters)

        vuforia.enableConvertFrameToBitmap()
        vuforia.frameQueueCapacity = 5
    }

    val state: StoneState get() {
        val frame = vuforia.frameQueue.take()
        val bitmap = vuforia.convertFrameToBitmap(frame)!!
        frame.close()

        if (saveResults)
            save(bitmap, "full")

        val values = stones.mapIndexed { index, it ->
            val bmp = Bitmap.createBitmap(bitmap, it.x, it.y, it.width, it.height)

            if (saveResults)
                save(bmp, index)

            bmp.average
        }

        return state(values[0], values[1], values[2])
    }

    private fun save(bitmap: Bitmap, label: Any) {
        try {
            val stream = FileOutputStream(File(Environment.getExternalStorageDirectory(), "ftc_scan_$label.jpg"))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun state(a: Int, b: Int, c: Int) = when {
        a < b && a < c -> StoneState.One
        b < a && b < c -> StoneState.Two
        c < a && c < b -> StoneState.Three
        else -> StoneState.Unknown
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