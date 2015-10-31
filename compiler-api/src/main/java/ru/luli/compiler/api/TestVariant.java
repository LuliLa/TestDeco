package ru.luli.compiler.api;

import java.io.Serializable;

public class TestVariant implements Serializable {
    private static final long serialVersionUID = -7639651838783699706L;
    private String variantText;
    private String pathToImage;
    private String uid;

    public TestVariant(String variantText, String pathToImage, String uid) {
        this.variantText = variantText;
        this.pathToImage = pathToImage;
        this.uid = uid;
    }

    public String getVariantText() {
        return variantText;
    }

    public void setVariantText(String variantText) {
        this.variantText = variantText;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
