// ISpanComm.aidl
package xianchao.com.practice.processConnect;

// Declare any non-default types here with import statements

interface ISpanComm {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void printMsg(String msg);

}
