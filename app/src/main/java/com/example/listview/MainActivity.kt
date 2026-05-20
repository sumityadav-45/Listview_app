package com.example.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 1. Data Model mein Image resource ID add ki
data class Actor(val name: String, val movie: String, val imageRes: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.actorRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Data List (Apni images ke naam yaha likhein)
        // Agar image nahi hai toh abhi ke liye default icon use kiya hai
        val actorList = listOf(
            Actor("Shah Rukh Khan", "Pathaan", R.drawable.img1),
            Actor("Salman Khan", "Tiger 3", R.drawable.img2),
            Actor("Aamir Khan", "Dangal", R.drawable.img3),
            Actor("Akshay Kumar", "OMG 2", R.drawable.img4),
            Actor("Ranbir Kapoor", "Animal", R.drawable.img5),
            Actor("Ranveer Singh", "Rocky Aur Rani", R.drawable.img6),
            Actor("Hrithik Roshan", "War", R.drawable.img7),
            Actor("Amitabh Bachchan", "Sholay", R.drawable.img8),
            Actor("Ayushmann Khurrana", "Andhadhun", R.drawable.img9),
            Actor("Pankaj Tripathi", "Mirzapur", R.drawable.img10),
            Actor("Nawazuddin Siddiqui", "Gangs of Wasseypur", R.drawable.img11),
            Actor("Robert Downey Jr.", "Iron Man", R.drawable.img12),

        )

        recyclerView.adapter = ActorAdapter(actorList) { selectedActor ->
            //  CLICK MESSAGE (Actor + Movie dono show honge)
            val message = "Selected: ${selectedActor.name} \nMovie: ${selectedActor.movie}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

class ActorAdapter(
    private val actors: List<Actor>,
    private val onClick: (Actor) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtActorName)
        val movie: TextView = view.findViewById(R.id.txtMovieName)
        val img: ImageView = view.findViewById(R.id.imgActor) // Image reference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.name.text = actor.name
        holder.movie.text = "Movie: ${actor.movie}"

        // Image set karna
        holder.img.setImageResource(actor.imageRes)

        holder.itemView.setOnClickListener { onClick(actor) }
    }

    override fun getItemCount() = actors.size
}