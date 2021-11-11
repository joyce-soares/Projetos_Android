package com.joyce.roomdatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.joyce.roomdatabasekotlin.database.model.AppDataBase
import com.joyce.roomdatabasekotlin.database.model.User
import com.joyce.roomdatabasekotlin.database.model.daos.UserDao
import com.joyce.roomdatabasekotlin.databinding.ActivityNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
*  1 - cria instancia do db
*  2 - cria instancia unica da dao de users
*  3 - set do btn de salvar
*  4 - valida se o user é valido e se for salva no db e mostra toast
* */

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    private lateinit var dataBase: AppDataBase //1
    private lateinit var userDao: UserDao      //2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        dataBase = AppDataBase.getInstance(this) //1
        userDao = dataBase.userDao() //1
    }

    override fun onStart() {
        super.onStart()

        this.binding.btnSave.setOnClickListener{ //3

            CoroutineScope(Dispatchers.IO).launch {

                val result = saveUser(
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                )

                withContext(Dispatchers.Main){

                    Toast.makeText(this@NewUserActivity,
                        if(result) "User saved" else "Error trying to save user",
                        Toast.LENGTH_SHORT).show()

                    if(result)
                        finish()
                }
            }
        }
    }

    private suspend fun saveUser(firtName: String, lastName: String): Boolean{ //3

        if(firtName.isBlank() || firtName.isEmpty())  //is blank é se n contem só espaços
            return false

        if (lastName.isBlank() || lastName.isEmpty())
            return false

        this.userDao.insert(User(firtName, lastName))
        return true
    }
}