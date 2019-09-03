package com.sun.moviedb.view.actor

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene

/**
 * Created by nguyenxuanhoi on 2019-08-29.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
interface MotionLayoutListener : MotionLayout.TransitionListener{
    override fun allowsTransition(p0: MotionScene.Transition?): Boolean =true
    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

    }
}