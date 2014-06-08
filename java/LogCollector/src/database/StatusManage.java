package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import storage.Statusunit;

public class StatusManage implements Isqltool {

	private static Logger logger = Logger.getLogger(StatusManage.class);
	@Override
	public boolean add(Object item) {
		if(item instanceof Statusunit){
			Connection conn =  (Connection) SQLconnect.getConnection();
			String sql = "INSERT INTO `GlusterMonitor`.`gm_status` (`id`, `hostname`, `ipofib`, " +
				"`cpuuseratio`, `memtotal`, `usedmem`, `netsendflow`, " +
				"`netreceiveflow`, `ibsendflow`, `ibreceiveflow`)" +
				" VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
			Statusunit unit = (Statusunit) item;
			sql = sql.format(sql, unit.getDate(),unit.getHostname(),unit.getIpofib(),unit.getCpuuseratio(),unit.getMemtotal(),unit.getUsedmem()
					,unit.getNetsendflow(),unit.getNetreceiveflow(),unit.getIbsendflow(),unit.getIbreceiveflow());
			try{
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
//			logger.info("使用的sql语句 " + sql);
            int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  
            logger.info("insert " + count + " entries"); //输出插入操作的处理结果  
            conn.close();   //关闭数据库连接  
            return true;
        } catch (SQLException e) {  
            System.out.println("插入数据失败 " + e.getMessage());  
            return false;
        }
		}
		return false;
	}

	@Override
	public ArrayList<Object> show(String date,String hostname,boolean flag) {
		ArrayList<Object> result = new ArrayList<Object>();
		Connection conn =  (Connection) SQLconnect.getConnection();
		String sql = "SELECT * FROM GlusterMonitor.gm_status where id like '" + date + "%' and hostname = '"+ hostname + "'";
		if(flag == true){
			sql += "order by(id) desc limit 0,1";
		}
		logger.info("sql " + sql);
		try{
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
			ResultSet rs = st.executeQuery(sql); // 执行查询操作的sql语句，并返回插入数据的个数
			while (rs.next()) {
				String id = rs.getString("id");
				String hostname_ = rs.getString("hostname");
				String ipofib = rs.getString("ipofib");
				String cpuuseratio = rs.getString("cpuuseratio");
				String memtotal = rs.getString("memtotal");
				String usedmem = rs.getString("usedmem");
				String netsendflow = rs.getString("netsendflow");
				String netreceiveflow = rs.getString("netreceiveflow");
				String ibsendflow = rs.getString("ibsendflow");
				String ibreceiveflow = rs.getString("ibreceiveflow");
				Statusunit unit = new Statusunit(id, hostname_,ipofib,cpuuseratio, memtotal,usedmem,netsendflow,netreceiveflow,
						ibsendflow,ibreceiveflow);
				result.add(unit);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
