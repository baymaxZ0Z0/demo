package com.example.Services;
import com.example.model.HbaseHelper;
import com.example.util.FileLogger;
import com.example.util.Struct;
import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

//throws IOException
public class Searcher {
    public Struct[] hello(String args) throws IOException,Exception {
        String tf = "gszImgTable";//table name
        String cf ="info";   //cloumnFamily name
        HbaseHelper hbaseHelper = new HbaseHelper("192.168.1.4",2181);
        hbaseHelper.createTable(tf, new String[]{cf});
        FileLogger log = new FileLogger("/home/hadoop/gsz/search2.log");
        BufferedImage img = null;
        boolean passed = false;
            File f = new File(args);
            if (f.exists()) {
                try {
                    img = ImageIO.read(f);
                    passed = true;
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        if (!passed) {
            System.out.println("No image given as first argument.");
            System.out.println("Run \"Searcher <query image>\" to search for <query image>.");
            System.exit(1);
        }
        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("indexer")));
        ImageSearcher searcher = new GenericFastImageSearcher(28, CEDD.class);
        ImageSearchHits hits = searcher.search(img, ir);
        // searching with a Lucene document instance ...
//        ImageSearchHits hits = searcher.search(ir.document(0), ir);
        Struct[] array = new Struct[hits.length()];
       //System.out.println(hits.length());
        for (int i = 0; i < hits.length(); i++) {
            String filename = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            String fname = filename.substring(filename.lastIndexOf("\\")+1);
            log.log("fname",fname);
            System.out.println(fname);
            Result rs = hbaseHelper.getByRow(tf,fname);
            String b= Bytes.toString(rs.value());
            array[i]=new Struct(hits.score(i),b);
            //hits.score(i) + fileName;
            //sort[i].hit = hits.score(i);
        }
        return array;
    }
}
