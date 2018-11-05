package com.example.model;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
public class HbaseHelper {
    private Connection mConnection;
    private final static String QUORUM = "hbase.zookeeper.quorum";
    private final static String PORT = "hbase.zookeeper.property.clientPort";
    private final static String MASTER = "hbase.master";
    private final static String AUTHENTICATION = "hbase.security.authentication";


    public HbaseHelper(String quorum, int port)
    {
        this(quorum, port, null, null);
    }

    public HbaseHelper(String quorum, int port, String master, String auth)
    {
        Configuration config = HBaseConfiguration.create();
        config.set(QUORUM, quorum);
        config.set(PORT, Integer.toString(port));
        try {
            mConnection = ConnectionFactory.createConnection(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 创建数据库表
    public void createTable(String tableName, String[] columnFamilies)
            throws Exception {
        // 新建一个数据库管理员
        Admin hAdmin = mConnection.getAdmin();
        TableName t = TableName.valueOf(tableName);
        if (hAdmin.tableExists(t)) {
            return;
        } else
        {
            // 新建一个 image 表的描述
            HTableDescriptor tableDesc = new HTableDescriptor(t);
            // 在描述里添加列族
            for (String columnFamily : columnFamilies) {
                tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            }
            // 根据配置好的描述建表
            hAdmin.createTable(tableDesc);
//            System.out.println("创建表成功");
            hAdmin.close();
        }
    }


    public void addRow(String tableName, String row,
                       String columnFamily, String column, String images) throws IOException{
        Table table = mConnection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(row));
        // 参数出分别：列族、列、值
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column),Bytes.toBytes(images));
        table.put(put);

    }

    public Result getByRow(String tableName,String row) throws Exception{
        Table table = mConnection.getTable(TableName.valueOf(tableName));
        Get get = new Get(row.getBytes());
        return table.get(get);
    }

}
