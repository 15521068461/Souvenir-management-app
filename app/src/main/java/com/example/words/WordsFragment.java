package com.example.words;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {
    private  WordViewMedol wordViewMedol;
    private  RecyclerView recyclerView;
    private  MyAdapter myAdapter1,myAdapter2;
    FloatingActionButton floatingActionButton;
    private static  final  String VIEW_TYPE_SHP = "view_type_shp";
    private static  final  String IS_USING_CARD_VIEW = "is_using_card_view";
    private List<Word>  allWords;
    private DividerItemDecoration dividerItemDecoration;

    public WordsFragment() {
        setHasOptionsMenu(true);
        // Required empty public constructor
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switchViewType:
                SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP,Context.MODE_PRIVATE);
                boolean viewType = shp.getBoolean(IS_USING_CARD_VIEW,false);
                SharedPreferences.Editor editor = shp.edit();
                if(viewType){
                recyclerView.setAdapter(myAdapter1);
                recyclerView.addItemDecoration(dividerItemDecoration);
                editor.putBoolean(IS_USING_CARD_VIEW,false);
            }   else{
                recyclerView.setAdapter(myAdapter2);
                recyclerView.removeItemDecoration(dividerItemDecoration);
                editor.putBoolean(IS_USING_CARD_VIEW,true);
            }
                editor.apply();
//                if (recyclerView.getAdapter() == myAdapter1) {
//                    recyclerView.setAdapter(myAdapter2);
//                    break;
//                } else {
//                    recyclerView.setAdapter(myAdapter1);
//                    break;
//                }

        }
                return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wordViewMedol = ViewModelProviders.of(requireActivity()).get(WordViewMedol.class);
        recyclerView =requireActivity().findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter1= new MyAdapter(false,wordViewMedol);
        myAdapter2= new MyAdapter(true,wordViewMedol);
        SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP,Context.MODE_PRIVATE);
        boolean viewType = shp.getBoolean(IS_USING_CARD_VIEW,false);
        dividerItemDecoration= new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);//recycleView的分割线
        if(viewType){
            recyclerView.setAdapter(myAdapter2);
        }   else{
            recyclerView.setAdapter(myAdapter1);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
      //  recyclerView.setAdapter(myAdapter1);
       wordViewMedol.getAllWordsLive().observe(requireActivity(), new Observer<List<Word>>() {
           @Override
           public void onChanged(List<Word> words) {
               allWords=words;
               int temp = myAdapter1.getItemCount();
               myAdapter1.setAllWords(words);
               myAdapter2.setAllWords(words);
             //  if (temp!=words.size()){                //返回时刷新页面
                   myAdapter1.notifyDataSetChanged();
                   myAdapter2.notifyDataSetChanged();
              // }

           }
       });

       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.END) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               Word wordToDelete = allWords.get(viewHolder.getAdapterPosition());
               wordViewMedol.deleteWords(wordToDelete);

           }
       }).attachToRecyclerView(recyclerView);


  floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton2);
  floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          Bundle bundle = new Bundle();
          NavController navController = Navigation.findNavController(v);
          navController.navigate(R.id.action_wordsFragment_to_addFragment,bundle);
      }
  });

    }

    @Override
    public void onResume() {

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
        super.onResume();
    }
}
