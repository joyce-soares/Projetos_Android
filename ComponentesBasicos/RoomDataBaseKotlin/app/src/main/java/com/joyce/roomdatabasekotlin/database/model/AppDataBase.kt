package com.joyce.roomdatabasekotlin.database.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joyce.roomdatabasekotlin.database.model.daos.UserDao

/*  Classe que cria nosso banco de dados  */

@Database(entities = [User::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao


    //garante que tenha apenas uma instancia unica do db na aplicacao  ++
    companion object {

        private const val DATABASE_NAME: String = "nome_do_banco"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: kotlin.synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, DATABASE_NAME
            ).build() // ++

    }

}