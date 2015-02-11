package com.example.mohsenraeisi.Activities.Activites;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;



public class CategorieListe extends Activity {
 
	static ArrayList<HashMap<String, String>> listItem;
	
	
	
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.animation3, R.anim.animation4);
	}
	@Override
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aliments_categories);
		final ListView list = (ListView) findViewById(R.id.listcat);
	
	/*	
		String[] Valeurs= new String[] {"pomme","orange","pomme de terre","kebab"
				,"poisson","viand","tomate","salade vert","dinde","lapin","carrot","poivron","haricot","bannan","onion",",ail"
		};
		*/
		
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		 
		   
			        
			      
		 HashMap<String, String> map;
		 
			map = new HashMap<String, String>();
			map.put("titre", "Fast-food");
	       map.put("img", String.valueOf(R.drawable.fastfood));
	               
	          
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Boissons");
	        map.put("img",  String.valueOf(R.drawable.boissons));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Poissons");
	        map.put("img",  String.valueOf(R.drawable.poissons));
	        listItem.add(map);
   
	        map = new HashMap<String, String>();
	        map.put("titre", "Viande");
	        map.put("img",  String.valueOf(R.drawable.viandes));
	        listItem.add(map);

	        map = new HashMap<String, String>();
	        map.put("titre", "Laitages");
	        map.put("img",  String.valueOf(R.drawable.lait));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Fruits");
	        map.put("img",  String.valueOf(R.drawable.fruits));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Desserts");
	        map.put("img",  String.valueOf(R.drawable.desserts));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Epices");
	        map.put("img",  String.valueOf(R.drawable.epices));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Sucreries");
	        map.put("img",  String.valueOf(R.drawable.sucrees));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Supplements");
	        map.put("img",  String.valueOf(R.drawable.supps));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Apéritifs");
	        map.put("img",  String.valueOf(R.drawable.aperitifs));
	        listItem.add(map);
	        
	        
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Plats préparés");
	        map.put("img",  String.valueOf(R.drawable.platprepare));
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Sauces");
	        map.put("img",  String.valueOf(R.drawable.sauces));
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Corps gras");
	        map.put("img",  String.valueOf(R.drawable.corpgras));
	        listItem.add(map);
	        
	        
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Oeufs");
	        map.put("img",  String.valueOf(R.drawable.oeufs));
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("titre", "Fruits secs");
	        map.put("img",  String.valueOf(R.drawable.fruitsec));
	        listItem.add(map);


        map = new HashMap<String, String>();
        map.put("titre", "Légumes");
        map.put("img",  String.valueOf(R.drawable.legumes));
        listItem.add(map);


        map = new HashMap<String, String>();
        map.put("titre", "Céréales");
        map.put("img",  String.valueOf(R.drawable.alimentoscereales));
        listItem.add(map);


        map = new HashMap<String, String>();
        map.put("titre", "Légumes secs");
        map.put("img",  String.valueOf(R.drawable.legumessec));
        listItem.add(map);
	        
		// ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		
		EditText inputSearch = (EditText)  findViewById(R.id.editText1);
		
        final SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.categorieitem,
                new String[] { "titre","img"}, new int[] {R.id.itemname,R.id.deletebutton});
       
       
        
       list.setAdapter(mSchedule);

		
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				int itempos=position;
				//String itemV = (String) list.getItemAtPosition(position);
				HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(itempos);
			
				
				Intent intent = new Intent ( CategorieListe.this, CategorieDetailleListe.class);
				intent.putExtra(CategorieDetailleListe.MESSAGE, map.get("titre"));
				
				
				startActivity(intent);
				overridePendingTransition(R.anim.animation1,R.anim.animation2);
				 // Toast.makeText(getApplicationContext(),
	                    //  "Position :"+itempos+"  ListItem : " +itemV , Toast.LENGTH_LONG)
	                    //  .show();
			
			}
		});
		
		
		
		inputSearch.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
               
            	mSchedule.getFilter().filter(cs);  
            	
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
           
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
        });
	
		
		
	}
	

	
}

/*
 *
 *
 *http://www.topendsports.com/testing/tests/bmr-equation.htm#
 *
 *
 *BMR formula: The Harris-Benedict Equation has for a long time been the standard formula and is widely used for estimating BMR. Use the calculations below to calculate BMR, where: Wt = weight in kg, Ht = height in cm, A = age in years.
men: kcal/day = (13.75 x Wt) + (5 x Ht) - (6.76 x Age) + 66
women: kcal/day = (9.56 x Wt) + (1.85 x Ht) - (4.68 x Age) + 655

Harris-Benedict Principle: Use the following table to calculate your recommended daily calorie intake to maintain current weight.
Exercise Level	Details	Calorie Calculation (Daily Needs)
Little to no exercise	 	BMR x 1.2
Light exercise	1-3 days per week	BMR x 1.375
Moderate exercise	3-5 days per week	BMR x 1.55
Heavy exercise	6-7 days per week	BMR x 1.725
Very heavy exercise	twice per day, extra heavy workouts	BMR x 1.9

*/


