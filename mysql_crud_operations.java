import java.sql .*;
class mysql_con_and_print_emp{
    public static void main(String args[]){ 
        try{ 
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/testDB","root",""); 
            Statement stmt=con.createStatement();
            ResultSet rs;
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS emp(id INT PRIMARY KEY, name VARCHAR(50), position VARCHAR(50))");
            stmt.executeUpdate("INSERT INTO emp VALUES(1, 'John Doe', 'Manager')");
            rs=stmt.executeQuery("select * from emp");
            while(rs.next()) System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            stmt.executeUpdate("UPDATE emp SET name='Jane Smith' WHERE id=1");
            rs=stmt.executeQuery("select * from emp");
            while(rs.next()) System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            stmt.executeUpdate("DELETE FROM emp WHERE id=1");
            rs=stmt.executeQuery("select * from emp");
            while(rs.next()) System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            con.close(); 
        }
        catch(Exception e){ System.out.println(e);}
    }
}
