package com.example.sunokitaab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sunokitaab.Class_Subject_Audios.Audios;
import com.example.sunokitaab.searchRecycler.ClassSubAdapter12;
import java.util.ArrayList;

public class SearchFragment extends Fragment implements ClassSubAdapter12.OnNoteListener {

    RecyclerView recyclerViewStories;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    RecyclerView recyclerView5;
    RecyclerView recyclerView6;
    RecyclerView recyclerView7;
    RecyclerView recyclerView8;
    RecyclerView recyclerView9;
    RecyclerView recyclerView10;
    RecyclerView recyclerView11;
    RecyclerView recyclerView12;

    LinearLayout linearLayoutStories;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;
    LinearLayout linearLayout5;
    LinearLayout linearLayout6;
    LinearLayout linearLayout7;
    LinearLayout linearLayout8;
    LinearLayout linearLayout9;
    LinearLayout linearLayout10;
    LinearLayout linearLayout11;
    LinearLayout linearLayout12;

    ArrayList<String> chaptersStories = new ArrayList<String>();
    ArrayList<String> subjectsPri = new ArrayList<String>();
    ArrayList<String> subjectsMid = new ArrayList<String>();
    ArrayList<String> subjectsHigh = new ArrayList<String>();
    ArrayList<String> subjectsHighHigh = new ArrayList<String>();

    private static final String TAG = "SearchFragment";


    ClassSubAdapter12.OnNoteListener onNoteListenerStories;
    ClassSubAdapter12.OnNoteListener onNoteListener1;
    ClassSubAdapter12.OnNoteListener onNoteListener2;
    ClassSubAdapter12.OnNoteListener onNoteListener3;
    ClassSubAdapter12.OnNoteListener onNoteListener4;
    ClassSubAdapter12.OnNoteListener onNoteListener5;
    ClassSubAdapter12.OnNoteListener onNoteListener6;
    ClassSubAdapter12.OnNoteListener onNoteListener7;
    ClassSubAdapter12.OnNoteListener onNoteListener8;
    ClassSubAdapter12.OnNoteListener onNoteListener9;
    ClassSubAdapter12.OnNoteListener onNoteListener10;
    ClassSubAdapter12.OnNoteListener onNoteListener11;
    ClassSubAdapter12.OnNoteListener onNoteListener12;



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, null);

        ClassSubAdapter12 stories = new ClassSubAdapter12(getActivity(),chaptersStories,onNoteListenerStories);

        ClassSubAdapter12 class1 = new ClassSubAdapter12(getActivity(),subjectsPri,onNoteListener1);
        ClassSubAdapter12 class2 = new ClassSubAdapter12(getActivity(),subjectsPri,onNoteListener2);

        ClassSubAdapter12 class3 = new ClassSubAdapter12(getActivity(),subjectsMid,onNoteListener3);
        ClassSubAdapter12 class4 = new ClassSubAdapter12(getActivity(),subjectsMid,onNoteListener4);
        ClassSubAdapter12 class5 = new ClassSubAdapter12(getActivity(),subjectsMid,onNoteListener5);

        ClassSubAdapter12 class6 = new ClassSubAdapter12(getActivity(),subjectsHigh,onNoteListener6);
        ClassSubAdapter12 class7 = new ClassSubAdapter12(getActivity(),subjectsHigh,onNoteListener7);
        ClassSubAdapter12 class8 = new ClassSubAdapter12(getActivity(),subjectsHigh,onNoteListener8);
        ClassSubAdapter12 class9 = new ClassSubAdapter12(getActivity(),subjectsHigh,onNoteListener9);
        ClassSubAdapter12 class10 = new ClassSubAdapter12(getActivity(),subjectsHigh,onNoteListener10);
        ClassSubAdapter12 class11 = new ClassSubAdapter12(getActivity(),subjectsHighHigh,onNoteListener11);
        ClassSubAdapter12 class12 = new ClassSubAdapter12(getActivity(),subjectsHighHigh,onNoteListener12);



       // ClassSubAdapter12 classSubAdapterhigh = new ClassSubAdapter12(getActivity(),subjectsHigh,this);
       // ClassSubAdapter12 classSubAdapterhighhigh = new ClassSubAdapter12(getActivity(),subjectsHighHigh,this);
       // ClassSubAdapter12 classSubAdapterPri = new ClassSubAdapter12(getActivity(),subjectsPri,this);
       // ClassSubAdapter12 classSubAdapterMid = new ClassSubAdapter12(getActivity(),subjectsMid,this);


        recyclerViewStories = rootView.findViewById(R.id.recyclerStories);
        recyclerView1 = rootView.findViewById(R.id.recycler1);
        recyclerView2 = rootView.findViewById(R.id.recycler2);
        recyclerView3 = rootView.findViewById(R.id.recycler3);
        recyclerView4 = rootView.findViewById(R.id.recycler4);
        recyclerView5 = rootView.findViewById(R.id.recycler5);
        recyclerView6 = rootView.findViewById(R.id.recycler6);
        recyclerView7 = rootView.findViewById(R.id.recycler7);
        recyclerView8 = rootView.findViewById(R.id.recycler8);
        recyclerView9 = rootView.findViewById(R.id.recycler9);
        recyclerView10 = rootView.findViewById(R.id.recycler10);
        recyclerView11 = rootView.findViewById(R.id.recycler11);
        recyclerView12 = rootView.findViewById(R.id.recycler12);


        linearLayoutStories = rootView.findViewById(R.id.llstories);
        linearLayout2= rootView.findViewById(R.id.ll2);
        linearLayout3= rootView.findViewById(R.id.ll3);
        linearLayout4= rootView.findViewById(R.id.ll4);
        linearLayout5= rootView.findViewById(R.id.ll5);
        linearLayout6= rootView.findViewById(R.id.ll6);
        linearLayout7= rootView.findViewById(R.id.ll7);
        linearLayout8= rootView.findViewById(R.id.ll8);
        linearLayout9= rootView.findViewById(R.id.ll9);
        linearLayout10= rootView.findViewById(R.id.ll10);
        linearLayout11 = rootView.findViewById(R.id.ll11);
        linearLayout12 = rootView.findViewById(R.id.ll12);



        LinearLayoutManager linearLayoutManagerStories = new LinearLayoutManager(getActivity());
        linearLayoutManagerStories.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity());
        linearLayoutManager3.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(getActivity());
        linearLayoutManager5.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(getActivity());
        linearLayoutManager6.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager7 = new LinearLayoutManager(getActivity());
        linearLayoutManager7.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager8 = new LinearLayoutManager(getActivity());
        linearLayoutManager8.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager9 = new LinearLayoutManager(getActivity());
        linearLayoutManager9.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager10 = new LinearLayoutManager(getActivity());
        linearLayoutManager10.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager11 = new LinearLayoutManager(getActivity());
        linearLayoutManager11.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager12 = new LinearLayoutManager(getActivity());
        linearLayoutManager12.setOrientation(RecyclerView.HORIZONTAL);

        recyclerViewStories.setLayoutManager(linearLayoutManagerStories);
        recyclerViewStories.setAdapter(stories);

        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.setAdapter(class1);

        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(class2);

        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView3.setAdapter(class3);

        recyclerView4.setLayoutManager(linearLayoutManager4);
        recyclerView4.setAdapter(class4);

        recyclerView5.setLayoutManager(linearLayoutManager5);
        recyclerView5.setAdapter(class5);

        recyclerView6.setLayoutManager(linearLayoutManager6);
        recyclerView6.setAdapter(class6);

        recyclerView7.setLayoutManager(linearLayoutManager7);
        recyclerView7.setAdapter(class7);

        recyclerView8.setLayoutManager(linearLayoutManager8);
        recyclerView8.setAdapter(class8);

        recyclerView9.setLayoutManager(linearLayoutManager9);
        recyclerView9.setAdapter(class9);

        recyclerView10.setLayoutManager(linearLayoutManager10);
        recyclerView10.setAdapter(class10);

        recyclerView11.setLayoutManager(linearLayoutManager11);
        recyclerView11.setAdapter(class11);

        recyclerView12.setLayoutManager(linearLayoutManager12);
        recyclerView12.setAdapter(class12);



        return rootView;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subjectsHigh.add("English");
        subjectsHigh.add("Science");
        subjectsHigh.add("Hindi");
        subjectsHigh.add("Social Science");

        chaptersStories.add("Story1");
        chaptersStories.add("Story2");

        subjectsMid.add("English");
        subjectsMid.add("Hindi");
        subjectsMid.add("E.V.S");

        subjectsPri.add("English");
        subjectsPri.add("Hindi");

        subjectsHighHigh.add("English");
        subjectsHighHigh.add("Hindi");
        subjectsHighHigh.add("Political Science");
        subjectsHighHigh.add("Economics");
        subjectsHighHigh.add("History");
        subjectsHighHigh.add("Geography");

        onNoteListenerStories = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (chaptersStories.get(position).equals("Story1")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c85da9c34ff860e396aa95651c236740");// CLASS 1 ENG
                    intent1.putExtra("class","Class 1 - English");
                } else if (chaptersStories.get(position).equals("Story2")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/e98ce18b8c2f9cb626bfa267a837a028"); // CLASS 1 HIN
                    intent1.putExtra("class","Class 1 - Hindi");
                }
                startActivity(intent1);
            }
        };

        onNoteListener1 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsPri.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c85da9c34ff860e396aa95651c236740");// CLASS 1 ENG
                    intent1.putExtra("class","Class 1 - English");
                } else if (subjectsPri.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/e98ce18b8c2f9cb626bfa267a837a028"); // CLASS 1 HIN
                    intent1.putExtra("class","Class 1 - Hindi");
                }
                startActivity(intent1);
            }
        };

        onNoteListener2 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsPri.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/082ccfea0da183211920aaa68f51db2f"); // CLASS 2 ENG
                    intent1.putExtra("class","Class 2 - English");
                } else if (subjectsPri.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/e1e0bc541a419f3656245785988f67a8"); // CLASS 2 HIN
                    intent1.putExtra("class","Class 2 - Hindi");
                }
                startActivity(intent1);
            }
        };

        onNoteListener3 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsMid.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/df1e619eb5ef929b9fc940dd2b62e629"); // CLASS 3 ENG
                    intent1.putExtra("class","Class 3 - English");
                } else if (subjectsMid.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/236e6f04f2eb41872d18ad9d8d70f0fb"); // CLASS 3 HIN
                    intent1.putExtra("class","Class 3 - Hindi");
                } else if (subjectsMid.get(position).equals("E.V.S")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c0e434a5813658ca9de0e079600dd745"); // CLASS 3 EVS
                    intent1.putExtra("class","Class 3 - E.V.S");
                }
                startActivity(intent1);
            }
        };

        onNoteListener4 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsMid.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/184fb533ba85fe20808b100733e30467"); // CLASS 4 ENG
                    intent1.putExtra("class","Class 4 - English");
                } else if (subjectsMid.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/03ebcfa459d5b5a87bece78f415ef101"); // CLASS 4 HIN
                    intent1.putExtra("class","Class 4 - Hindi");

                } else if (subjectsMid.get(position).equals("E.V.S")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/928647963fb8522d182c85eeb6908070"); // CLASS 4 EVS
                    intent1.putExtra("class","Class 4 - E.V.S");

                }
                startActivity(intent1);
            }
        };

        onNoteListener5 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsMid.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/46a16dd98c379d401392246a86c56b50"); // CLASS 5 ENG
                    intent1.putExtra("class","Class 5 - English");

                } else if (subjectsMid.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/cb5d0acc65f5e9e2356ce7c7841e0689"); // CLASS 5 HIN
                    intent1.putExtra("class","Class 5 - Hindi");

                } else if (subjectsMid.get(position).equals("E.V.S")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/4a11403e1d4ffb6bed0036ce295f251f"); // CLASS 5 EVS
                    intent1.putExtra("class","Class 5 - E.V.S");

                }
                startActivity(intent1);

            }
        };

        onNoteListener6 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsHigh.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/357c6961dc6f1497fe3136a5cfb6e819"); // CLASS 6 ENG
                    intent1.putExtra("class","Class 6 - English");

                } else if (subjectsHigh.get(position).equals("Science")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/66c363efa76647b51064b6c6bd257bb0"); // CLASS 6 SCI
                    intent1.putExtra("class","Class 6 - Science");

                } else if (subjectsHigh.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/b5253476ab0501d2647b013956a37ddb"); // CLASS 6 HIN
                    intent1.putExtra("class","Class 6 - Hindi");


                } else if (subjectsHigh.get(position).equals("Social Science")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 6 SST
                    intent1.putExtra("class","Class 6 - Social Science");


                }
                startActivity(intent1);

            }
        };

        onNoteListener7 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if (subjectsHigh.get(position).equals("English")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/9a75024013121ed2f455043e2c6002b6"); // CLASS 7 ENG
                    intent1.putExtra("class","Class 7 - English");

                } else if (subjectsHigh.get(position).equals("Science")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/471092630637950e2df99d2a0c7afbfa"); // CLASS 7 SCI
                    intent1.putExtra("class","Class 7 - Science");

                } else if (subjectsHigh.get(position).equals("Hindi")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/c51850788eb41fe3ccacd3934ae1d411"); // CLASS 7 HIN
                    intent1.putExtra("class","Class 7 - Hindi");

                } else if (subjectsHigh.get(position).equals("Social Science")) {
                    intent1.putExtra("rss", "https://www.podcasts.com/rss_feed/f975e80e229a89ee7b3d7f37ce95b58f"); // CLASS 7 SST
                    intent1.putExtra("class","Class 7 - Social Science");

                }
                startActivity(intent1);

            }
        };

        onNoteListener8 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if(subjectsHigh.get(position).equals( "English")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/0a870144e3285568b964af496aa9149d"); // CLASS 8 ENG
                    intent1.putExtra("class","Class 8 - English");
                }
                else if(subjectsHigh.get(position).equals("Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/9f162f9d43e69ce26f84dffebbb973d1"); // CLASS 8 SCI
                    intent1.putExtra("class","Class 8 - Science");
                }
                else if(subjectsHigh.get(position).equals("Hindi")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/952482e4f5a6d491f29a3a6c258851fe"); // CLASS 8 HIN
                    intent1.putExtra("class","Class 8 - Hindi");

                }
                else if(subjectsHigh.get(position).equals("Social Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/88d979304d8f8f3ffb9277f2c4d0d352"); // CLASS 8 SST
                    intent1.putExtra("class","Class 8 - Social Science");
                }
                startActivity(intent1);

            }
        };

        onNoteListener9 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if(subjectsHigh.get(position).equals( "English")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/17e402370158cc3f370e5618e56a3727"); // CLASS 9 ENG
                    intent1.putExtra("class","Class 9 - English");
                }
                else if(subjectsHigh.get(position).equals("Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/0fa5d6a86dce8ec9a21305a6e9d7ecc6"); // CLASS 9 SCI
                    intent1.putExtra("class","Class 9 - Science");
                }
                else if(subjectsHigh.get(position).equals("Hindi")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/a1500cc8abb17eeb785e009c95ac2e9c"); // CLASS 9 HIN
                    intent1.putExtra("class","Class 9 - Hindi");
                }
                else if(subjectsHigh.get(position).equals("Social Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/f57f79cd3c1591312d65121b8b1dc9f1"); // CLASS 9 SST
                    intent1.putExtra("class","Class 9 - Social Science");
                }
                startActivity(intent1);

            }
        };

        onNoteListener10 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if(subjectsHigh.get(position).equals( "English")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/cee0b224516b70db8d33fafd906098ed"); // CLASS 10 ENG
                    intent1.putExtra("class","Class 10 - English");
                }
                else if(subjectsHigh.get(position).equals("Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/c6cb48db0b24472949d3647ac255bd89"); // CLASS 10 SCI
                    intent1.putExtra("class","Class 10 - Science");
                }
                else if(subjectsHigh.get(position).equals("Hindi")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/45f27509f75065a401b149a488aac751"); // CLASS 10 HIN
                    intent1.putExtra("class","Class 10 - Hindi");
                }
                else if(subjectsHigh.get(position).equals("Social Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/95126300095e18ea421acc593a0fd564"); // CLASS 10 SST
                    intent1.putExtra("class","Class 10 - Social Science");
                }
                startActivity(intent1);

            }
        };

        onNoteListener11 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if(subjectsHighHigh.get(position).equals( "English")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/50e113399776b65636dcba0a97efa77e"); // CLASS 11 ENG
                    intent1.putExtra("class","Class 11 - English");
                }
                else if(subjectsHighHigh.get(position).equals("Political Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/1608484741a4709a689112187856ebd1"); // CLASS 11 POL
                    intent1.putExtra("class","Class 11 - Political Science");
                }
                else if(subjectsHighHigh.get(position).equals("Hindi")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/73c3923626434d52e59dfcd77fa0a658"); // CLASS 11 HIN
                    intent1.putExtra("class","Class 11 - Hindi");
                }
                else if(subjectsHighHigh.get(position).equals("Economics")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/56ec60b777ea8dd7a81efd6241c9a8f1"); // CLASS 11 ECO
                    intent1.putExtra("class","Class 11 - Economics");
                }
                else if(subjectsHighHigh.get(position).equals("History")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/b28a15ce619e3fc344e80d2dbd316dfd"); // CLASS 11 HIS
                    intent1.putExtra("class","Class 11 - History");
                }
                else if(subjectsHighHigh.get(position).equals("Geography")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/106e6567aa8bd3cecf4080670b11bd10"); // CLASS 11 GEO
                    intent1.putExtra("class","Class 11 - Geography");
                }
                startActivity(intent1);

            }
        };

        onNoteListener12 = new ClassSubAdapter12.OnNoteListener() {
            @Override
            public void OnNoteClick(int position) {
                Intent intent1 = new Intent(getActivity(), Audios.class);
                if(subjectsHighHigh.get(position).equals( "English")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/39b9ad5c1812c50323ea1e414f533c17"); // CLASS 12 ENG
                    intent1.putExtra("class","Class 12 - English");
                }
                else if(subjectsHighHigh.get(position).equals("Political Science")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/826688b8e7f1a3173c108b16f4627a2c"); // CLASS 12 POL
                    intent1.putExtra("class","Class 12 - Political Science");
                }
                else if(subjectsHighHigh.get(position).equals("Hindi")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/ed57d5a221ce51d75c9f3e9c3b2a89cc"); // CLASS 12 HIN
                    intent1.putExtra("class","Class 12 - Hindi");
                }
                else if(subjectsHighHigh.get(position).equals("Economics")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/989bd06090abcd5e84fc9234b1061c03"); // CLASS 12 ECO
                    intent1.putExtra("class","Class 12 - Economics");
                }
                else if(subjectsHighHigh.get(position).equals("History")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/db22a8cf5cbbca5bb3360c277c605ebb"); // CLASS 12 HIS
                    intent1.putExtra("class","Class 12 - History");
                }
                else if(subjectsHighHigh.get(position).equals("Geography")){
                    intent1.putExtra("rss","https://www.podcasts.com/rss_feed/e85c970df2560a794799401fcb736b71"); // CLASS 12 GEO
                    intent1.putExtra("class","Class 12 - Geography");
                }
                startActivity(intent1);

            }
        };

    }

    @Override
    public void onDestroy() {

        subjectsPri.clear();
        subjectsMid.clear();
        subjectsHigh.clear();
        subjectsHighHigh.clear();

        super.onDestroy();
    }

    @Override
    public void OnNoteClick(int position){

    }







}
