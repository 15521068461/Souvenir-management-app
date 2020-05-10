package com.example.words;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewMedol extends AndroidViewModel {    //WordViewModel

     private WordRepository wordRepository;
    public WordViewMedol(@NonNull Application application) {
        super(application);
        wordRepository= new WordRepository(application);
      }


 LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    void  insertWords(Word...words){
       wordRepository.insertWords(words);
    }
    void  updateWords(Word...words){
       wordRepository.updateWords(words);
    }

    void  deleteWords(Word...words){
      wordRepository.deleteWords(words);
    }
    void  deleteAllWords(){
     wordRepository.deleteAllWords();
    }



    }

