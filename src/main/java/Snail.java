import java.util.Arrays;

public class Snail {

    /**
     *
     * TODO 5
     *
     * Input: an n x n 2d array.
     * Output: a 1d array of length n^2 that contains the order of elements visited in a snail traversal of the 2d array.
     *
     * Example:
     *
     * Input:
     *
     * {
     *     {1,2,3},
     *     {4,5,6},
     *     {7,8,9}
     * }
     *
     * Output:
     *
     * {1,2,3,6,9,8,7,4,5}
     *
     * @param array2d
     * @return a 1d array containing the order of elements visited in a snail traversal of the 2d array.
     *         returns an empty array if array2d is not square.
     */
    public static int[] flattenSnail(int[][] array2d) {
        if (array2d==null || !isPerfectSquare(array2d)) return new int[]{};
        int[] res = new int[]{};
        while (array2d!=null) {
            int[] flat = new int[4*array2d.length-4];
            if (flat.length==0) {
                int[] result = new int[res.length+1];
                System.arraycopy(res, 0, result, 0, res.length);
                result[result.length-1]=array2d[0][0];
                res=result;
                break;
            }
            System.arraycopy(array2d[0], 0, flat, 0, array2d.length);
            for (int i = 0; i < array2d.length - 1; i++) {
                flat[i + array2d.length] = array2d[i + 1][array2d.length - 1];
            }
            for (int i = 0; i < array2d.length - 1; i++) {
                flat[i + array2d.length + array2d.length - 1] = array2d[array2d.length - 1][array2d.length - i - 2];
            }
            for (int i = 0; i < array2d.length - 2; i++) {
                flat[i + array2d.length + array2d.length + array2d.length - 2] = array2d[array2d.length - i - 2][0];
            }
            array2d = stripOut(array2d);
            int[] temp = new int[res.length+ flat.length];
            System.arraycopy(res, 0, temp, 0, res.length);
            System.arraycopy(flat, 0, temp, res.length, flat.length);
            res=temp;
        }
        return res;
    }
    
    private static int[][] stripOut(int[][] arr) {
        if (arr.length<=2) return null;
        int[][] res = new int[arr.length-2][arr.length-2];
        for (int i = 1; i < arr.length-1; i++) {
            System.arraycopy(arr[i], 1, res[i - 1], 0, arr.length - 1 - 1);
        }
        return res;
    }

    /**
     *
     * TODO 6
     *
     * Input: a 1d array of length n, where n is a perfect square.
     * Output: a 2d array of width == height == sqrt(n) that represents a snail.
     *
     * Example:
     *
     * Input:
     *
     * {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
     *
     * Output:
     *
     * {
     *     {1,2,3,4,5},
     *     {16,17,18,19,6},
     *     {15,24,25,20,7},
     *     {14,23,22,21,8},
     *     {13,12,11,10,9},
     * }
     *
     *
     * @param array1d
     * @return a 2d array of width == height == sqrt(n), where n is the length of the inputted array, that represents a snail.
     *         returns an empty 2d array if the length of array1d is not a perfect square.
     */
    public static int[][] makeSnail(int[] array1d) {
        if (array1d==null || !isPerfectSquare(array1d)) return new int[][]{};
        int s = (int) Math.sqrt(array1d.length);
        int[][] temp;
        if(array1d.length%2==0) temp = new int[2][2];
        else {
            temp = new int[][]{
                    new int[3],
                    new int[]{0,array1d[array1d.length-1],0},
                    new int[3]
            };
            array1d = Arrays.copyOf(array1d, array1d.length-1);
        }
        boolean done = false;
        int x = array1d.length-1;
        while (!done) {
            int max = temp.length;
            for (int i = 0; i < max-1; i++) {
                temp[1+i][0]=array1d[x--];
            }
            for (int i = 0; i < max-1; i++) {
                temp[max-1][i+1]=array1d[x--];
            }
            for (int i = 0; i < max-1; i++) {
                temp[max-i-2][max-1]=array1d[x--];
            }
            for (int i = 0; i < max-1; i++) {
                temp[0][max-i-2]=array1d[x--];
            }
            if (temp.length==s) {
                done=true;
                continue;
            }
            int[][] bigger = new int[max+2][max+2];
            for (int j = 0; j < max; j++) {
                System.arraycopy(temp[j], 0, bigger[1 + j], 1, max);
            }
            temp = bigger;
        }
        return temp;
    }

    /**
     *
     * TODO 1
     *
     * Private helper method that prints the contents of a 1d array.
     * Used mainly for debugging purposes.
     *
     * @param array1d
     */
    private static void print1dArray(int[] array1d) {
        for (int i : array1d) {
            System.out.println(i);
        }
    }

    /**
     *
     * TODO 2
     *
     * Private helper method that prints the contents of a 2d array.
     * Used mainly for debugging purposes.
     *
     * @param array2d
     */
    private static void print2dArray(int[][] array2d) {
        for (int[] a : array2d) {
            print1dArray(a);
        }
    }

    /**
     *
     * TODO 3
     *
     * Private helper method that checks to see if a 1d array is of a length that is a perfect square.
     *
     * @param array1d
     * @return
     */
    private static boolean isPerfectSquare(int[] array1d) {
        return Math.sqrt(array1d.length)-Math.floor(Math.sqrt(array1d.length))==0;
    }


    /**
     *
     * TODO 4
     *
     * Private helper method that checks to see if a 2d array is a square.
     *
     * @param array2d
     * @return
     */
    private static boolean isPerfectSquare(int[][] array2d) {
        for (int[] i : array2d) {
            if (i.length!=array2d.length) {
                return false;
            }
        }
        return true;
    }


}
