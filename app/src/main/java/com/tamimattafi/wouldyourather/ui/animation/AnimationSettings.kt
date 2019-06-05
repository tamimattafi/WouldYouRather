package com.tamimattafi.wouldyourather.ui.animation

import android.os.Parcel
import android.os.Parcelable


data class AnimationSettings(
    val centerX: Int,
    val centerY: Int,
    val width: Int,
    val height: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeInt(centerX)
            writeInt(centerY)
            writeInt(width)
            writeInt(height)
        }
    }

    override fun describeContents(): Int {
        return width
    }

    companion object CREATOR : Parcelable.Creator<AnimationSettings> {

        fun createFromDimensions(centerX: Int, centerY: Int, width: Int, height: Int): AnimationSettings {
            return AnimationSettings(centerX, centerY, width, height)
        }

        override fun createFromParcel(parcel: Parcel): AnimationSettings {
            return AnimationSettings(parcel)
        }

        override fun newArray(size: Int): Array<AnimationSettings?> {
            return arrayOfNulls(size)
        }
    }
}