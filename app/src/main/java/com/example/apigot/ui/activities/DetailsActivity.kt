package com.example.apigot.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.apigot.R
import com.example.apigot.databinding.ActivityDetailsBinding
import com.example.apigot.model.SecondView
import com.example.apigot.network.GotAPI
import com.example.apigot.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "0")

        val call = RetrofitService.getRetrofit()
            .create(GotAPI::class.java)
            .getCharacterDetailApiary(id)

        call.enqueue(object : Callback<SecondView> {
            override fun onResponse(call: Call<SecondView>, response: Response<SecondView>) {

                with(binding) {

                    pbConexion.visibility = View.INVISIBLE

                    textFullName.text = response.body()?.fullName
                    Glide.with(this@DetailsActivity)
                        .load(response.body()?.imageUrl)
                        .into(ivFoto)
                    textNombre.text = response.body()?.firstName
                    textApellido.text = response.body()?.lastName
                    textTitulo.text = response.body()?.title
                    textCasa.text = response.body()?.family
                    Glide.with(this@DetailsActivity)
                        .load(response.body()?.houseFlag)
                        .into(ivBandera)
                }
            }

            override fun onFailure(call: Call<SecondView>, t: Throwable) {
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(
                    this@DetailsActivity,
                    getString(R.string.no_conexion),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        })

        binding.tvNombre.text = getString(R.string.nombre)
        binding.tvApellido.text = getString(R.string.apellido)
        binding.tvTitulo.text = getString(R.string.titulo)
        binding.tvFamilia.text = getString(R.string.Casa)
    }
}