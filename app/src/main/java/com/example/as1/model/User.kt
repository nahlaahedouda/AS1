package com.example.as1.model

import android.os.Parcel
import android.os.Parcelable

class User (val id: Int = -1, val name: String?, val email: String?, val phone_Number: Int, val password: String?):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeInt(phone_Number)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {

        const val TABLE_NAME= "Movie"
        const val COL_ID= "_id"
        const val COL_NAME= "name"
        const val COL_EMAIL= "email"
        const val COL_PHONE_NUMBER= "phone_number"
        const val COL_PASSWORD= "password"

        const val TABLE_CREATE=
            "CREATE TABLE $TABLE_NAME(" +
                    "$COL_NAME TEXT NOT NULL," +
                    "$COL_EMAIL TEXT," +
                    "$COL_PHONE_NUMBER INTEGER," +
                    "$COL_PASSWORD TEXT, " +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ")"


        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}