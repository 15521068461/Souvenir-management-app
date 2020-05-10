package com.example.words;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    private  String word;

    private String chineseMeaning;

    private  String picture;

   // private byte[] img1;

    public Word(String word, String chineseMeaning,String picture) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
        this.picture=picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

//    public byte[] getImg1() {
//        return img1;
//    }
//
//    public void setImg1(byte[] img1) {
//       this.img1 = img1;
//  }
}
