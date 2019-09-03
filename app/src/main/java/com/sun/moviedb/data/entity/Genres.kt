package com.sun.moviedb.data.entity

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Entity(tableName = "genres", primaryKeys = ["id"])
@Parcelize
data class Genres(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
):Parcelable
