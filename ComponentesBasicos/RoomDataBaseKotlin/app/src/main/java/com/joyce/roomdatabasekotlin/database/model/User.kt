package com.joyce.roomdatabasekotlin.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "first_name") val firstName: String, //anotacao para na coluna do db aparecer o nome com _
    @ColumnInfo(name = "last_name")  val lastName: String,
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0  //uid vai ser a chave primaria autogerada
}
