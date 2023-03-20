public class MathQuiz {
    private static int test = 0;
    private static boolean carryOver(int x, int y){
        return x + y >= 10;
    }
    public static void main(String[] args){
        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);
        System.out.println(a + " " + b + " " + carryOver(a, b));
        // System.out.println((int) (Math.random() * 10));
        
        String spaces = "    2";
        String[] parts = spaces.split(" ");
        for (String i:parts){
            System.out.println(i);
        }
        test += 1;
        System.out.println(test);

        int[][] arr = new int[10][2];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j<2; j++){
                arr[i][j] = i + j;
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }
    }
}