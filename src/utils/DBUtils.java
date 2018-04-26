package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bill;
import beans.Client;
import beans.Warehouse;

public class DBUtils {
	public static List<Client> getClients(Connection con) throws SQLException {
		
		String query = "SELECT * from clients";
		PreparedStatement pstm = con.prepareStatement(query);
		ResultSet rs = pstm.executeQuery();
		
		List<Client> clientList = new ArrayList<Client>();
		try {
			while(rs.next()) {
				String name = rs.getString("cl_name");
				
				Client client = new Client();
				client.setName(name);
				clientList.add(client);				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		
		return clientList;
	}
	
	public static List<Warehouse> getWarehouses(Connection conn) throws SQLException{
		
		String query = "Select * from warehouses";
		PreparedStatement pstm = conn.prepareStatement(query);
		
		
		ResultSet rs = pstm.executeQuery();
		List<Warehouse> warehousesList = new ArrayList<Warehouse>();
		while(rs.next()) {
			
			Warehouse wh = new Warehouse();
			wh.setName(rs.getString("wh_name"));
			warehousesList.add(wh);			
		}
		
		return warehousesList;
	}
	
	public static List<Bill> getBill(Connection conn) throws SQLException{
		
		String query = "Select number, startTime, planTime, state, cl_name from assembly  JOIN clients ON assembly.id_client=clients.id_client  where startTime >= DATE_SUB(NOW(), INTERVAL 2 HOUR)";
		PreparedStatement pstm = conn.prepareStatement(query);
		ResultSet rs = pstm.executeQuery();
		
		
		List<Bill> billList = new ArrayList<Bill>();
		while(rs.next()) {
			Bill bill = new Bill();
			
			bill.setBillNumber(rs.getString("number"));
			bill.setStartTime(rs.getTimestamp("startTime"));
			bill.setPlanTime(rs.getTimestamp("planTime"));
			bill.setState(rs.getBoolean("state"));
			bill.setOrgName(rs.getString("cl_name"));
			billList.add(bill);
		}
		
		return billList;
	}
	
	public static void deleteWarehouse(Connection conn, String wh_name) throws SQLException {
		String query = "DELETE from warehouses where wh_name='"+wh_name+"'";
		
		PreparedStatement pstm = conn.prepareCall(query);
		pstm.executeUpdate();
		
	}
	
	public static void addWarehouse(Connection conn, String wh_name) throws SQLException {
		String query = "INSERT into warehouses (wh_name) values ('"+wh_name+"');";
		if(conn == null) {
			System.out.println("null!!");
		}
		else {
			PreparedStatement pstm = conn.prepareStatement(query);
			System.out.println(pstm);
			pstm.executeUpdate();
		}

	}
	
	public static void addClient(Connection conn, String clientName) throws SQLException{
		String query = "INSERT into clients (cl_name) values (?)" ;
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, clientName);
		
		pstm.executeUpdate();
		
	}
	
	public static boolean addBill(Connection conn, String clientName, String billNumber, String time,
			String[] warehouses) throws SQLException {

		if ((clientName != null && !clientName.isEmpty()) || (billNumber != null && !billNumber.isEmpty())
				|| (time != null && !time.isEmpty()) || (warehouses != null)) {

			String query;
			PreparedStatement pstm;
			ResultSet rs;

			// Check whether bill existence in database
			query = "Select * from assembly WHERE number = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, billNumber);
			rs = pstm.executeQuery();
			if (rs.first())
				return false;

			String clientString = "INSERT INTO dbassembly.clients ( cl_name )" + "SELECT * FROM(SELECT ? ) "
					+ "AS tmp WHERE NOT EXISTS( " + "SELECT cl_name FROM dbassembly.clients WHERE cl_name= ?"
					+ ") LIMIT 1;";

			String billString = "INSERT INTO dbassembly.assembly ( number, id_client, startTime, planTime ) "
					+ "values (?," + " (SELECT id_client FROM dbassembly.clients WHERE cl_name= ?)," + " now(),"
					+ " date_add(now(), INTERVAL ? minute));";

			String workString = "INSERT INTO dbassembly.wh_works (id_assembly, id_warehouse) " + "VALUES ( "
					+ "(SELECT id_assembly FROM dbassembly.assembly WHERE number = ?), "
					+ "(SELECT id_warehouse FROM dbassembly.warehouses WHERE wh_name = ?))";

			PreparedStatement pstAddClient = conn.prepareStatement(clientString);
			PreparedStatement pstAddBill = conn.prepareStatement(billString);
			PreparedStatement pstAddWork = conn.prepareStatement(workString);

			pstAddClient.setString(1, clientName);
			pstAddClient.setString(2, clientName);
			pstAddClient.executeUpdate();

			pstAddBill.setString(1, billNumber);
			pstAddBill.setString(2, clientName);
			pstAddBill.setString(3, time);
			pstAddBill.executeUpdate();

			for (String warehouse : warehouses) {
				pstAddWork.setString(1, billNumber);
				pstAddWork.setString(2, warehouse);
				pstAddWork.executeUpdate();				
			}
			return true;
		}
		System.out.println("что-то пошло не по плану");
		return false;
	}
	
	public static List<Warehouse> getListWarehouses(Connection conn) throws SQLException {
		String query = "SELECT wh_name from warehouses";
		PreparedStatement pstm = conn.prepareStatement(query);
		ResultSet rs = pstm.executeQuery();
		
		List<Warehouse> warehouseList = new ArrayList<Warehouse>();
		while(rs.next()) {
			Warehouse warehouse = new Warehouse();
			warehouse.setName(rs.getString("wh_name"));
			
			warehouseList.add(warehouse);
			
		}
		
		return warehouseList;
		
	}
	
	/**
	 * This function return list of warehouses which is included in the Bill 
	 * @param conn
	 * @param billNumberToEdit
	 * @return
	 * @throws SQLException
	 */
	public static List<Warehouse> getListWarehouses(Connection conn, String billNumberToEdit) throws SQLException{
		
		String query = "SELECT wh_name from warehouses where id_warehouse IN ("
							+ "SELECT id_warehouse from wh_works where id_assembly = ("
								+ "SELECT id_assembly from assembly where number = ?) AND NOT finished=1) ";

		
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, billNumberToEdit);
		
		ResultSet rs = pstm.executeQuery();
		
		List<Warehouse> warehouseList = new ArrayList<>();
		while(rs.next()) {
			Warehouse ws = new Warehouse();
			ws.setName(rs.getString(1));
			warehouseList.add(ws);
		}
		return warehouseList;
		
	}
	/**
	 * Confirming in DB that warehouse has finished his work 
	 * @param conn
	 * @param warehouse
	 * @param billNumber
	 * @return
	 * @throws SQLException
	 */
	public static boolean confirmWarehouseWork(Connection conn, String warehouse, String billNumber) throws SQLException {
		String query = "UPDATE wh_works SET finished=1 WHERE id_assembly=(SELECT id_assembly FROM assembly WHERE number = ?) "
				+ "AND id_warehouse=(SELECT id_warehouse FROM warehouses where wh_name =?)"; 
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, billNumber);
		pstm.setString(2, warehouse);
		
		pstm.executeUpdate();
		
		isBillFinishedFully(billNumber, conn);
		
		return false;
	}
	
	private static void isBillFinishedFully(String billNumber, Connection conn) throws SQLException{
		List<Warehouse> warehouseList = getListWarehouses(conn, billNumber);
		if(warehouseList.isEmpty()) {
			String query = "UPDATE assembly SET state=1 where id_assembly=(SELECT id_assembly WHERE number=?)";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, billNumber);
			pstm.executeUpdate();
		}
	}
	
	public static void changeClientProperties(Connection conn, String oldName, String newName) throws SQLException {
		String query = "UPDATE clients SET cl_name= ? where cl_name = ?";
		PreparedStatement pstm = conn.prepareStatement(query);
		
		pstm.setString(1, newName);
		pstm.setString(2, oldName);
		
		pstm.executeUpdate();
		
	}
}
