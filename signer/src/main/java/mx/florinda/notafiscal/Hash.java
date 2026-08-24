package mx.florinda.notafiscal;

import jakarta.enterprise.context.ApplicationScoped;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class Hash {

  public String geraHash(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hashBytes = md.digest(input.getBytes());
      StringBuilder hash = new StringBuilder();
      for (byte b : hashBytes) {
        hash.append(String.format("%02x", b));
      }
      return hash.toString();
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    }
  }

}
