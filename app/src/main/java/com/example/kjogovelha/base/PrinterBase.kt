package com.example.kjogovelha.base

import android.content.Context
import com.pax.dal.IDAL
import com.pax.dal.IPrinter
import com.pax.dal.exceptions.PrinterDevException
import com.pax.neptunelite.api.NeptuneLiteUser

class PrinterBase(val context: Context) {

    var dal:IDAL? = null
    var neptuneLiteUser:NeptuneLiteUser? = null
    var iprinter:IPrinter? = null

    fun setupPrinter(){
        neptuneLiteUser  = NeptuneLiteUser.getInstance()
        neptuneLiteUser?.let {
            dal = neptuneLiteUser!!.getDal(context)
        }

       dal?.let{
           iprinter = dal!!.printer
       }

    }

    fun init(){
        try {
            iprinter!!.init()
        }catch(ex:PrinterDevException){

        }
    }

    fun start(){
        try {
            iprinter!!.start()
        }catch(ex:PrinterDevException){

        }
    }

    fun printStr(string1:String, string2:String?){
        try {
            iprinter!!.printStr(string1,string2)
        }catch(ex:PrinterDevException){

        }
    }

}