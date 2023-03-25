public class test {
    
    public static void main() {
        int startNumber = 13;
        int[] countSet = { 13, 14, 15 }; {}

        for (int i = startNumber; i >= 1; i--) {
            int numIndex = (startNumber - i) % 3;
            int countNumber = countSet[numIndex] - (startNumber - i) / 3 * 3;
            System.out.print(countNumber + " ");
        }
    }

}
