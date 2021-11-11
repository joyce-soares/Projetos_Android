package com.joyce.roomdatabase_kotlin.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity   //anotacao diz que essa classe é uma tabela do meu banco
data class User(
    @ColumnInfo(name = "first_name") val firstName: String, //anotacao para na coluna do db aparecer o nome com _
    @ColumnInfo(name = "last_name")  val lastName: String,
){
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0  //uid vai ser a chave primaria autogerada
}
