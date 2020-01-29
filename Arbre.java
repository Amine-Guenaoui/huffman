public class Arbre{
    
   public Arbre fils_g = null;
   public Arbre fils_d = null;
   public int occ = 0;
   public char c ='*';  
   public int qui = 3;
    
    public Arbre(Arbre  fils_g,Arbre  fils_d, int occ, char c){
            this.fils_d = fils_d;
            this.fils_g = fils_g;
            this.occ = occ;
            this.c = c;
    }

    public Arbre(Arbre  fils_g,Arbre  fils_d){
            this.fils_d = fils_d;
            this.fils_g = fils_g;
    }

    public Arbre(char c, int occ){
            this.occ = occ;
            this.c = c;
    }

    public boolean leaf(){
            return fils_d==null && fils_g==null; 
    }

    public static Arbre merge(Arbre a1,Arbre a2){
                return new Arbre(a1,a2,a1.occ+a2.occ,'*');
    }

    public void afficherPrefix(){

        System.out.println(" Pere : char =" +c+ " occ =" + occ );
        if(fils_g != null ) {
        System.out.println(fils_g.qui);
        fils_g.afficherPrefix();
        }
        if(fils_d != null ) {
        System.out.println(fils_d.qui);
        fils_d.afficherPrefix();
        }
    }

    public int getQui(){
            return qui;
    }

    public char getChar(){
            return c;
    }


}