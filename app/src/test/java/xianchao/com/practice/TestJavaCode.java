package xianchao.com.practice;

import android.util.Log;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class TestJavaCode {
    public static void test() {

        System.out.println(Arrays.toString(hexToByte("168518")));
        System.out.println((byte) 0x85);

        System.out.println(Arrays.toString(hexToByte("ffffffffffffffffffffffff0401ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff500018738118000001F4004C4B40000000003C0000000000FFFFFFFFFFFFFFFFFFFF000000BE37746501FC103200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF290342FFFFFFFFFFFFFFFFFFFFFF0401")));
        System.out.println(Arrays.toString(decodeHex("ffffffffffffffffffffffff0401ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff500018738118000001F4004C4B40000000003C0000000000FFFFFFFFFFFFFFFFFFFF000000BE37746501FC103200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF290342FFFFFFFFFFFFFFFFFFFFFF0401")));
        System.out.println(Arrays.toString(hexToByteArray("ffffffffffffffffffffffff0401ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff500018738118000001F4004C4B40000000003C0000000000FFFFFFFFFFFFFFFFFFFF000000BE37746501FC103200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF290342FFFFFFFFFFFFFFFFFFFFFF0401")));
    }

    public static void quitSort(int arr[], int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        System.out.println(Arrays.toString(arr));

        while(start < end) {
            while(end > start && key <= arr[end]) {
                end --;
            }
            if(key >= arr[end]) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }

            while(start < end && arr[start] <= key) {
                start ++;
            }
            if(arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }

        if(start > low) {
            quitSort(arr, low, start-1);
        }
        if(end < high) {
            quitSort(arr, end+1, high);
        }
    }


    /**
     * hex转byte数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex) {
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte) intVal);
        }
        return ret;
    }


    private static final byte[] DIGITS = new byte['f' + 1];

    /**
     * Quickly converts a hexadecimal string to a byte array.
     */
    public static byte[] decodeHex(String hexString) {
        int length = hexString.length();

        if ((length & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }

        boolean badHex = false;
        byte[] out = new byte[length >> 1];
        for (int i = 0, j = 0; j < length; i++) {
            int c1 = hexString.charAt(j++);
            if (c1 > 'f') {
                badHex = true;
                break;
            }

            final byte d1 = DIGITS[c1];
            if (d1 == -1) {
                badHex = true;
                break;
            }

            int c2 = hexString.charAt(j++);
            if (c2 > 'f') {
                badHex = true;
                break;
            }

            final byte d2 = DIGITS[c2];
            if (d2 == -1) {
                badHex = true;
                break;
            }

            out[i] = (byte) (d1 << 4 | d2);
        }

        if (badHex) {
            throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexString);
        }

        return out;
    }


    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte1(inHex.substring(i, i + 2));
            j++;
        }
        return result;

    }


    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte1(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

}
