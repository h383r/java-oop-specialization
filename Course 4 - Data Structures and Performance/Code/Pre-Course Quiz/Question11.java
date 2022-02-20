public class Question11 {

    public static void main(String[] args) {

        int[] array = {20, -10, 15, -7, -8, 45};
        int sum = 0;

        for(int i = 0; i<array.length;i++) {

            if(array[i] > 0) {

                sum+=array[i];
            }
        }
        System.out.println(sum);
    }
}

