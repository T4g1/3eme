package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Permet de signer et vérifier des données
 */
public class Sign {
    private static SecretKey RSAkey;         // Clé pour le chiffrmeent des message reuce par chiffrement asymétrique
    
    public static SecretKey getKey() {
        return RSAkey;
    }
    
    public static void setKey(byte[] key) {
        RSAkey = new SecretKeySpec(key, 0, key.length, "DES");
    }
    
    public static byte[] sign(byte[] dataToSign)
    {
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(
                    new FileInputStream("E:\\Users\\T4g1\\Projets\\3eme\\Java\\Etape 3\\keystore\\java_keystore.jks"),
                    "azerty".toCharArray()
            );

            // Récupére les clés
            PrivateKey privateKey = (PrivateKey)ks.getKey("denys", "azerty".toCharArray());

            Signature s = Signature.getInstance("SHA1withRSA", "BC");
            s.initSign(privateKey);
            s.update(dataToSign);
            return s.sign();
        } catch (SignatureException ex) {
            System.out.println("Signature invalide");
        } catch (InvalidKeyException ex) {
            System.out.println("Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Le provider n'existe pas");
        } catch (UnrecoverableKeyException ex) {
            System.out.println("Impossible de recupere la cle");
        } catch (KeyStoreException ex) {
            System.out.println("Erreur de KeyStore");
        } catch (FileNotFoundException ex) {
            System.out.println("Certificat introuvable");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
        } catch (IOException ex) {
            System.out.println("Impossible de lire le fichier");
        } catch (CertificateException ex) {
            System.out.println("Certificat invalide");
        }
        
        return null;
    }

    public static boolean verify(byte[] dataSigned, byte[] sigToVerify) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(
                    new FileInputStream("E:\\Users\\T4g1\\Projets\\3eme\\Java\\Etape 3\\keystore\\carteid_keystore.p12"),
                    "azerty".toCharArray()
            );

            X509Certificate certif = (X509Certificate)ks.getCertificate("denys");
            PublicKey publicKey = certif.getPublicKey();
            
            Signature s = Signature.getInstance("SHA1withRSA", "BC");
            s.initVerify(publicKey);
            s.update(dataSigned);
            return s.verify(sigToVerify);
        } catch (SignatureException ex) {
            System.out.println("Signature invalide");
        } catch (InvalidKeyException ex) {
            System.out.println("Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Le provider n'existe pas");
        } catch (KeyStoreException ex) {
            System.out.println("Erreur de KeyStore");
        } catch (FileNotFoundException ex) {
            System.out.println("Certificat introuvable");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
        } catch (IOException ex) {
            System.out.println("Impossible de lire le fichier");
        } catch (CertificateException ex) {
            System.out.println("Certificat invalide");
        }
        
        return false;
    }
    
    /**
     * Renvois la clé encryptée
     * @return      Clé encryptée
     */
    public static byte[] getEncryptedKey() {
        try {
            // Récupére le keystore
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(
                    new FileInputStream("E:\\Users\\T4g1\\Projets\\3eme\\Java\\Etape 3\\keystore\\carteid_keystore.p12"),
                    "azerty".toCharArray()
            );

            // Récupére la clé publique
            X509Certificate certif = (X509Certificate)ks.getCertificate("denys");
            PublicKey publicKey = certif.getPublicKey();
            
            // Initialise le chiffrement
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            return cipher.doFinal(RSAkey.getEncoded());
        } catch (IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println("Impossible de chiffrer la cle");
        } catch (InvalidKeyException ex) {
            System.out.println("Sign encryptKey: Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Provider non disponnible");
        } catch (NoSuchPaddingException ex) {
            System.out.println("Padding non disponnible");
        } catch (KeyStoreException ex) {
            System.out.println("Erreur de KeyStore");
        } catch (FileNotFoundException ex) {
            System.out.println("Certificat introuvable");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
        } catch (IOException ex) {
            System.out.println("Impossible de lire le fichier");
        } catch (CertificateException ex) {
            System.out.println("Certificat invalide");
        }
        
        return null;
    }
    
    /**
     * Decrypte la clé donnée
     * @return      Clé decryptée
     */
    public static byte[] decryptKey(byte[] key) {
        try {
            // Récupére le keystore
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(
                    new FileInputStream("E:\\Users\\T4g1\\Projets\\3eme\\Java\\Etape 3\\keystore\\java_keystore.jks"),
                    "azerty".toCharArray()
            );

            // Récupére la clé privée
            PrivateKey privateKey = (PrivateKey)ks.getKey("denys", "azerty".toCharArray());
            
            // Initialise le chiffrement
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            return cipher.doFinal(key);
        } catch (IllegalBlockSizeException | BadPaddingException | CertificateException ex) {
            System.out.println("Impossible de dechiffrer la cle");
        } catch (InvalidKeyException ex) {
            System.out.println("Sign decryptKey: Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Provider non disponnible");
        } catch (NoSuchPaddingException ex) {
            System.out.println("Padding non disponnible");
        } catch (UnrecoverableKeyException ex) {
            System.out.println("Impossible de recupere la cle");
        } catch (KeyStoreException ex) {
            System.out.println("Erreur de KeyStore");
        } catch (FileNotFoundException ex) {
            System.out.println("Certificat introuvable");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
        } catch (IOException ex) {
            System.out.println("Impossible de lire le fichier");
        }
        
        return null;
    }

    public static void genKey() {
        KeyGenerator cleGen;
        try {
            cleGen = KeyGenerator.getInstance("DES", "BC");
            cleGen.init(new SecureRandom());
            RSAkey = cleGen.generateKey();
        } catch (NoSuchAlgorithmException |NoSuchProviderException ex) {
            System.out.println("Sign genKey: impossible de generer la cle");
        }
    }
}
