package otp;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.google.zxing.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.security.InvalidKeyException;
import java.security.Key;
import java.time.Instant;

public class GenerateOTPCode {
    public static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    private static String PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "qr" + FILE_SEPARATOR + "qrExample.png";

    public static void main (String[] args) throws UnsupportedEncodingException {
        String credential = getCredential();
        int otp = getOTPCode(credential);
        System.out.println("Codigo OTP es: " + otp);
    }

    public static String getCredential() {
        String credential = null;
        try {
            BufferedImage image = ImageIO.read(new File(PATH));
            Result result = decodeQRCode(image);
            if(result != null){
                System.out.println("Informacion del codigo QR: " + result.getText());
                credential = result.getText();
            } else {
                System.out.println("No se encontro ningun codigo QR en la imagen");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return credential;
    }

    public static Result decodeQRCode(BufferedImage image){
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            return new MultiFormatReader().decode(binaryBitmap);
        }catch (NotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static int getOTPCode(String credential) throws UnsupportedEncodingException {
        byte[] keyBytes = credential.getBytes("UTF-8");
        Key secretKey = new SecretKeySpec(keyBytes,0,keyBytes.length,"AES");
        TimeBasedOneTimePasswordGenerator otpGenerator = new TimeBasedOneTimePasswordGenerator();
        int otp = 0;
        try {
            otp = otpGenerator.generateOneTimePassword(secretKey, Instant.now());
        } catch (InvalidKeyException e){
            e.printStackTrace();
        }
        return otp;
    }
}
