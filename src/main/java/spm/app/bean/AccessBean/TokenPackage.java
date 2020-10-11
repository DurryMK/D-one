package spm.app.bean.AccessBean;

public class TokenPackage {
    private String publicKey;
    private String privateKey;
    private String original;
    private String encryption;

    public TokenPackage() {

    }

    public TokenPackage(String original) {
        this.original = original;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    @Override
    public String toString() {
        return "TokenPackage{" +
                "publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", original='" + original + '\'' +
                ", encryption='" + encryption + '\'' +
                '}';
    }
}
