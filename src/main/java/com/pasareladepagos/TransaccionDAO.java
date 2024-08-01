package com.pasareladepagos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaccionDAO {

    public void insertarTransaccion(String requestID, String processUrl) {
        String sql = "INSERT INTO transacciones (RequestID, ProcessUrl) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, requestID);
            pstmt.setString(2, processUrl);
            pstmt.executeUpdate();
            System.out.println("Transacción insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void consultarTransaccion(int id) {
        String sql = "SELECT * FROM transacciones WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("RequestID: " + rs.getString("RequestID"));
                System.out.println("ProcessUrl: " + rs.getString("ProcessUrl"));
                System.out.println("Fecha de Creación: " + rs.getTimestamp("fecha_creacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTransaccion(int id, String newRequestID, String newProcessUrl) {
        String sql = "UPDATE transacciones SET RequestID = ?, ProcessUrl = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, newRequestID);
            pstmt.setString(2, newProcessUrl);
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Transacción actualizada correctamente.");
            } else {
                System.out.println("No se encontró la transacción con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTransaccion(int id) {
        String sql = "DELETE FROM transacciones WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Transacción eliminada correctamente.");
            } else {
                System.out.println("No se encontró la transacción con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TransaccionDAO dao = new TransaccionDAO();
        dao.insertarTransaccion("3005000", "https://checkout-test.placetopay.com/spa/session/3005000/abc123");
        dao.consultarTransaccion(1); // Consulta la transacción con ID 1
        dao.actualizarTransaccion(1, "3005001", "https://checkout-test.placetopay.com/spa/session/3005001/xyz456");
        dao.eliminarTransaccion(2); // Elimina la transacción con ID 2
    }
}
