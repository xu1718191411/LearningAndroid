package com.example.syoui.imagetab;

import android.graphics.Bitmap;

/**
 * Created by syoui on 2017/07/07.
 */

public class IconListItem {

    private Bitmap mThumbnail = null;
    private String mTitle = null;
    private String imageName = null;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {

        return imageName;
    }



    /**
     * 空のコンストラクタ
     */
    public IconListItem() {};

    /**
     * コンストラクタ
     * @param thumbnail サムネイル画像
     * @param title タイトル
     */
    public IconListItem(String _imageName,String title) {
        mTitle = title;
        imageName = _imageName;
    }

    /**
     * サムネイル画像を設定
     * @param thumbnail サムネイル画像
     */
    public void setThumbnail(Bitmap thumbnail) {
        mThumbnail = thumbnail;
    }

    /**
     * タイトルを設定
     * @param title タイトル
     */
    public void setmTitle(String title) {
        mTitle = title;
    }

    /**
     * サムネイル画像を取得
     * @return サムネイル画像
     */
    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    /**
     * タイトルを取得
     * @return タイトル
     */
    public String getTitle() {
        return mTitle;
    }


}
