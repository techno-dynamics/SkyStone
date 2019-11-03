package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import io.arct.ftclib.eventloop.OperationMode

@OperationMode.Bind(OperationMode.Type.AUTONOMOUS, name = "Version 0.9.0")
class Version(sdk: OpMode) : OperationMode(sdk) {
    override fun init() {}
    override fun loop() {}
}