//package com.example.Controller;
//
//import com.example.model.HbaseHelper;
//import com.example.Services.others.PicToByte;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class test {
//    public static void main(String []args)throws Exception{
//        String tf = "gszImgTable";//table name
//        String cf ="info";   //cloumnFamily name
//        HbaseHelper hbaseHelper = new HbaseHelper("192.168.1.4",2181);
//        hbaseHelper.createTable(tf, new String[]{cf});
//        BufferedImage img = null;
//        PicToByte bs = new PicToByte();
//        String baseImage="";
//        List<File> mlist = new ArrayList<File>();
//        //File f = new File("F:\\images\\1.jpg");
//        File mFile = new File("F:\\plants\\plant\\");
//        if(mFile.exists()&&mFile.isDirectory()){
//            getAllFile(mFile,mlist);
//        }
//        for(File file2: mlist){
//            String fname = file2.getName();
//            //System.out.println(fname);
//            img = ImageIO.read(file2);
//            baseImage = bs.ImageToBase64ByLocal(img);
//            hbaseHelper.addRow(tf,fname,cf,"plant",baseImage);
//        }
//
//    }
//
//    private static void getAllFile(File mFile,List<File> mlist){
//        File[] files = mFile.listFiles();  //获取子目录
//        if(files !=null){
//            for(File file:files){
//                if(file.isDirectory()){
//                    getAllFile(file,mlist);
//                }else{
//                    String fileName = file.getName();
//                    if(fileName.endsWith(".jpg")||fileName.endsWith(".png")||fileName.endsWith(".gif")){
//                        mlist.add(file);
//                    }
//                }
//            }
//        }
//    }
//}
