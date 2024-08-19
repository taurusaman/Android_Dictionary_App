package com.example.dictonaryandroidapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictonaryandroidapp.databinding.ActivityMainBinding
import com.example.dictonaryandroidapp.model.WordResult
import com.example.dictonaryandroidapp.service.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: MeaningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val word = binding.searchInput.text.toString()
            getMeaning(word)

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }


        adapter = MeaningAdapter(emptyList())
        binding.meaningRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.meaningRecyclerView.adapter = adapter

    }






    fun getMeaning(word: String){
        setInProgress(true)

            GlobalScope.launch {
                try {
                    val response = RetrofitInstance.apiServices.getMeaning(word)
                    Log.i("Response From Api", response.body().toString())
                    if (response.body() == null) {
                        throw (Exception())
                    }

                    response.body()?.first()?.let { definition ->
                        runOnUiThread {
                            setInProgress(false)
                            setUI(definition)
                        }
                    }

                } catch (e: Exception) {
                    runOnUiThread {
                        setInProgress(false)
                        Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }

    }



   private fun setUI(response: WordResult){
       binding.wordTextview.text = response.word
       binding.phoneticTextview.text = response.phonetic
       adapter.updateNewData(response.meanings)
   }



    fun setInProgress(inProgress: Boolean) {
        runOnUiThread {
            if (inProgress) {
                binding.searchButton.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.searchButton.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }



}