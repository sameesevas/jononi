package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FAQ extends AppCompatActivity {
      ArrayList<Modelclass> arrayList;
      RecyclerView recyclerView;
      Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        recyclerView=findViewById(R.id.recycler_view);
        arrayList= new ArrayList<>();

        arrayList.add(new Modelclass("Q1.Is cramping normal during pregnancy?","=> Early in pregnancy, uterine cramping can indicate normal changes of pregnancy initiated by hormonal changes; later in pregnancy, it can indicate a growing uterus. Cramping that is different from previous pregnancies, worsening cramping, or cramping associated with any vaginal bleeding may be a sign of ectopic pregnancy, threatened abortion, or missed abortion.",false));
        arrayList.add(new Modelclass("Q2.How much weight should I gain in pregnancy?","=> Most pregnant women gain between 10kg and 12.5kg (22lb to 26lb), putting on most of the weight after week 20. Much of the extra weight is due to your baby growing, but your body will also be storing fat, ready to make breast milk after your baby is born.\n" +
                "\n" +
                "Putting on too much or too little weight while you're pregnant can lead to health problems for you or your unborn baby. But don't worry, it's easy to make healthy food choices. Find out what to eat when pregnant and what foods to avoid.\n",false));
        arrayList.add(new Modelclass("Q3.I feel so tired. How can I help myself feel better?","=>Fatigue is normal and particularly marked in the first and early second trimesters. The hormonal changes associated with pregnancy that lead to massively increased cardiac output are probably responsible. By ten to twelve weeks your heart may be pumping an extra liter of blood per minute. This combined with the huge energy requirements of enlarging the uterine muscle just flat out make you tired!",false));
        arrayList.add(new Modelclass("Q4.How long should I be allowed to go past my due date?","=>Most doctors and midwives are happy for you to go a few days over your due date as long as everything seems to be okay. Many will let pregnant women go up to two weeks over.\n" +
                "\n" +
                "After 42 weeks, however, the baby’s health might be at risk. A very small number of babies die unexpectedly if they are still in the womb beyond 42 weeks of pregnancy.\n",false));
        arrayList.add(new Modelclass("Q5.When can you start to feel the baby move?","=>You can feel your baby move usually midway through your second trimester. Initially, it may feel like gentle flutters in your belly. Rest assured, your baby’s moved before, but they were too small for you to feel it. Second (or third, or fourth) time moms may notice these flutters earlier.",false));
        arrayList.add(new Modelclass("Q6.How long does it take to heal after giving birth?","=>Women typically feel better in six to eight weeks. Your doctor can help you develop a recovery plan that works for you.",false));
        arrayList.add(new Modelclass("Q7.How much bleeding is normal during pregnancy?","=>Bleeding and spotting (light bleeding) from the vagina during pregnancy are common, and it may not be a sign of a problem. In fact, up to 1 out of 4 of all pregnant women have some bleeding or spotting during their pregnancy. Tell your doctor about any bleeding you experience even if it is light so that your doctor can find out what is causing it. At any time, if you have heavy bleeding, seek medical care right away as this can be a sign of miscarriage.",false));
        arrayList.add(new Modelclass("Q8.How often should I get prenatal checkups?","=>Prenatal checkups are important appointments that you should not miss. They allow your healthcare professional to monitor your health, the development of your baby, and the potential for any complications.\n" +
                "\n" +
                "You will need to see your doctor or midwife once a month for the first 28 weeks of pregnancy. From week twenty-eight to week forty, you will need to go for checkups every two weeks. After week forty, you will need to go for checkups every week until you give birth. This is to ensure that both you and your baby are healthy, and to catch any potential problems early on.\n",false));
        arrayList.add(new Modelclass("Q9.What are the signs of labor?","=>As you get closer to your due date, you may start to experience some of the signs of labor. This can be both an exciting and nerve-wracking time for expectant mothers.\n" +
                "\n" +
                "Some of the signs that labor may be starting to include:\n" +
                "\n" +
                "Contractions\n" +
                "Water breaking\n" +
                "Low back pain\n" +
                "Bloody show\n" +
                "Nesting instinct\n" +
                "Diarrhea and nausea\n" +
                "\n" +
                "These are just some of the signs that labor may be starting. If you experience any of these, it is important to contact your healthcare professional or midwife right away. They will be able to give you specific instructions on what to do next and when you should come into the hospital or birthing center.",false));
        arrayList.add(new Modelclass("Q10.Can I exercise during pregnancy? ","=>We recommend 30-45 minutes of exercise 4-5 times a week. Walking, biking, and swimming are safe during pregnancy. Check with your health care provider before starting an exercise program.",false));

        adapter= new Adapter(arrayList,FAQ.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}