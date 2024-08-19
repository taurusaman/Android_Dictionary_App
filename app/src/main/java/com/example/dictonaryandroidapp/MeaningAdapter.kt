package com.example.dictonaryandroidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictonaryandroidapp.databinding.MeaningRecyclerRowBinding
import com.example.dictonaryandroidapp.model.Meaning


class MeaningAdapter(private var meaningList: List<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {


    class  MeaningViewHolder(private val binding: MeaningRecyclerRowBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(meaning: Meaning){
                //Bind all the meanings
                binding.partOfSpeechTextview.text  =  meaning.partOfSpeech

                binding.definitionsTextview.text = meaning.definitions.joinToString(separator = "\n\n") {
                    val currentIndex = meaning.definitions.indexOf(it)

                    (currentIndex+1).toString() + ". " +it.definition.toString()
                }

                if (meaning.synonyms.isEmpty()){
                    binding.synonymsTextview.visibility = View.GONE
                    binding.synonymsTitleTextview.visibility = View.GONE
                }else{
                    binding.synonymsTextview.visibility = View.VISIBLE
                    binding.synonymsTitleTextview.visibility = View.VISIBLE
                    binding.synonymsTextview.text = meaning.synonyms.joinToString(separator = " ,")
                }


                if (meaning.antonyms.isEmpty()){
                    binding.antonymsTextview.visibility = View.GONE
                    binding.antonymsTitleTextview.visibility = View.GONE
                }else{
                    binding.antonymsTextview.visibility = View.VISIBLE
                    binding.antonymsTitleTextview.visibility = View.VISIBLE
                    binding.antonymsTextview.text = meaning.antonyms.joinToString(separator = " ,")
                }
            }
    }



    fun updateNewData(newMeaningList : List<Meaning>){
         meaningList = newMeaningList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecyclerRowBinding.
        inflate(LayoutInflater.from(parent.context),parent, false)
        return MeaningViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }



    override fun getItemCount(): Int {
        return meaningList.size
    }


}