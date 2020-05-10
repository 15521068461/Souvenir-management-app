package com.example.words;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
  private Button buttonSubmit;
  private EditText editTextEnglish ,editTextChinese,editTextPicture;
private  WordViewMedol wordViewMedol;
private TextView textView;
private ImageButton imageButton;

    public AddFragment() {
        // Required empty public constructor
    }
    //------------------------------------------------------------------------------------------
    //裁剪图片临时保存的位置
    final String IMAGE_FILE_LOCATION = "file:///"+ Environment.getExternalStorageDirectory().getPath()+"/temp.png";
    private Uri tempUri = Uri.parse(IMAGE_FILE_LOCATION);
    private static final String IMAGE_UNSPECIFIED = "image/*";
    //----------------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();




         wordViewMedol = ViewModelProviders.of(activity).get(WordViewMedol.class);
        buttonSubmit = activity.findViewById(R.id.buttonSubmit);
        editTextEnglish= activity.findViewById(R.id.editTextEnglish);
        editTextChinese= activity.findViewById(R.id.editTextChniese);
        editTextPicture=activity.findViewById(R.id.editTextPicture);
        imageButton=activity.findViewById(R.id.imageButton);

        textView=activity.findViewById(R.id.textView);

        String string1 = getArguments().getString("EnglishWord");
        String string2 = getArguments().getString("ChineseWord");
        final String string3 = getArguments().getString("Check");
        final int j =getArguments().getInt("id");


        if(string1!=null||string2!=null) {
            System.out.println("二个人文化和融合和");
            buttonSubmit.setText("修改");
            textView.setText("修改纪念品");
            editTextEnglish.setText(string1);
            editTextChinese.setText(string2);

        }
        buttonSubmit.setEnabled(false);
        editTextEnglish.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextEnglish,0);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            String english = editTextEnglish.getText().toString().trim();
            String chinese = editTextChinese.getText().toString().trim();
         //   String picture= editTextPicture.getText().toString().trim();
            buttonSubmit.setEnabled(!english.isEmpty() && ! chinese.isEmpty());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextEnglish.addTextChangedListener(textWatcher);
        editTextChinese.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(string3!=null){

                    System.out.println(string3 + "艰苦开卷考进口酒开机");
                    String english1 = editTextEnglish.getText().toString().trim();
                    String chinese1 = editTextChinese.getText().toString().trim();
                    String picture1= editTextPicture.getText().toString().trim();

                    Word word1 =new Word(english1,chinese1,picture1);
                    word1.setId(j);
                   wordViewMedol.updateWords(word1);



                } else {
                    String english = editTextEnglish.getText().toString().trim();
                    String chinese = editTextChinese.getText().toString().trim();
                    String picture= editTextPicture.getText().toString().trim();
                    Word word =new Word(english,chinese,picture);
                    wordViewMedol.insertWords(word);




                }

               NavController navController = Navigation.findNavController(v);
               navController.navigateUp();

                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请读取文件和照片的权限并回调onRequestPermissionsResult(),下同
                requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 || requestCode == 2 || requestCode == 3){
            for (int i=0;i<permissions.length;i++){
                if (permissions[i].equals("android.permission.WRITE_EXTERNAL_STORAGE") ){//&& grantResults[i] == PackageManager.PERMISSION_GRANTED
                    gallery(requestCode);
                }else {
                    Toast.makeText(getContext(),"授权失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            Uri uri = data.getData();
            switch (requestCode){
                case 1:

                   // imageButton.setImageResource(R.drawable.cake);
                   // imageButton.setImageURI(Uri.parse(ABC));
                    imageButton.setImageURI(tempUri);
                    System.out.println(tempUri+"哇大大青蛙达瓦的");
                   // System.out.println(ABC+"哇大大青蛙达瓦的");
                      imageButton.setBackgroundColor(Color.rgb(255,255,255));

                    break;
                case 2: break;

                case 3: break;
                case 4:
                case 5:

                case 6:
                    crop(uri,requestCode);
                    break;
            }
        }
    }



    //调用系统相册选择一张图片
    private void gallery(int requestCode){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(galleryIntent, requestCode+3);
    }

    //调用系统裁剪功能
    private void crop(Uri uri,int requestCode){
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.putExtra("crop", true);
        cropIntent.putExtra("aspectX",1);
        cropIntent.putExtra("aspectY",1);
        cropIntent.putExtra("outputX",300);
        cropIntent.putExtra("outputY",300);
        cropIntent.putExtra("return-data",false);
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(cropIntent, requestCode-3);
    }

    //将图片按钮转换为二进制数组
    private byte[] imgToByte(ImageButton img){
        Drawable drawable = img.getDrawable();
        Bitmap bmp;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        bmp = bitmapDrawable.getBitmap();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,os);
        return os.toByteArray();
    }
}

