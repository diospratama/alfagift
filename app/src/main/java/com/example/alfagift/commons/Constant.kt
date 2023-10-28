package com.example.alfagift.commons

object Constant {
    object Connection {
        const val connect_timeout = 80L
        const val read_timeout = 80L
        const val write_timeout = 15L
    }
    object  Header{
        const val accept="application/json"
        const val language="en-US"
        const val sort="popularity.desc"
    }

    object  Movies{
        const val pageDefault=1
        const val extraData="extra_data_description"
        const val extraDataIdMovie="extra_data_idMovie"
    }

}