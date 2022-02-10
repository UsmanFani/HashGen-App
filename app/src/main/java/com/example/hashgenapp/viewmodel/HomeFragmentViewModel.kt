package com.example.hashgenapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HomeFragmentViewModel: ViewModel() {


     fun getHash(plainText:String,algorithm:String ):String{
        val bytes=MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
         return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray):String{
        return byteArray.joinToString("") { "%02x".format(it) }
    }

}