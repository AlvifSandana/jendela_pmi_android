package com.app.slider

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

@Suppress("DEPRECATION")
object PreferenceHelper {

    val ID = "ID"
    val USER_ID = "USER_ID"
    val USER_EMAIL = "EMAIL"
    val USER_PASSWORD = "PASSWORD"
    val USER_API_TOKEN = "API_TOKEN"
    val USER_ROLE = "ROLE"
    val USER_STATUS = "STATUS"
    val USER_FULL_NAME = "FULLNAME"
    val USER_TTL = "TTL"
    val USER_ADDRESS = "ADDRESS"
    val USER_GOL_DARAH = "GOLDARAH"

    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    // id getter setter
    var SharedPreferences.id
        get() = getInt(ID, 0)
        set(value) {
            editMe {
                it.putInt(ID, value)
            }
        }

    // user id getter setter
    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value) {
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    // user email getter setter
    var SharedPreferences.userEmail
        get() = getString(USER_EMAIL, "")
        set(value) {
            editMe {
                it.putString(USER_EMAIL, value)
            }
        }

    // user password getter setter
    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    // user api_token getter setter
    var SharedPreferences.api_token
        get() = getString(USER_API_TOKEN, "")
        set(value) {
            editMe {
                it.putString(USER_API_TOKEN, value)
            }
        }

    // user role getter setter
    var SharedPreferences.role
        get() = getString(USER_ROLE, "")
        set(value) {
            editMe {
                it.putString(USER_ROLE, value)
            }
        }

    // user full name  getter setter
    var SharedPreferences.fullname
        get() = getString(USER_FULL_NAME, "")
        set(value) {
            editMe {
                it.putString(USER_FULL_NAME, value)
            }
        }

    // user ttl getter setter
    var SharedPreferences.TTL
        get() = getString(USER_TTL, "")
        set(value) {
            editMe {
                it.putString(USER_TTL, value)
            }
        }

    // user address getter setter
    var SharedPreferences.address
        get() = getString(USER_ADDRESS, "")
        set(value) {
            editMe {
                it.putString(USER_ADDRESS, value)
            }
        }

    // user status getter setter
    var SharedPreferences.status
        get() = getString(USER_STATUS, "")
        set(value) {
            editMe {
                it.putString(USER_STATUS, value)
            }
        }

    // golongan darah getter setter
    var SharedPreferences.golongan_darah
        get() = getString(USER_GOL_DARAH, "")
        set(value) {
            editMe {
                it.putString(USER_GOL_DARAH, value)
            }
        }

    // clear value
    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}