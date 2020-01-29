public class CharCode{

    char x;
    String code="-";

    public CharCode(char x,String code){
        this.x=x;
        this.code=code;

    }

    public void encode(char x,String code){
        if(this.x==x)
        this.code+=code;
    }

    public void Show(){
        System.out.println("Char = "+ this.x +";Code" + this.code);
    }


}