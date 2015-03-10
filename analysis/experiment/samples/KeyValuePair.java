public class KeyValuePair {

    String key;
    Object value;

    public KeyValuePair (String s, Object v)
    {
        key = s;
        value = v;
    }

    public String toString ()
    {
        return "[Key=" + key + " val=" + value + "]";
    }

}
