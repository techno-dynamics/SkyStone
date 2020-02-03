package io.arct.ftc

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables

data class SkystoneTracker(
    val vuforia: VuforiaLocalizer,
    val trackables: List<VuforiaTrackable>,
    val targets: VuforiaTrackables
)