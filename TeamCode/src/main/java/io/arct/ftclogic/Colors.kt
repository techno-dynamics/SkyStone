package io.arct.ftclogic


import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.sensor.ColorSensor

@OperationMode.Bind(OperationMode.Type.Operated)
class Colors : OperationMode() {
    private val color: ColorSensor by robot.map

    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loop() {
        log.add("r: ${color.red} g: ${color.green} b: ${color.blue}").add("alpha: ${color.alpha} argb: ${color.argb}")
    }
}