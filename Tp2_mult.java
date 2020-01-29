import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Tp2_mult {
    
    public static void main(String Arg[] ) {
        String c = "abcdefghijklmopqrstuvwxyz";
        int tab[] = new int [26]; // tableau des characteres 
        String chaine = "aabbcccdaaa";//decodage a faire 
        System.out.println("la chaine donne est : \n"+chaine );
        Arbre List[] = new Arbre[chaine.length()];
        for (int i=0 ; i< c.length() ; i++){
            int k=0;
                while( k < chaine.length()){
                     if(c.charAt(i) == chaine.charAt(k))
                        tab[i]++;
                     k++;   
                }
                // creer l'arbre associer a un character 
        }

        afficher(c,tab , 25);

        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int i=0 ; i<26 ; i++ ) {
            if(tab[i]!=0) {
                map.put(Character.toString(c.charAt(i)),tab[i]);
            }
        }
      /* tri 
        map.entrySet()
      .stream()    
      .sorted(Map.Entry.comparingByValue())    
      .forEach(System.out::println);
      */
       
       //storing sorted elements    k = char , value = occ  
       Map<String, Integer> sortedByOccu = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
      //la file des arbres 
      LinkedList<Arbre> liste= new LinkedList<Arbre>();

      /* //affichage 
       sortedByOccu.entrySet()
      .stream()        
      .forEach(System.out::println);
       *///iterating over each element
        for (Map.Entry<String,Integer> entry : sortedByOccu.entrySet())  
        {  liste.add(new Arbre(entry.getKey().charAt(0), entry.getValue()));
            Collections.sort(liste,new CompareArbre()); } 
        //sorting the list of Arbres
       // Collections.sort(liste,new CompareArbre());
        
        construire(liste);
        //showArbre(liste);
        Arbre racine = liste.pop();
        racine.c='-';
        racine.afficherPrefix();
        LinkedList<CharCode> codageListe;//vide 
        String codage="";
        LinkedList<CharCode> listeCode= new LinkedList<CharCode>();//empty
        codageListe = encryptArbre(racine,codage,listeCode);
        showCode(codageListe);
        System.out.println("le code finale est : " + getCode(listeCode));
       //+---
       //sort the new tree , until list.length is one       
        // need a function to sort out the table . and then i have to create a function to add it to a table 
        /*Arbre A1 = new Arbre('a',10);
        A1.fils_d = new Arbre('c',100);
        A1.fils_g = new Arbre('b',100);
        A1.afficherPrefix();*/


    }

    public static void afficher(String chaine,int [] tab , int taille ){

        for (int i=0 ; i< taille ; i++)
            System.out.println("["+chaine.charAt(i)+"] = "+tab[i]+"\n");
    }

    public static void construire(LinkedList<Arbre> liste){
        while(liste.size()>1) {
        Arbre f1 = liste.pop(),
              f2 = liste.pop();
              f1.qui=0;
              f2.qui=1;
              liste.add(Arbre.merge(f1,f2));
              Collections.sort(liste,new CompareArbre());// to sort the tree each time 
        }
    }

    public static void showArbre(LinkedList<Arbre> liste){
        for(Arbre e:liste){
        System.out.println(e.occ + " " + e.c);
        }
    }

    
    public static void showCode(LinkedList<CharCode> liste){
        for(CharCode e:liste){
        System.out.println(e.x + ": " + e.code);
        }
    }
    //construction du code HUFFMAN 
    public static LinkedList<CharCode> encryptArbre(Arbre racine,String code,LinkedList<CharCode> liste){
        
        if(racine.c != '-') code+=Integer.toString(racine.getQui());
        if(!racine.leaf()){
            
            encryptArbre(racine.fils_g,code,liste);    
            encryptArbre(racine.fils_d,code,liste);    
            
            System.out.println(code);
        }else{

            liste.add(new CharCode(racine.c,code));
        }
        return liste;
    }

    public static String getCode(LinkedList<CharCode> liste){
        String code="";
        for(CharCode e:liste){
        code+=e.code;
        }
        return code;
    }
    
}

