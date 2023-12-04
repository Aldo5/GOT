package com.example.apigot.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apigot.R
import com.example.apigot.databinding.ActivityMainBinding
import com.example.apigot.model.PrincipalView
import com.example.apigot.network.GotAPI
import com.example.apigot.network.RetrofitService
import com.example.apigot.ui.adapters.CharactersAdapter
import com.example.apigot.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit()
            .create(GotAPI::class.java)
            .getGot("characters/characters_list")

        call.enqueue(object: Callback<ArrayList<PrincipalView>> {
            override fun onResponse(
                call: Call<ArrayList<PrincipalView>>,
                response: Response<ArrayList<PrincipalView>>
            ) {
                binding.pbConexion.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, getString(R.string.respuesta_servidor, response.toString()))
                Log.d(Constants.LOGTAG, getString(R.string.datos, response.body().toString()))

                val charactersAdapter = CharactersAdapter(response.body()!!){people ->

                    Toast.makeText(this@MainActivity,
                        getString(R.string.click_personaje, people.firstName), Toast.LENGTH_SHORT).show()

                    val bundle = bundleOf(
                        "id" to people.id
                    )

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }

                binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                binding.rvMenu.adapter = charactersAdapter

            }

            override fun onFailure(call: Call<ArrayList<PrincipalView>>, t: Throwable) {
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity,
                    getString(R.string.no_conexion),
                    Toast.LENGTH_SHORT)
                    .show()
            }

        })

        if(user?.isEmailVerified != true){
            /*firebaseAuth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()*/
            Toast.makeText(this@MainActivity,
                getString(R.string.email_verify),
                Toast.LENGTH_SHORT).show()
            user?.reload()
        }

        binding.signOutButton.setOnClickListener {

            firebaseAuth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}