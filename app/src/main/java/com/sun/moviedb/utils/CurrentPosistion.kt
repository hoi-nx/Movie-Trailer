package com.sun.moviedb.utils

import androidx.annotation.IntDef
import androidx.annotation.StringDef

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@IntDef(
    CurrentPosistion.TRAILER,
    CurrentPosistion.CASTING,
    CurrentPosistion.PRODUCER
)
annotation class CurrentPosistion {
    companion object {
        const val TRAILER = 0
        const val CASTING = 1
        const val PRODUCER = 2
    }
}