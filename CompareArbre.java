import java.util.Comparator;
class CompareArbre implements Comparator<Arbre>{
 
    @Override
    public int compare(Arbre e1, Arbre e2) {
        if(e1.occ > e2.occ){
            return 1;
        } else {
            return -1;
        }
    }
}