package com.example.apigot.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apigot.R
import com.example.apigot.databinding.ListBinding
import com.example.apigot.model.PrincipalView

class CharactersAdapter(
    private var personajes: ArrayList<PrincipalView>,
    private var onCharacterClicked: (PrincipalView) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(private var binding: ListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(personaje: PrincipalView) {
            Glide.with(itemView.context)
                .load(personaje.imageUrl)
                .into(binding.ivFoto)
            binding.tvName.text = personaje.firstName
            binding.tvLastName.text = personaje.lastName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personajes[position])

        holder.itemView.setOnClickListener {
            onCharacterClicked(personajes[position])
        }
    }
}
