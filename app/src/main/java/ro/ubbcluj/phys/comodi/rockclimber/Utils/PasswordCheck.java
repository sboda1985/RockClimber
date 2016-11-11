package ro.ubbcluj.phys.comodi.rockclimber.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by sboda on 11/8/16.
 */
public class PasswordCheck {


    private String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public boolean CompareHashedPassword(String passwordToHash, String salt, String hashedpassword) {
        return get_SHA_512_SecurePassword(passwordToHash, salt).equals(hashedpassword);
    }

    public boolean PasswordMatch(String TypedInPassword){

        return false;

    }
}
