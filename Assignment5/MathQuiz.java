public class MathQuiz {

    private boolean carryOver(int x, int y){
        return (x + y >= 10 && x + y < 20);
    }
    public static void main(String[] args){
        int x = (int) Math.random();
        System.out.println(x);
    }
}