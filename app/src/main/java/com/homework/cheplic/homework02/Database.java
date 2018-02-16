package com.homework.cheplic.homework02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Devin on 2/6/2018.
 */

public class Database implements Serializable{
    private static Database sDatabase = new Database();
    private List<FavoritePage> mPageList;

    static Database getInstance(){
        return sDatabase;
    }

    private Database() {
        mPageList = new ArrayList<>();
    }

    static void load(File f) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        sDatabase = (Database) ois.readObject();
        ois.close();
    }

    void save(File f) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(this);
        oos.close();
    }

    void addPage(FavoritePage f){
        mPageList.add(f);
    }

    int getPageCount(){
        return mPageList.size();
    }

    FavoritePage getPage(int i){
        return mPageList.get(i);
    }

    public FavoritePage getPageViaUUID(UUID id){
        for(FavoritePage page: mPageList)
            if(page.getUUID().equals(id))
                return page;
        return null;
    }

    List<FavoritePage> getPages(){
        return Collections.unmodifiableList(mPageList);
    }

    static void createDefaultDatabase(){
        Database d = new Database();

        d.addPage(new FavoritePage("iMotorhead", "http://imotorhead.com", 0));
        d.addPage(new FavoritePage("HMMM", "http://reddit.com/r/hmmm", 0));
        d.addPage(new FavoritePage("BoneHurtingJuice", "http://reddit.com/r/bonehurtingjuice", 0));
        d.addPage(new FavoritePage("Google", "http://google.com", 0));
        d.addPage(new FavoritePage("YouTube", "https://www.youtube.com/watch?v=LZc0JPGxUK4&list=FLtKvrMeuv_nFep31gEjbGSQ&index=12", 0));
        d.addPage(new FavoritePage("Netflix", "http://netflix.com", 0));
        d.addPage(new FavoritePage("MyWestminster", "http://my.westminster.edu", 0));
        d.addPage(new FavoritePage("HumbleBundle", "http://humblebundle.com", 0));

        sDatabase = d;
    }
}
