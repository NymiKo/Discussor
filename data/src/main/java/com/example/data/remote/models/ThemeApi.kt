package com.example.data.remote.models

import kotlinx.serialization.*

@Serializable
data class ThemeApi(val id_theme: Int, val name_theme: String, val vote_for: String, val vote_against: String) {

//    companion object {
//        @UnstableDefault
//        fun toObject(stringValue: String): ThemeApi {
//            return Json.nonstrict.parse(serializer(), stringValue)
//        }
//    }
}

//
//@UnstableDefault
//fun ThemeApi.toJson(): String {
//    return Json.stringify(ThemeApi.serializer(), this)
//}
