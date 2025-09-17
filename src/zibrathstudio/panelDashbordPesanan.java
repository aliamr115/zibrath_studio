/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package zibrathstudio;

import zibrathstudio.dashboard;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class panelDashbordPesanan extends javax.swing.JPanel {

    /**
     * Creates new form panelDashbordPesanan
     */
    DefaultTableModel model = new DefaultTableModel();
    
    public panelDashbordPesanan() {
        initComponents();
        no_pesanan();
        tbPesanan.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Produk");
        model.addColumn("Harga");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        no_customer();
        
    }


    public void no_pesanan() {
        try {
            tNoPesanan.setEditable(false);
            Connection conn = koneksi.konek();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id_pesanan) AS max_pesanan FROM pesanan");

            if (rs.next()) {
                String maxPesanan = rs.getString("max_pesanan");
                if (maxPesanan == null) {
                    tNoPesanan.setText("PS01");
                } else {
                    int urutan = Integer.parseInt(maxPesanan.substring(2)) + 1;
                    tNoPesanan.setText("PS" + String.format("%02d", urutan));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void no_customer() {
        try {
            Connection conn = koneksi.konek();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id_pelanggan) AS max_pesanan FROM pelanggan");

            if (rs.next()) {
                String maxPesanan = rs.getString("max_pesanan");
                if (maxPesanan == null) {
                    tCus.setText("01");
                } else {
                    int urutan = Integer.parseInt(maxPesanan) + 1;
                    tCus.setText(String.valueOf(urutan));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    void tambah_pesanan(){
        String noPesanan = tNoPesanan.getText();
        int Idcus = Integer.parseInt(tCus.getText());
        String namaCus = tNamaPembeli.getText();
        String nomor = tNoWhatsapp.getText();
        String IdUser = "1";
        String status = "Belum Bayar";
        String deskripsi = tDeskripsi.getText();
        int harga = Integer.parseInt(totalHarga.getText().replace(".", "").substring(2));
        Date utilDate = tTanggal.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try {
            String sqluser = "INSERT INTO `pelanggan`(`id_pelanggan`, `nama_pelanggan`, `no_wa`) VALUES (?,?,?)";
            Connection conn = koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sqluser);
            ps.setInt(1, Idcus);
            ps.setString(2, namaCus);
            ps.setString(3, nomor);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException sQLException) {
            System.out.println(sQLException);
        }
        
        try {
            String sql = "INSERT INTO `pesanan`(`id_pesanan`, `id_user`, `id_pelanggan`, `deskripsi_pesanan`, `status_pesanan`, `total_harga`, `deadline`) "
                    + "VALUES (?,?,?,?,?,?,?)";
            Connection conn = koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, noPesanan);
            ps.setString(2, IdUser);
            ps.setInt(3, Idcus);
            ps.setString(4, deskripsi);
            ps.setString(5, status);
            ps.setInt(6, harga);
            ps.setDate(7, sqlDate);
            ps.executeUpdate();
            ps.close();            
            
        } catch (SQLException sQLException) {
            System.out.println(sQLException);
        }

            for (int i = 0; i < tbPesanan.getRowCount(); i++) {
        int id = Integer.parseInt(tbPesanan.getValueAt(i, 0).toString());
        int qty = Integer.parseInt(tbPesanan.getValueAt(i, 3).toString());
        int subtotal = Integer.parseInt(tbPesanan.getValueAt(i, 4).toString());
        
        try {
            String sql = "INSERT INTO `rincian_pesanan`(`id_pesanan`, `id_produk`, `qty`, `subtotal`) VALUES (?,?,?,?)";
            Connection conn = koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, noPesanan);
            ps.setInt(2, id);
            ps.setInt(3, qty);
            ps.setInt(4, subtotal);
            ps.executeUpdate();
            ps.close();            
            
        } catch (SQLException sQLException) {
            System.out.println(sQLException);
        } 
            }
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tCus = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        bb = new javax.swing.JLabel();
        aaa = new javax.swing.JLabel();
        tNoPesanan = new javax.swing.JTextField();
        tNamaPembeli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tTanggal = new com.toedter.calendar.JDateChooser();
        cc = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();
        tNoWhatsapp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tDeskripsi = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Nomor = new javax.swing.JLabel();
        lbNamaPembeli = new javax.swing.JLabel();
        lbNoWhatsapp = new javax.swing.JLabel();
        lbTanggal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        totalHarga = new javax.swing.JLabel();
        btnBuatPesanan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPesanan = new javax.swing.JTable();
        lbTanggal1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bTambah = new javax.swing.JButton();
        tQty = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tIdProduk = new javax.swing.JTextField();
        tNamaProduk = new javax.swing.JTextField();
        lbHarga = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        tCus.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(tCus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tCus))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        bb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bb.setForeground(new java.awt.Color(255, 255, 255));
        bb.setText("No Whatsapp");

        aaa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aaa.setForeground(new java.awt.Color(255, 255, 255));
        aaa.setText("Nama Pembeli");

        tNoPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNoPesananActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Deskripsi");

        cc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cc.setForeground(new java.awt.Color(255, 255, 255));
        cc.setText("No Pesanan");

        btnKonfirmasi.setBackground(new java.awt.Color(255, 102, 0));
        btnKonfirmasi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setText("Konfirmasi");
        btnKonfirmasi.setBorderPainted(false);
        btnKonfirmasi.setFocusPainted(false);
        btnKonfirmasi.setFocusable(false);
        btnKonfirmasi.setOpaque(true);
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Deadline");

        tDeskripsi.setColumns(20);
        tDeskripsi.setRows(5);
        jScrollPane4.setViewportView(tDeskripsi);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(cc, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bb, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aaa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNamaPembeli)
                    .addComponent(tNoWhatsapp)
                    .addComponent(tNoPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(tTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnKonfirmasi)
                        .addGap(76, 76, 76))
                    .addComponent(jScrollPane4))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(aaa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNoWhatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNoPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnKonfirmasi)
                .addGap(12, 12, 12))
        );

        jScrollPane3.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rincian Pesanan");

        jPanel4.setBackground(new java.awt.Color(255, 102, 0));

        Nomor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Nomor.setForeground(new java.awt.Color(255, 255, 255));
        Nomor.setText("00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Nomor)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Nomor)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lbNamaPembeli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNamaPembeli.setForeground(new java.awt.Color(255, 255, 255));
        lbNamaPembeli.setText("-");

        lbNoWhatsapp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNoWhatsapp.setForeground(new java.awt.Color(255, 255, 255));
        lbNoWhatsapp.setText("-");

        lbTanggal.setForeground(new java.awt.Color(255, 255, 255));
        lbTanggal.setText("-");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total");

        totalHarga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalHarga.setForeground(new java.awt.Color(255, 255, 255));
        totalHarga.setText("Rp.0");

        btnBuatPesanan.setBackground(new java.awt.Color(255, 102, 0));
        btnBuatPesanan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuatPesanan.setForeground(new java.awt.Color(255, 255, 255));
        btnBuatPesanan.setText("Buat Pesanan");
        btnBuatPesanan.setBorderPainted(false);
        btnBuatPesanan.setFocusPainted(false);
        btnBuatPesanan.setFocusable(false);
        btnBuatPesanan.setOpaque(true);
        btnBuatPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuatPesananActionPerformed(evt);
            }
        });

        tbPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbPesanan);

        lbTanggal1.setForeground(new java.awt.Color(255, 255, 255));
        lbTanggal1.setText("Deadline:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNoWhatsapp, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                            .addComponent(jLabel10)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnBuatPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbTanggal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbTanggal1)))))
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(lbNoWhatsapp))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbTanggal1)
                        .addGap(8, 8, 8)
                        .addComponent(lbTanggal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuatPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id Produk");

        bTambah.setBackground(new java.awt.Color(255, 102, 0));
        bTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bTambah.setForeground(new java.awt.Color(255, 255, 255));
        bTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zibrathstudio/gambar/icons8-plus-20.png"))); // NOI18N
        bTambah.setText("Tambah Produk");
        bTambah.setBorderPainted(false);
        bTambah.setFocusPainted(false);
        bTambah.setFocusable(false);
        bTambah.setOpaque(true);
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Qty");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Produk");

        tIdProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tIdProdukMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(bTambah, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(tQty, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(tIdProduk)
                    .addComponent(tNamaProduk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(tIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(tNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bTambah)
                .addGap(104, 104, 104))
        );

        lbHarga.setText("jLabel7");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TAMBAH PESANAN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lbHarga))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(53, 53, 53)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(lbHarga)
                .addGap(0, 2, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuatPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuatPesananActionPerformed
        tambah_pesanan();
        dashboard.content.removeAll();
        dashboard.content.add(new panelPesanan());
        dashboard.content.repaint();
        dashboard.content.revalidate();
    }//GEN-LAST:event_btnBuatPesananActionPerformed

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed

        String nama = tNamaPembeli.getText();
        String nomor = tNoWhatsapp.getText();
        String id = tNoPesanan.getText();
        String no = id.substring(2);
        Date deadline = tTanggal.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("d EEEE yyyy"); // tanpa leading zero di tanggal
        String tgldeadline = sdf.format(deadline);
        
        if (nama.isBlank() && nomor.isBlank()) {
            JOptionPane.showMessageDialog(null, "Harap Masukkan data", "Warning", 1);
        } else {
            Nomor.setText(no);
            lbNamaPembeli.setText(nama);
            lbNoWhatsapp.setText(nomor);
            tNamaPembeli.setEditable(false);
            tNoWhatsapp.setEditable(false);
            lbTanggal.setText(tgldeadline);
        }
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void tNoPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNoPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNoPesananActionPerformed

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        String ID = tIdProduk.getText();
        String nama = tNamaProduk.getText();
        Double harga = Double.parseDouble(lbHarga.getText());
        int qty = Integer.parseInt(tQty.getText());
        int subtotal = (int)(harga * qty);
        int hitung = 0;

        Object[] baris = {ID,nama, String.valueOf(harga),String.valueOf(qty), String.valueOf(subtotal)};
        model.addRow(baris);
        for (int i = 0; i < tbPesanan.getRowCount(); i++) {
            hitung = hitung + Integer.parseInt(tbPesanan.getValueAt(i, 4).toString());
        }
        String total = Integer.toString(hitung);
        double Rp = Double.parseDouble(total);
        DecimalFormat df = new DecimalFormat("#,###,###");
        totalHarga.setText("Rp." + df.format(Rp));
        tIdProduk.setText(null);
        tNamaProduk.setText(null);
        lbHarga.setText(null);
        tQty.setText(null);
    }//GEN-LAST:event_bTambahActionPerformed

    private void tIdProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tIdProdukMouseClicked
        boolean close = true;
        DataBarang db = new DataBarang(null, close);
        db.setTitle("Tambah Barang");
        db.setVisible(true);
    }//GEN-LAST:event_tIdProdukMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nomor;
    private javax.swing.JLabel aaa;
    private javax.swing.JButton bTambah;
    private javax.swing.JLabel bb;
    private javax.swing.JButton btnBuatPesanan;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JLabel cc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JLabel lbHarga;
    private javax.swing.JLabel lbNamaPembeli;
    private javax.swing.JLabel lbNoWhatsapp;
    private javax.swing.JLabel lbTanggal;
    private javax.swing.JLabel lbTanggal1;
    private javax.swing.JLabel tCus;
    private javax.swing.JTextArea tDeskripsi;
    public static javax.swing.JTextField tIdProduk;
    private javax.swing.JTextField tNamaPembeli;
    public static javax.swing.JTextField tNamaProduk;
    private javax.swing.JTextField tNoPesanan;
    private javax.swing.JTextField tNoWhatsapp;
    private javax.swing.JTextField tQty;
    private com.toedter.calendar.JDateChooser tTanggal;
    private javax.swing.JTable tbPesanan;
    private javax.swing.JLabel totalHarga;
    // End of variables declaration//GEN-END:variables
}
