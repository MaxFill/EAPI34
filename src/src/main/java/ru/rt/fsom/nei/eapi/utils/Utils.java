package ru.rt.fsom.nei.eapi.utils;

import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * This class contains common methods needed by a NEI.
 *
 * @author Maksim Filatov
 * @version 1.0
 * @since jdk1.6
 */
public final class Utils {

    private Utils() {
    }

    public static String getCurrentDateAsString(){
	    Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    return dateFormat.format(date);
    }

    public static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
    }

    public static String makeRequestId(){
	    Random rand = new Random();
	    int n = rand.nextInt(100000);
	    return String.valueOf(n);
    }

    public static void printArglistParams(boolean isDebug, String title, Arglist arglist, MacroSet macro){
	    if (!isDebug) return;
	    macro.printDebug(title);
	    Enumeration<String> keys = arglist.getKeys();
	    while (keys.hasMoreElements()) {
		    String paramName = keys.nextElement();
		    String value = arglist.getArg(paramName);
		    macro.printDebug(" arglist param: " + paramName + " =\t" + value);
	    }
    }

    public static void printDebug(boolean isDebug, String msg, MacroSet macro){
	    if (isDebug) macro.printDebug(msg);
    }

    /**
     * Read parameter
     *
     * @param paramName name of the task parameter to be read
     * @param argList
     * @param macro a reference to the NEI
     * @return paramValue task parameter value
     * @throws java.lang.NoSuchFieldException
     */
    public static String readParam(String paramName, Arglist argList, MacroSet macro) throws NoSuchFieldException {
	    String paramValue = argList.getArg(paramName);
	    if (hasMissingParam(paramValue, paramName, macro)) {
		    throw new NoSuchFieldException("Parameter = " + paramName);
	    }
	    return paramValue;
    }

    /**
     * Validate if given value is integer
     *
     * @param value value to validate
     * @param macro a reference to the NEI
     * @throws NumberFormatException
     */
    public static void isInteger(String value, MacroSet macro) throws NumberFormatException {
	    try {
		    Integer.parseInt(value);
	    } catch (NumberFormatException e) {
		    macro.printDebug("Error format! [" + value + "] is not integer.");
		    throw e;
	    }
    }

    /**
     * Format message by replacing \n and \t with an empty space
     *
     * @param inputMsg input message
     * @return formatted message
     */
    public static String formatMessage(String inputMsg) {
	    return inputMsg.replaceAll("\n", " ").replaceAll("\t", " ");
    }

    /**
     * Check if parameter and value is missing
     *
     * @param paramValue parameter value
     * @param paramName parameter name
     * @param macro a reference to the NEI
     * @return boolean true or false
     */
    private static boolean hasMissingParam(String paramValue, String paramName, MacroSet macro) {
	    if (paramValue == null || paramValue.equals("")) {
		    macro.printDebug("Error incorrect parameter = [" + paramName + "]");
		    return true;
	    }
	    return false;
    }

    /**
     * Convert string value to Integer
     *
     * @param value parameter value
     * @return Integer
     * @throws Exception
     */
    public static Integer toInt(String value) throws Exception {
	    if (value == null) return null;
	    try {
		    return Integer.valueOf(value);
	    } catch(NumberFormatException e) {
		    throw new Exception("Unable to convert value " + value + " to Integer");
	    }
    }

    /**
     * Convert string value to Long
     *
     * @param value parameter value
     * @return Long
     * @throws Exception
     */
    public static Long toLong(String value) throws Exception {
	    if (value == null) return null;
	    try {
		    return Long.valueOf(value);
	    } catch(NumberFormatException e) {
		    throw new Exception("Unable to convert value " + value + " to Long");
	    }
    }

    /**
     * Convert string value to BigInteger
     *
     * @param value parameter value
     * @return BigInteger
     * @throws Exception
     */
    public static BigInteger toBigInteger(String value) throws Exception {
	    if (value == null) return null;
	    try {
		    return new BigInteger(value);
	    } catch(NumberFormatException e) {
		    throw new Exception("Unable to convert value " + value + " to java.math.BigInteger");
	    }
    }

    /**
     * Convert string value to java.util.Calendar
     *
     * @param value parameter value
     * @return calendar
     * @throws Exception
     */
    public static Calendar toCalendar(String value) throws Exception {
	    if (value == null)  return null;
	    try {
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    Date date = sdf.parse(value);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    return cal;
	    } catch(NumberFormatException e) {
		    throw new Exception("Unable to convert value " + value + " to java.util.Calendar");
	    }
    }

    /**
     * Convert string value to javax.xml.datatype.XMLGregorianCalendar
     *
     * @param value parameter value
     * @return xmlGregorianCal
     * @throws Exception
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(String value) throws Exception {
	    if (value == null) return null;
	    try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date date = sdf.parse(value.replace("T", " "));
		    GregorianCalendar gregorianCal = new GregorianCalendar();
		    gregorianCal.setTime(date);
		    XMLGregorianCalendar xmlGregorianCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCal);
		    return xmlGregorianCal;
	    } catch(NumberFormatException ex) {
		    throw new Exception("Unable to convert value " + value + " to javax.xml.datatype.XMLGregorianCalendar");
	    }
    }

    /**
     * Converts Arraylist containing int value to be converted to int[]
     * @param arrayList
     * @return
     * @throws Exception
     */
    public static int[] convertArrayListToIntArray(ArrayList<? extends Object> arrayList) throws Exception {
	    int[] arrayInteger = new int[]{};
	    if (arrayList != null && arrayList.size() > 0) {
		    arrayInteger = new int[arrayList.size()];
		    for (int i=0; i<arrayList.size(); i++) {
			    String str = arrayList.get(i).toString();
			    arrayInteger[i] = Integer.parseInt(str);
		    }
	    }
	    return arrayInteger;
    }

    /**
     * Converts Arraylist containing double value to be converted to double[]
     * @param arrayList
     * @return
     * @throws Exception
     */
    public static double[] convertArrayListToDoubleArray(ArrayList<? extends Object> arrayList) throws Exception {
	    double[] arrayDouble = new double[]{};
	    if (arrayList != null && arrayList.size() > 0) {
		    arrayDouble = new double[arrayList.size()];
		    for (int i=0; i<arrayList.size(); i++) {
			    String str = arrayList.get(i).toString();
			    arrayDouble[i] = Double.parseDouble(str);
		    }
	    }
	    return arrayDouble;
    }

    /**
     * Converts Arraylist containing boolean value to be converted to boolean[]
     * @param arrayList
     * @return
     * @throws Exception
     */
    public static boolean[] convertArrayListToBooleanArray(ArrayList<? extends Object> arrayList) throws Exception {
	    boolean[] arrayBoolean = new boolean[]{};
	    if (arrayList != null && arrayList.size() > 0) {
		    arrayBoolean = new boolean[arrayList.size()];
		    for (int i=0; i<arrayList.size(); i++) {
			    String str = arrayList.get(i).toString();
			    arrayBoolean[i] = Boolean.parseBoolean(str);
		    }
	    }
	    return arrayBoolean;
    }

    /**
     * Converts Arraylist containing long value to be converted to long[]
     * @param arrayList
     * @return
     * @throws Exception
     */
    public static long[] convertArrayListToLongArray(ArrayList<? extends Object> arrayList) throws Exception {
	    long[] arrayLong = new long[]{};
	    if (arrayList != null && arrayList.size() > 0) {
		    arrayLong = new long[arrayList.size()];
		    for (int i=0; i<arrayList.size(); i++) {
			    String str = arrayList.get(i).toString();
			    arrayLong[i] = Long.parseLong(str);
		    }
	    }
	    return arrayLong;
    }

    /**
     * Converts Arraylist containing string value to be converted to string[]
     * @param arrayList
     * @return
     * @throws Exception
     */
    public static String[] convertArrayListToStringArray(ArrayList<? extends Object> arrayList) throws Exception {
	    String[] arrayString = new String[]{};
	    if (arrayList != null && arrayList.size() > 0) {

		    arrayString = new String[arrayList.size()];
		    for (int i=0; i<arrayList.size(); i++) {
			    String str = arrayList.get(i).toString();
			    arrayString[i] = str.toString();
		    }
	    }
	    return arrayString;
    }

    /**
     * Print xml request into a formatted and indented xml in mml log
     * @param mmlMessage
     * @param type
     * @return xmlOutput
     * @throws TransformerException
     */
    public static String formatXML(String mmlMessage, String type) throws TransformerException {
		Source xmlInput = new StreamSource(new StringReader(mmlMessage));
		StringWriter stringWriter = new StringWriter();
		StreamResult xmlOutput = new StreamResult(stringWriter);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 3);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		if (type.equals("request")) {
			//separate the header - XML-UTF8 from the xml content,
			//indentation start from the first element of the XML request
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
		} else if (type.equals("response")){
			//format the xml content only (without the header - XML-UTF8)
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		}

		transformer.transform(xmlInput, xmlOutput);
		return xmlOutput.getWriter().toString().trim();
	}
	
    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }
    
    public static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}
