package io.arct.ftccore.device

import com.qualcomm.robotcore.hardware.HardwareDevice

/**
 * Represents a hardware device
 *
 * @property connectionInfo The connection information of the device
 * @property name The name of the device
 * @property manufacturer The manufacturer of the device
 * @property version The devices version number
 */
open class Device internal constructor(private val device: HardwareDevice) {
    val connectionInfo = device.connectionInfo
    val name = device.deviceName
    val manufacturer = device.manufacturer
    val version = device.version

    /**
     * Close the device
     */
    fun close() {
        device.close()
    }
}

