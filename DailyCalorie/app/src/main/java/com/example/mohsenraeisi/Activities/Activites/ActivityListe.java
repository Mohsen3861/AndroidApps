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

//import com.example.mohsenraeisi.dailycalorie.R;


public class ActivityListe extends Activity {


	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.animation3, R.anim.animation4);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activite_liste);
		EditText inputSearch = (EditText)  findViewById(R.id.editText1);
		final ListView list = (ListView) findViewById(R.id.listeactivity);


		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

			      //ajouter des elements
		HashMap<String, String> map;
		 map = new HashMap<String, String>();



		 ADDdata(listItem,map);



    final SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.alimentitem,
    new String[] {"nom","cal" }, new int[] {R.id.nomsousmenu,R.id.catcal});
    list.setAdapter(mSchedule);


    list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
			int itempos=position;
			//String itemV = (String) list.getItemAtPosition(position);
			HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(itempos);


			Intent intent = new Intent ( ActivityListe.this,TimeActivity.class);


			intent.putExtra(TimeActivity.MESSAGE_NOM, map.get("nom"));
			intent.putExtra(TimeActivity.MESSAGE_CALORIE, map.get("cal"));


			startActivity(intent);
			overridePendingTransition(R.anim.animation1,R.anim.animation2);


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



	public void AjouterActivity(ArrayList<HashMap<String, String>> listItem,HashMap<String, String> map,String nom,
			String cal ){


		  map = new HashMap<String, String>();

	        map.put("nom",nom );
	        map.put("cal",cal );

	        listItem.add(map);


	}




	public void ADDdata (ArrayList<HashMap<String, String>> listItem,HashMap<String, String> map){

        AjouterActivity(listItem,map,"Aérobic, enseignement dans une classe","354");
        AjouterActivity(listItem,map,"Aérobic, faible impact","295");
        AjouterActivity(listItem,map,"Aérobic, fort impact","413");
        AjouterActivity(listItem,map,"Aérobic, général","384");
        AjouterActivity(listItem,map,"Agriculture, mise en boules de foin, nettoyage de la grange","472");
        AjouterActivity(listItem,map,"Agriculture, pelleter les grains","325");
        AjouterActivity(listItem,map,"Agriculture, traite à la main","177");
        AjouterActivity(listItem,map,"Aquagym (aqua-aérobic)","236");
        AjouterActivity(listItem,map,"Aviron, position assise, allure lente","413");
        AjouterActivity(listItem,map,"Aviron, position assise, allure modérée","502");
        AjouterActivity(listItem,map,"Aviron, position assise, allure très vigoureuse","708");
        AjouterActivity(listItem,map,"Aviron, position assise, allure vigoureuse","561");
        AjouterActivity(listItem,map,"Badminton, en compétition","413");
        AjouterActivity(listItem,map,"Badminton, hors compétition","266");
        AjouterActivity(listItem,map,"Balle au prisonier","295");
        AjouterActivity(listItem,map,"Barman, serveur","148");
        AjouterActivity(listItem,map,"Baseball","295");
        AjouterActivity(listItem,map,"Basket-ball, arbitrage","413");
        AjouterActivity(listItem,map,"Basket-ball, en fauteuil roulant","384");
        AjouterActivity(listItem,map,"Basket-ball, en match","472");
        AjouterActivity(listItem,map,"Basket-ball, général","354");
        AjouterActivity(listItem,map,"Basket-ball, tirs aux paniers","266");
        AjouterActivity(listItem,map,"Billard","148");
        AjouterActivity(listItem,map,"Bobsleigh","413");
        AjouterActivity(listItem,map,"Bolo, jeu normal","354");
        AjouterActivity(listItem,map,"Bolo, mode compétition","590");
        AjouterActivity(listItem,map,"Bowling","177");
        AjouterActivity(listItem,map,"Boxe, entraînement (mode sparring partner)","531");
        AjouterActivity(listItem,map,"Boxe, frappant les sacs","354");
        AjouterActivity(listItem,map,"Boxe, sur le ring, général","708");
        AjouterActivity(listItem,map,"Broomball, ballon-balai, ballon sur glace","413");
        AjouterActivity(listItem,map,"Canoë-kayak, allure lente","177");
        AjouterActivity(listItem,map,"Canoë-kayak, allure modérée","413");
        AjouterActivity(listItem,map,"Canoë-kayak, en équipe, mode compétition","708");
        AjouterActivity(listItem,map,"Catch, lutte","354");
        AjouterActivity(listItem,map,"Chasse, général","295");
        AjouterActivity(listItem,map,"Club de remise en forme, exercices, général","325");
        AjouterActivity(listItem,map,"Conduire un camion (position assise)","118");
        AjouterActivity(listItem,map,"Construction, à l'extérieur, remodelage","325");
        AjouterActivity(listItem,map,"Corde à sauter, allure lente","472");
        AjouterActivity(listItem,map,"Corde à sauter, allure modérée, général","590");
        AjouterActivity(listItem,map,"Corde à sauter, allure rapide","708");
        AjouterActivity(listItem,map,"Course à pied","384");
        AjouterActivity(listItem,map,"Course à pied, 10,8 km/h","649");
        AjouterActivity(listItem,map,"Course à pied, 11,25 km/h","679");
        AjouterActivity(listItem,map,"Course à pied, 12 km/h","738");
        AjouterActivity(listItem,map,"Course à pied, 13 km/h","797");
        AjouterActivity(listItem,map,"Course à pied, 13,8 km/h","826");
        AjouterActivity(listItem,map,"Course à pied, 14,5 km/h","885");
        AjouterActivity(listItem,map,"Course à pied, 16 km/h","944");
        AjouterActivity(listItem,map,"Course à pied, 17,5 km/h","1062");
        AjouterActivity(listItem,map,"Course à pied, 8 km/h","472");
        AjouterActivity(listItem,map,"Course à pied, 9,5 km/h","590");
        AjouterActivity(listItem,map,"Course à pied, cross country","531");
        AjouterActivity(listItem,map,"Course à pied, en montant les escaliers","885");
        AjouterActivity(listItem,map,"Course à pied, général","472");
        AjouterActivity(listItem,map,"Course à pied, sur une piste, pratique en équipe","590");
        AjouterActivity(listItem,map,"Course d'orientation","531");
        AjouterActivity(listItem,map,"Courses de chevaux, galopant","472");
        AjouterActivity(listItem,map,"Cricket","295");
        AjouterActivity(listItem,map,"Croquet","148");
        AjouterActivity(listItem,map,"Crosse","472");
        AjouterActivity(listItem,map,"Cuisson ou préparation des plats","148");
        AjouterActivity(listItem,map,"Curling","236");
        AjouterActivity(listItem,map,"Cyclisme, < 16 km/h, loisirs","236");
        AjouterActivity(listItem,map,"Cyclisme, > 32 km/h, courses","944");
        AjouterActivity(listItem,map,"Cyclisme, 19 à 22 km/h, allure modérée","472");
        AjouterActivity(listItem,map,"Cyclisme, 22 à 25 km/h, allure vigoureuse","590");
        AjouterActivity(listItem,map,"Cyclisme, 25 à 30 km/h, très rapide, en course","708");
        AjouterActivity(listItem,map,"Cyclisme, de 16 à 19 km/h, allure lente","354");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, allure lente","325");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, allure modérée","413");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, allure très lente","177");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, allure très vigoureuse","738");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, allure vigoureuse","620");
        AjouterActivity(listItem,map,"Cyclisme, vélo d'appartement, général","295");
        AjouterActivity(listItem,map,"Cyclisme, VTT en montagne ou BMX","502");
        AjouterActivity(listItem,map,"Danse (aérobic, ballet ou moderne)","354");
        AjouterActivity(listItem,map,"Danse (salle de bal, lente)","177");
        AjouterActivity(listItem,map,"Danse (salle de bal, rapide)","325");
        AjouterActivity(listItem,map,"Danse de salon (en couple), lente","177");
        AjouterActivity(listItem,map,"Danse de salon (en couple), rapide","325");
        AjouterActivity(listItem,map,"Danse, général","266");
        AjouterActivity(listItem,map,"Déménager (déballant les cartons)","207");
        AjouterActivity(listItem,map,"Déplacement de meubles, dans la maison","354");
        AjouterActivity(listItem,map,"Déplacement d'objets ménagers en portant les objets","413");
        AjouterActivity(listItem,map,"Déplacement d'objets ménagers, par escalier","531");
        AjouterActivity(listItem,map,"Descendre l'escalier en marchant","177");
        AjouterActivity(listItem,map,"Dormir","37");
        AjouterActivity(listItem,map,"Entraînement en circuit, général, peu de repos","472");
        AjouterActivity(listItem,map,"Equitation, général","236");
        AjouterActivity(listItem,map,"Equitation, marche","148");
        AjouterActivity(listItem,map,"Equitation, trot","384");
        AjouterActivity(listItem,map,"Escalade, varappe, ascension de rochers","649");
        AjouterActivity(listItem,map,"Escalade, varappe, descente en rappel","472");
        AjouterActivity(listItem,map,"Escrime","354");
        AjouterActivity(listItem,map,"Etirements, hatha yoga","236");
        AjouterActivity(listItem,map,"Etirements, léger","148");
        AjouterActivity(listItem,map,"Exercices corporels, à la maison, allure légère ou modérée","266");
        AjouterActivity(listItem,map,"Exercices corporels, à la maison, allure vigoureuse","472");
        AjouterActivity(listItem,map,"Extraction du charbon (de la mine)","354");
        AjouterActivity(listItem,map,"Faire la cuisine","148");
        AjouterActivity(listItem,map,"Faire la queue (debout)","75");
        AjouterActivity(listItem,map,"Faire les courses (avec un chariot)","207");
        AjouterActivity(listItem,map,"Fléchettes (mur ou pelouse)","148");
        AjouterActivity(listItem,map,"Footbag","240");
        AjouterActivity(listItem,map,"Football (exercices, course drapeaux, général)","472");
        AjouterActivity(listItem,map,"Football, mode compétition","590");
        AjouterActivity(listItem,map,"Football, occasionnel, général","413");
        AjouterActivity(listItem,map,"Foresterie","472");
        AjouterActivity(listItem,map,"Frisbee (mode rythmé)","207");
        AjouterActivity(listItem,map,"Frisbee, général","177");
        AjouterActivity(listItem,map,"Frisbee, ultimate","480");
        AjouterActivity(listItem,map,"Golf (mini-série ou frapper au loin)","177");
        AjouterActivity(listItem,map,"Golf (tirant les clubs)","295");
        AjouterActivity(listItem,map,"Golf (transportant des clubs)","325");
        AjouterActivity(listItem,map,"Golf (utilisant le chariot)","207");
        AjouterActivity(listItem,map,"Golf, général","236");
        AjouterActivity(listItem,map,"Grattage, plâtrage, peinture, mettre du papier mural","266");
        AjouterActivity(listItem,map,"Gymnastique suédoise, maison, effort léger/modéré","210");
        AjouterActivity(listItem,map,"Gymnastique, général","236");
        AjouterActivity(listItem,map,"Haltérophilie, allure modérée","177");
        AjouterActivity(listItem,map,"Haltérophilie, allure vigoureuse","354");
        AjouterActivity(listItem,map,"Handball (jeu en équipe)","472");
        AjouterActivity(listItem,map,"Handball, général","708");
        AjouterActivity(listItem,map,"Hockey sur gazon","472");
        AjouterActivity(listItem,map,"Hockey sur glace","472");
        AjouterActivity(listItem,map,"Hors-bord","148");
        AjouterActivity(listItem,map,"Jaï-alaï (pelote basque)","720");
        AjouterActivity(listItem,map,"Jardinage, général","295");
        AjouterActivity(listItem,map,"Jeu de palet, de boulingrin","177");
        AjouterActivity(listItem,map,"Jogging, dans l'eau (aquajogging)","472");
        AjouterActivity(listItem,map,"Jogging, général","413");
        AjouterActivity(listItem,map,"Jonglage","236");
        AjouterActivity(listItem,map,"Jouer un instrument tout en marchant (ex. : parade)","236");
        AjouterActivity(listItem,map,"Judo, jujitsu","590");
        AjouterActivity(listItem,map,"Karaté","590");
        AjouterActivity(listItem,map,"Kayak","295");
        AjouterActivity(listItem,map,"Kick Boxing","590");
        AjouterActivity(listItem,map,"Kickball","420");
        AjouterActivity(listItem,map,"Laver le chien","207");
        AjouterActivity(listItem,map,"Les Mills BodyAttack","475");
        AjouterActivity(listItem,map,"Les Mills BodyBalance/BodyFlow","232");
        AjouterActivity(listItem,map,"Les Mills BodyCombat","481");
        AjouterActivity(listItem,map,"Les Mills BodyPump","325");
        AjouterActivity(listItem,map,"Les Mills BodyStep","478");
        AjouterActivity(listItem,map,"Les Mills Grit","549");
        AjouterActivity(listItem,map,"Les Mills RPM","449");
        AjouterActivity(listItem,map,"Lire (en s'asseyant)","67");
        AjouterActivity(listItem,map,"Luge","413");
        AjouterActivity(listItem,map,"Maçonnerie","413");
        AjouterActivity(listItem,map,"Marche rapide (ex. : parade militaire)","384");
        AjouterActivity(listItem,map,"Marcher/Courir, jouant avec enfant, allure modérée","236");
        AjouterActivity(listItem,map,"Marcher/Courir, jouant avec enfant, allure vigoureuse","295");
        AjouterActivity(listItem,map,"Masseur (position debout)","236");
        AjouterActivity(listItem,map,"Menuiserie, charpenterie, général","207");
        AjouterActivity(listItem,map,"Monocycle","295");
        AjouterActivity(listItem,map,"Moto-cross","236");
        AjouterActivity(listItem,map,"Motoneige","207");
        AjouterActivity(listItem,map,"Musculation","354");
        AjouterActivity(listItem,map,"Musique, batterie","236");
        AjouterActivity(listItem,map,"Musique, guitare, classique (assis)","118");
        AjouterActivity(listItem,map,"Musique, guitare, rock and roll (debout)","177");
        AjouterActivity(listItem,map,"Musique, piano, orgue, violon","148");
        AjouterActivity(listItem,map,"Musique, violoncelle, flûte, cor","118");
        AjouterActivity(listItem,map,"Natation sychronisée","472");
        AjouterActivity(listItem,map,"Natation, brasse papillon, général","660");
        AjouterActivity(listItem,map,"Natation, brasse, général","590");
        AjouterActivity(listItem,map,"Natation, dos, général","472");
        AjouterActivity(listItem,map,"Natation, laps, allure modérée","472");
        AjouterActivity(listItem,map,"Natation, laps, allure rapide","590");
        AjouterActivity(listItem,map,"Natation, papillon, général","649");
        AjouterActivity(listItem,map,"Natation, pour le loisir, général","354");
        AjouterActivity(listItem,map,"Natation, sur place, allure modérée","236");
        AjouterActivity(listItem,map,"Natation, sur place, allure rapide","590");
        AjouterActivity(listItem,map,"Nettoyage (voiture, fenêtres, etc.), lourd et vigoureux","266");
        AjouterActivity(listItem,map,"Nettoyage du garage, du trottoir","236");
        AjouterActivity(listItem,map,"Nettoyage du sol, utilisant mains et genoux","325");
        AjouterActivity(listItem,map,"Nettoyage, allure lente ou modéré","148");
        AjouterActivity(listItem,map,"Nettoyage, dans la maison, général","207");
        AjouterActivity(listItem,map,"Nettoyer les gouttières","295");
        AjouterActivity(listItem,map,"Officier de police","148");
        AjouterActivity(listItem,map,"Pansage de chevaux, intense","354");
        AjouterActivity(listItem,map,"Pansage de chevaux, lent","210");
        AjouterActivity(listItem,map,"Parachutisme","177");
        AjouterActivity(listItem,map,"Patinage sur glace, 14 km/h ou moins","325");
        AjouterActivity(listItem,map,"Patinage sur glace, allure rapide (> 14 km/h)","531");
        AjouterActivity(listItem,map,"Patinage sur glace, allure rapide (mode compétition)","885");
        AjouterActivity(listItem,map,"Patinage sur glace, général","413");
        AjouterActivity(listItem,map,"Patinage, roller","413");
        AjouterActivity(listItem,map,"Pêche dans la rivière, dans les échassiers","354");
        AjouterActivity(listItem,map,"Pêche sur bateau, position assise","148");
        AjouterActivity(listItem,map,"Pêche sur la berge des rivières, position debout","207");
        AjouterActivity(listItem,map,"Pêche, général","236");
        AjouterActivity(listItem,map,"Pêche, sur la glace, position assise","118");
        AjouterActivity(listItem,map,"Pédalo","236");
        AjouterActivity(listItem,map,"Pelleter la neige, à la main","354");
        AjouterActivity(listItem,map,"Pilates","150");
        AjouterActivity(listItem,map,"Plongée (sous-marine, tremplin ou plate-forme)","177");
        AjouterActivity(listItem,map,"Plongée en apnée (snorkeling)","295");
        AjouterActivity(listItem,map,"Plongée sous-marine, général","413");
        AjouterActivity(listItem,map,"Polo","472");
        AjouterActivity(listItem,map,"Pompes, intense","474");
        AjouterActivity(listItem,map,"Pompier en train d'éteindre une incendie","708");
        AjouterActivity(listItem,map,"Port de charges lourdes (comme des briques)","472");
        AjouterActivity(listItem,map,"Porter entre 12 et 22 kilos en montant l'escalier","472");
        AjouterActivity(listItem,map,"Porter entre 7 et 11 kilos en montant l'escalier","354");
        AjouterActivity(listItem,map,"Porter un sac à dos, général","413");
        AjouterActivity(listItem,map,"Poser ou enlever les tapis/carrelages du sol","266");
        AjouterActivity(listItem,map,"Poser un double-vitrage","295");
        AjouterActivity(listItem,map,"Pousser ou tirer une poussette avec enfant","148");
        AjouterActivity(listItem,map,"Pousser un fauteuil roulant","236");
        AjouterActivity(listItem,map,"Promenade à pied, 3 km/h, allure lente","148");
        AjouterActivity(listItem,map,"Promenade à pied, 5 km/h, allure modérée","207");
        AjouterActivity(listItem,map,"Promenade à pied, 5,5 km/h, en montée","236");
        AjouterActivity(listItem,map,"Promenade à pied, 6,5 km/h, allure très rapide","354");
        AjouterActivity(listItem,map,"Promenade à pied, à l'aide de béquilles","236");
        AjouterActivity(listItem,map,"Promenade à pied, en montant","472");
        AjouterActivity(listItem,map,"Promenade à pied, piste d'herbe","295");
        AjouterActivity(listItem,map,"Promenade à pied, transportant 1 enfant ou 7 kgs","207");
        AjouterActivity(listItem,map,"Promenader à pied, moins de 3 km/h, allure très lente","118");
        AjouterActivity(listItem,map,"Promener le chien","177");
        AjouterActivity(listItem,map,"Racketball, jeu normal","413");
        AjouterActivity(listItem,map,"Racketball, mode compétition","590");
        AjouterActivity(listItem,map,"Rafting","295");
        AjouterActivity(listItem,map,"Rameur; allure lente","207");
        AjouterActivity(listItem,map,"Rameur; allure modérée","413");
        AjouterActivity(listItem,map,"Rameur; allure rapide","502");
        AjouterActivity(listItem,map,"Rameur; allure très rapide","708");
        AjouterActivity(listItem,map,"Randonnée pédestre, cross country","354");
        AjouterActivity(listItem,map,"Raquette (sur neige)","472");
        AjouterActivity(listItem,map,"Ratisser la pelouse","236");
        AjouterActivity(listItem,map,"Redressement assis, intense","474");
        AjouterActivity(listItem,map,"Regarder la TV","45");
        AjouterActivity(listItem,map,"Remontée mécanique, général","420");
        AjouterActivity(listItem,map,"Réparation automobile","177");
        AjouterActivity(listItem,map,"Repeindre la maison à l'extérieur","295");
        AjouterActivity(listItem,map,"Rugby","590");
        AjouterActivity(listItem,map,"S'asseoir dans une classe, travail de bureau","104");
        AjouterActivity(listItem,map,"S'asseoir dans une réunion","96");
        AjouterActivity(listItem,map,"Saut à ski (montée au lieu du saut en emportant les skis)","413");
        AjouterActivity(listItem,map,"Sauts avec écart (Jumping Jack), intense","498");
        AjouterActivity(listItem,map,"Séance de jeu avec enfant, mouvements légers","148");
        AjouterActivity(listItem,map,"Skateboard","295");
        AjouterActivity(listItem,map,"Ski sur l'eau, ski nautique","354");
        AjouterActivity(listItem,map,"Ski sur neige, général","413");
        AjouterActivity(listItem,map,"Ski, en descente, allure lente","295");
        AjouterActivity(listItem,map,"Ski, en descente, allure modérée","354");
        AjouterActivity(listItem,map,"Ski, en descente, allure vigoureuse","472");
        AjouterActivity(listItem,map,"Ski, ski de fond, allure course","826");
        AjouterActivity(listItem,map,"Ski, ski de fond, allure lente","413");
        AjouterActivity(listItem,map,"Ski, ski de fond, allure modéré","472");
        AjouterActivity(listItem,map,"Ski, ski de fond, allure vigoureuse","531");
        AjouterActivity(listItem,map,"Ski, ski de fond, en montée","974");
        AjouterActivity(listItem,map,"Snowboard","372");
        AjouterActivity(listItem,map,"Softball, arbitrage","240");
        AjouterActivity(listItem,map,"Softball, lancer rapide ou lent","295");
        AjouterActivity(listItem,map,"Soins des enfants (bain, nourrir, etc.), position assise","177");
        AjouterActivity(listItem,map,"Soins des enfants (bain, nourrir, etc.), position debout","207");
        AjouterActivity(listItem,map,"Sortir la poubelle","177");
        AjouterActivity(listItem,map,"Soudage, ou travailler dans une salle de cinéma","177");
        AjouterActivity(listItem,map,"Spinning","420");
        AjouterActivity(listItem,map,"Squash","708");
        AjouterActivity(listItem,map,"Step, intense","876");
        AjouterActivity(listItem,map,"Stepper, machine simulateur d'escalier","531");
        AjouterActivity(listItem,map,"Surf (couché ou debout sur la planche)","177");
        AjouterActivity(listItem,map,"Tâches ménagères, faire le ménage, général","207");
        AjouterActivity(listItem,map,"Tae Bo","606");
        AjouterActivity(listItem,map,"Tae Kwon Do","590");
        AjouterActivity(listItem,map,"Tai chi","236");
        AjouterActivity(listItem,map,"Tennis de table, ping-pong","236");
        AjouterActivity(listItem,map,"Tennis, double","354");
        AjouterActivity(listItem,map,"Tennis, général","413");
        AjouterActivity(listItem,map,"Tennis, simple","472");
        AjouterActivity(listItem,map,"Tir à l'arc (en tant que sport et non en situation de chasse)","207");
        AjouterActivity(listItem,map,"Toilettage du cheval","354");
        AjouterActivity(listItem,map,"Tondre la pelouse, assis sur la tondeuse","148");
        AjouterActivity(listItem,map,"Tondre la pelouse, général","325");
        AjouterActivity(listItem,map,"Tractions (exercice de musculation), intense","456");
        AjouterActivity(listItem,map,"Trampoline","207");
        AjouterActivity(listItem,map,"Travail (léger) dans un bureau","89");
        AjouterActivity(listItem,map,"Travailler sur l'ordinateur","81");
        AjouterActivity(listItem,map,"Travaux d'électricité, plomberie","177");
        AjouterActivity(listItem,map,"Vélo elliptique (allure modérée)","410");
        AjouterActivity(listItem,map,"Vélo elliptique (allure vigoureuse)","485");
        AjouterActivity(listItem,map,"Voile, bateau, planche à voile, général","180");
        AjouterActivity(listItem,map,"Voile, en mode compétition","295");
        AjouterActivity(listItem,map,"Voile, planche à voile, général","177");
        AjouterActivity(listItem,map,"Volley-ball dans l'eau","177");
        AjouterActivity(listItem,map,"Volley-ball, à la plage","472");
        AjouterActivity(listItem,map,"Volley-ball, hors compétition","177");
        AjouterActivity(listItem,map,"Volley-ball, mode compétition, dans un gymnase","236");
        AjouterActivity(listItem,map,"Wallyball, général","413");
        AjouterActivity(listItem,map,"Water polo","590");
        AjouterActivity(listItem,map,"Wii baseball","210");
        AjouterActivity(listItem,map,"Wii bowling","174");
        AjouterActivity(listItem,map,"Wii boxe","324");
        AjouterActivity(listItem,map,"Wii golf","150");
        AjouterActivity(listItem,map,"Wii tennis","240");
        AjouterActivity(listItem,map,"Zumba","528");


    }
}
