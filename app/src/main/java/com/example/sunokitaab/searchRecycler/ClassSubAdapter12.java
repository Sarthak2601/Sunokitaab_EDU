package com.example.sunokitaab.searchRecycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunokitaab.Class_Subject_Audios.Audios;
import com.example.sunokitaab.R;
import com.example.sunokitaab.SearchFragment;

import java.util.ArrayList;

public class ClassSubAdapter12 extends RecyclerView.Adapter<ClassSubAdapter12.viehHolder> {


    String id;
    Context context;
    ArrayList<String> classes;
    OnNoteListener monNoteListener;

    public ClassSubAdapter12(Context context, ArrayList<String> classes,OnNoteListener onNoteListener) {
        this.context = context;
        this.classes = classes;
        this.monNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public viehHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viehHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.class_cardview, parent, false),monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final viehHolder holder, final int position) {

        holder.textView_title.setText(classes.get(position));

       /* holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               // Toast.makeText(context, "View tag" + v.getTag() + "Item id tag" + (getItemId(v.getId())) + "LL ID:" + v.getId() + "LLSETID:" + id, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(context, Audios.class);

                //CLASS 1
                if (v.getTag() == Integer.valueOf(R.id.ll1)) {
                    if (classes.get(position).equals("English")) {

                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c85da9c34ff860e396aa95651c236740"); // CLASS 1 ENG

                    } else if (classes.get(position).equals("Hindi")) {

                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/e98ce18b8c2f9cb626bfa267a837a028"); // CLASS 1 HIN

                    }
                }
                //CLASS 2
                else if (v.getId() == R.id.ll2) {
                    if (classes.get(position).equals("English")) {

                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/082ccfea0da183211920aaa68f51db2f"); // CLASS 2 ENG

                    } else if (classes.get(position).equals("Hindi")) {

                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/e1e0bc541a419f3656245785988f67a8"); // CLASS 2 HIN

                    }
                }
                //CLASS 3
                else if (v.getId() == R.id.ll3) {
                    if (classes.get(position).equals("English")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/df1e619eb5ef929b9fc940dd2b62e629"); // CLASS 3 ENG

                    } else if (classes.get(position).equals("Hindi")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/236e6f04f2eb41872d18ad9d8d70f0fb"); // CLASS 3 HIN
                    } else if (classes.get(position).equals("E.V.S")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c0e434a5813658ca9de0e079600dd745"); // CLASS 3 EVS
                    }
                }
                //CLASS 4
                else if (v.getId() == R.id.ll4) {
                    if (classes.get(position).equals("English")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/184fb533ba85fe20808b100733e30467"); // CLASS 4 ENG
                    } else if (classes.get(position).equals("Hindi")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/03ebcfa459d5b5a87bece78f415ef101"); // CLASS 4 HIN
                    } else if (classes.get(position).equals("E.V.S")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/928647963fb8522d182c85eeb6908070"); // CLASS 4 EVS
                    }
                }
                //CLASS 5
                else if (v.getId() == R.id.ll5) {
                    if (classes.get(position).equals("English")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/46a16dd98c379d401392246a86c56b50"); // CLASS 5 ENG
                    } else if (classes.get(position).equals("Hindi")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/cb5d0acc65f5e9e2356ce7c7841e0689"); // CLASS 5 HIN
                    } else if (classes.get(position).equals("E.V.S")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/4a11403e1d4ffb6bed0036ce295f251f"); // CLASS 5 EVS
                    }
                }
                //CLASS 6
                else if (v.getTag()== Integer.valueOf(R.id.ll6)) {
                    if (classes.get(position).equals("English")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/357c6961dc6f1497fe3136a5cfb6e819"); // CLASS 6 ENG
                    } else if (classes.get(position).equals("Science")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 6 SCI

                    } else if (classes.get(position).equals("Hindi")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 6 HIN

                    } else if (classes.get(position).equals("Social Science")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 6 SST

                    }
                }
                //CLASS 7
                else if (v.getId() == R.id.ll7) {
                    if (classes.get(position).equals("English")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/9a75024013121ed2f455043e2c6002b6"); // CLASS 7 ENG
                    } else if (classes.get(position).equals("Science")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/471092630637950e2df99d2a0c7afbfa"); // CLASS 7 SCI
                    } else if (classes.get(position).equals("Hindi")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c51850788eb41fe3ccacd3934ae1d411"); // CLASS 7 HIN
                    } else if (classes.get(position).equals("Social Science")) {
                        intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/f975e80e229a89ee7b3d7f37ce95b58f"); // CLASS 7 SST
                    }
                }

                //CLASS 8

                else if(v.findViewById(R.id.ll8) == holder.linearLayout.findViewById(R.id.ll8)){
                    if(classes.get(position).equals( "English")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/0a870144e3285568b964af496aa9149d"); // CLASS 8 ENG
                    }
                    else if(classes.get(position).equals("Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/9f162f9d43e69ce26f84dffebbb973d1"); // CLASS 8 SCI
                    }
                    else if(classes.get(position).equals("Hindi")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/952482e4f5a6d491f29a3a6c258851fe"); // CLASS 8 HIN
                    }
                    else if(classes.get(position).equals("Social Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/88d979304d8f8f3ffb9277f2c4d0d352"); // CLASS 8 SST
                    }
                }
                //CLASS 9
                else if(v.findViewById(R.id.ll9) == holder.linearLayout.findViewById(R.id.ll9)){
                    if(classes.get(position).equals( "English")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/17e402370158cc3f370e5618e56a37279"); // CLASS 9 ENG

                    }
                    else if(classes.get(position).equals("Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 9 SCI
                    }
                    else if(classes.get(position).equals("Hindi")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 9 HIN
                    }
                    else if(classes.get(position).equals("Social Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 9 SST
                    }
                }
                //CLASS 10
                else if(v.findViewById(R.id.ll10) == holder.linearLayout.findViewById(R.id.ll10)){
                    if(classes.get(position).equals( "English")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/357c6961dc6f1497fe3136a5cfb6e819"); // CLASS 10 ENG
                    }
                    else if(classes.get(position).equals("Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 10 SCI
                    }
                    else if(classes.get(position).equals("Hindi")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 10 HIN
                    }
                    else if(classes.get(position).equals("Social Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/95126300095e18ea421acc593a0fd564"); // CLASS 10 SST
                    }
                }
                //CLASS 11
                else if(v.findViewById(R.id.ll11) == holder.linearLayout.findViewById(R.id.ll11)){
                    if(classes.get(position).equals( "English")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/357c6961dc6f1497fe3136a5cfb6e819"); // CLASS 11 ENG
                    }
                    else if(classes.get(position).equals("Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 11 SCI
                    }
                    else if(classes.get(position).equals("Hindi")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 11 HIN
                    }
                    else if(classes.get(position).equals("Social Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 11 SST
                    }
                }
                //CLASS 12
                else if(v.findViewById(R.id.ll12) == holder.linearLayout.findViewById(R.id.ll12)){
                    if(classes.get(position).equals( "English")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/357c6961dc6f1497fe3136a5cfb6e819"); // CLASS 12 ENG
                    }
                    else if(classes.get(position).equals("Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 12 SCI
                    }
                    else if(classes.get(position).equals("Hindi")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 12 HIN
                    }
                    else if(classes.get(position).equals("Social Science")){
                        intent1.putExtra("rss","https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 12 SST
                    }
                    }

                context.startActivity(intent1);
            }
        });
          */

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class viehHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView_title;
        private LinearLayout linearLayout;
        OnNoteListener onNoteListener;

        public viehHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.subjects);
           // linearLayout = itemView.findViewById(R.id.mainLinearLayout);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(getAdapterPosition());
        }
    }
        public interface OnNoteListener{
            void OnNoteClick(int position);
        }


    }
