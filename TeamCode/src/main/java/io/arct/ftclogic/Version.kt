package io.arct.ftclogic

import io.arct.ftclib.eventloop.OperationMode

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Version 1.1.0", group = "Misc")
class Version : OperationMode() {
    override fun init() {}
    override fun loop() {}
}