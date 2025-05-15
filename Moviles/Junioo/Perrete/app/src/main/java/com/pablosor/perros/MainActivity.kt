package com.pablosor.perros

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablosor.perros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter // Para iniciarlo necesito una lista de imagenes
    private val dogimages = mutableListOf<String>() // La que obtenemos de la llamada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.svPerretes.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = DogAdapter(dogimages)
        binding.rvPerretes.layoutManager = LinearLayoutManager(this)
        binding.rvPerretes.adapter = adapter
    }

    // Creo la instancia del objeto retrofit
    // Uso corrutinas
     private fun getRetrofit():Retrofit{
         return Retrofit.Builder()
                // Hay que mirar como funciona la API
                // Las direcciones deben terminar con "/"
             .baseUrl("https://dog.ceo/api/breed/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }

    // Creo el método para buscar usando la corrutina
    private fun buscarPorRaza(raza:String){
        CoroutineScope(Dispatchers.IO).launch {
            // Lo que hagamos aqui dentro estará dentro de un hilo secundario
            val call = getRetrofit().create(ApiService::class.java).getDogsByBreeds("$raza/images")

            // call es un objeto response no el objeto que queremos mostrar
            val cachorros = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    // En este caso mostramos las imagenes en el Recycler View
                    val images = cachorros?.imagenes?: emptyList()

                    // Primero borramos lo que teníamos
                    dogimages.clear()
                    dogimages.addAll(images)

                    // Avisamos al adaptador de que hay cambios
                    adapter.notifyDataSetChanged()


                }else{
                    // Mostraremos un error
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error obteniendo datos de API", Toast.LENGTH_SHORT).show()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            buscarPorRaza(query.lowercase())
            return true
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}