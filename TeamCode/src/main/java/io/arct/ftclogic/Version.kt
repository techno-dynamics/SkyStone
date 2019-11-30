package io.arct.ftclogic

import io.arct.ftclib.eventloop.OperationMode

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Version 0.9.1", group = "Misc")
class Version : OperationMode() {
    override fun init() {}
    override fun loop() {}
}