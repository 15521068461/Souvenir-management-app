package com.example.words;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

//
//   Button button,button2,button3,button4;
//   WordViewMedol wordViewMedol;
//   RecyclerView recyclerView;
//   Switch aSwitch;
//   MyAdapter myAdapter1,myAdapter2;
 private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(findViewById(R.id.fragment2));
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navController.navigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    //        recyclerView= findViewById(R.id.RecycleView);
//        aSwitch=findViewById(R.id.switch1);
//        myAdapter1 = new MyAdapter(false, wordViewMedol);
//        myAdapter2 = new MyAdapter(true, wordViewMedol);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(myAdapter1);
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    recyclerView.setAdapter(myAdapter2);
//                }else {
//                    recyclerView.setAdapter(myAdapter1);
//                }
//            }
//        });
//
//
//        wordViewMedol= ViewModelProviders.of(this).get(WordViewMedol.class);
//
//
//         wordViewMedol.getAllWordsLive().observe(this, new Observer<List<Word>>() {
//             @Override
//             public void onChanged(List<Word> words) {
//                          myAdapter1.setAllWords(words);
//                           myAdapter1.notifyDataSetChanged();
//                           myAdapter2.setAllWords(words);
//                           myAdapter2.notifyDataSetChanged();
//             }
//         });
//
//
//         button=findViewById(R.id.button);
//       // button2= findViewById(R.id.button2);
//        button3 =findViewById(R.id.button3);
//       //  button4= findViewById(R.id.button4);
//
//         button.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 String[] english = {
//                         "Hello",
//                         "World",
//                         "Android",
//                         "Google",
//                         "Studio"
//                 };
//                 String[] chinese = {
//                         "你好",
//                         " 世界",
//                         "安卓系统",
//                         "谷歌公司",
//                         "工作室"
//                 };
//         for(int i = 0; i<english.length;i++){
//             wordViewMedol.insertWords(new Word(english[i],chinese[i]));   //参数是一个Word对象
//         }
//             }
//         });
//         button3.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//
//                wordViewMedol.deleteAllWords();
//             }
//         });
//         button2.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Word word =new Word("Morning","早上好");
//                 word.setId(60);
//                wordViewMedol.updateWords(word);
//             }
//         });
//         button4.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Word word =new Word("Morning","早上好");
//                 word.setId(60);
//                wordViewMedol.deleteWords(word);
//
//             }
//         });






}
