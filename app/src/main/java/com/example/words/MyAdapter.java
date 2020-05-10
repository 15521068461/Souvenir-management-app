package com.example.words;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Word> allWords =new ArrayList<>();
    boolean useCardView;

    private int[] icons ={R.drawable.table,R.drawable.apple,R.drawable.cake,R.drawable.wireclothes,R.drawable.kiwifruit
            ,R.drawable.scarf};
    //MyItemClickListener myItemClickListener;

//    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
//        this.myItemClickListener = myItemClickListener;
//    }

    public MyAdapter(boolean useCardView, WordViewMedol wordViewMedol) {
        this.useCardView = useCardView;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

//    public   interface  MyItemClickListener{
//        public void onItemClick(int position ,View view);
//    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;

        if(useCardView){
            itemView = layoutInflater.inflate(R.layout.cell_cards,parent,false);
        }
        else {
             itemView = layoutInflater.inflate(R.layout.cell_normal,parent,false);
        }
        return  new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Word word = allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());
        holder.textViewPicture.setText(word.getPicture());
        holder.iv.setBackgroundResource(icons[word.getId()-1]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("你好"+holder.textViewEnglish.getText());


                String string1= (String) holder.textViewEnglish.getText();
                String string2= (String) holder.textViewChinese.getText();
                String string3= "牛牛牛牛牛牛牛牛";
                int i=word.getId();

                Bundle bundle = new Bundle();
                bundle.putString("EnglishWord",string1);
                bundle.putString("ChineseWord",string2);
                bundle.putString("Check",string3);
                 bundle.putInt("id",i);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_wordsFragment_to_addFragment,bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {  //内部类加上static 防止内存泄漏
    TextView textViewNumber,textViewEnglish,textViewChinese,textViewPicture;
    ImageView iv;
 public MyViewHolder(@NonNull View itemView) {
        super(itemView);
      textViewNumber =  itemView.findViewById(R.id.textViewNumber);
       textViewEnglish= itemView.findViewById(R.id.textViewEnglish);
       textViewChinese= itemView.findViewById(R.id.textViewChinese);
       textViewPicture=itemView.findViewById(R.id.textViewPicture);

       iv=itemView.findViewById(R.id.iv);
    }


}

}
