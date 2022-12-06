package Aplicacion;

import Archivos.Escritura;
import Archivos.Lectura;
import Clases.ColaDinamica;
import Clases.IMetodosRecursivos;
import Clases.PersonasArrayList;
import Clases.PilaDinamica;
import Clases.PilaEstatica;
import Clases.Personal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import javax.crypto.AEADBadTagException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fmrColectivosMapache extends javax.swing.JFrame implements IMetodosRecursivos {

    ColaDinamica c = new ColaDinamica();
    PilaDinamica p = new PilaDinamica();
    Lectura lectura;
    Lectura lecturaCombis;
    Escritura escritura;
    Escritura escrituraCombis;
    DefaultTableModel modeloRegistro;
    DefaultTableModel modeloIngreso;
    PersonasArrayList ArrayCombi;
    PersonasArrayList Arraytemp;
    private int numeroPersonas;

    public fmrColectivosMapache() {
        initComponents();

        ArrayCombi = new PersonasArrayList();
        lectura = new Lectura();
        lecturaCombis = new Lectura();
        modeloRegistro = (DefaultTableModel) tblRegistro.getModel();
        LlenarTabla();
        inicializarNoPersonas();
        MostrarEstadisticas();
        setLocationRelativeTo(null);

    }

    public void inicializarNoPersonas() {
        this.numeroPersonas = 0;
    }

    public void setNoPersonas(int noPersonas) {
        this.numeroPersonas = noPersonas;
    }

    public int getNoPersonas() {
        return numeroPersonas;
    }

    private String MostrarGeneros() {
        int contHombres = 0;
        int contMujeres = 0;
        String infoGenero = "";
        int hombres = (BuscarHombres(ObtenerGeneros(), contHombres));
        int mujeres = (BuscarMujeres(ObtenerGeneros(), contMujeres));
        infoGenero = "Numero de hombres: " + hombres + "\nNumero de mujeres: " + mujeres;
        return infoGenero;
    }

    private String MostrarTipos() {
        int cont = 0;
        String infoGenero = "";
        int alumnos = (BuscarAlumnos(ObtenerTipos(), cont));
        int docentes = (BuscarDocentes(ObtenerTipos(), cont));
        int administrativos = (BuscarAdministrativos(ObtenerTipos(), cont));
        infoGenero = "Numero de Alumnos: " + alumnos + "\n" + " Numero de docentes: " + docentes + "\n" + " Numero de administrativos: " + administrativos;
        return infoGenero;
    }

    private String MostrarSemestres() {
        int cont = 0;
//        String cadena = "1234111";
        String resultado = "";
        return resultado = contarSemestres(ObtenerSemestres(), cont, cont, cont, cont, cont, cont, cont, cont, cont);
    }

    //LEE EL ARCHIVO CON LA COLA DE PERSONAS Y COLOCA LA INFORMACIÓN EN LA TABLA
    private void LlenarTabla() {
        lectura.DefinirPath("C:/ED/ExaRegistro.txt");
        c.CopiarCola(lectura.ObtenerPersonalCola());
        LimpiarTabla();

        Queue<Personal> ptemp = (Queue<Personal>) c.getCola();
        for (Personal e : ptemp) {
            modeloRegistro.addRow(new Object[]{
                e.getTipo(), e.getMatricula(), e.getNombre(), e.getSemestre(), e.getCarrera(),
                e.getGrupo(), e.getGenero()});
        }
    }

    private void LimpiarTabla() {
        int filas = tblRegistro.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloRegistro.removeRow(0);
        }
    }

    //ESCRIBE LA COLA DE ALUMNOS
    private void Escribir() {
        escritura = new Escritura();
        escritura.DefinirPath("C:/ED/ExaRegistro.txt", false);
        Queue<Personal> ptemp = (Queue<Personal>) c.getCola();
        for (Personal e : ptemp) {
            escritura.Escribir(e.toString());
        }
        escritura.CerrarArchivo();
    }

    //USADO PARA COMPROBAR EL FUNCIONAMIENTO DEL RECORRIDO DE LA PILA DE HASHMAPS
    private void comprobar() {
        Stack<HashMap<Integer, Personal[]>> ptemp = p.getPila();
        List<HashMap<Integer, Personal[]>> aux = new ArrayList<>();
        List<String> datos = new ArrayList<>();
        Personal[] datosEstudiantes = null;
        String matriculasCombi = "";
        String mensaje = "";
        for (HashMap<Integer, Personal[]> e : ptemp) {
            aux.add(e);
        }
        for (HashMap<Integer, Personal[]> hashMap : aux) {
            for (Map.Entry<Integer, Personal[]> x : hashMap.entrySet()) {
                mensaje += x.getValue();
            }
        }
        for (HashMap<Integer, Personal[]> hashMap : aux) {
            for (Map.Entry<Integer, Personal[]> x : hashMap.entrySet()) {
                if (x.getKey() == null) {
                    matriculasCombi += "";
                } else {
                    matriculasCombi += x.getKey() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, "" + mensaje + "\ncombis:\n" + matriculasCombi);
    }

    private void mostrarJAreaCombis() {
        Stack<HashMap<Integer, Personal[]>> ptemp = p.getPila();
        List<HashMap<Integer, Personal[]>> aux = new ArrayList<>();
        String matriculasCombi = "";
        for (HashMap<Integer, Personal[]> e : ptemp) {
            aux.add(e);
        }
        for (HashMap<Integer, Personal[]> hashMap : aux) {
            for (Map.Entry<Integer, Personal[]> x : hashMap.entrySet()) {
                if (x.getKey() == null) {
                    matriculasCombi += "";
                } else {
                    String temporal = "";
                    Personal[] arraytemp = x.getValue();
                    for (int i = 0; i < arraytemp.length; i++) {
                        temporal += arraytemp[i].getNombre() + "\n";
                    }
                    matriculasCombi += "Combi: " + x.getKey() + "\n" + temporal + "\n";
                }
            }
        }

        jtextCombis.setText("");
        jtextCombis.append(matriculasCombi);
    }

    private String ObtenerGeneros() {
        String temporal = "";
        lectura.DefinirPath("C:/ED/ExaRegistro.txt");
        Queue<Personal> ptemp = (Queue<Personal>) lectura.ObtenerPersonalCola();
        for (Personal e : ptemp) {
            temporal += e.getGenero();
        }
        return temporal;
    }

    private String ObtenerTipos() {
        String temporal = "";
        lectura.DefinirPath("C:/ED/ExaRegistro.txt");
        Queue<Personal> ptemp = (Queue<Personal>) lectura.ObtenerPersonalCola();
        for (Personal e : ptemp) {
            temporal += e.getTipo();
        }
        return temporal;
    }

    public int BuscarDocentes(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("D")) {
                contador++;
            }
            return BuscarDocentes(palabra.substring(1), contador);
        }
    }

    public int BuscarAlumnos(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("A")) {
                contador++;
            }
            return BuscarAlumnos(palabra.substring(1), contador);
        }
    }

    public int BuscarAdministrativos(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("S")) {
                contador++;
            }
            return BuscarAdministrativos(palabra.substring(1), contador);
        }
    }

    private String ObtenerSemestres() {
        String temporal = "";
        lectura.DefinirPath("C:/ED/ExaRegistro.txt");
        Queue<Personal> ptemp = (Queue<Personal>) lectura.ObtenerPersonalCola();
        for (Personal e : ptemp) {
            temporal += e.getSemestre();
        }
        return temporal;
    }

    @Override
    public int BuscarHombres(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("H")) {
                contador++;
            }
            return BuscarHombres(palabra.substring(1), contador);
        }
    }

    public String contarSemestres(String cadena, int cont1, int cont2, int cont3, int cont4, int cont5, int cont6, int cont7, int cont8, int cont9) {
        if (cadena.length() == 1) {
            char aux = cadena.charAt(0);
            switch (aux) {
                case '1':
                    cont1++;
                    break;
                case '2':
                    cont2++;
                    break;
                case '3':
                    cont3++;
                    break;
                case '4':
                    cont4++;
                    break;
                case '5':
                    cont5++;
                    break;
                case '6':
                    cont6++;
                    break;
                case '7':
                    cont7++;
                    break;
                case '8':
                    cont8++;
                    break;
                case '9':
                    cont9++;
                    break;
            }
            return "Semestre 1: " + cont1 + "\n"
                    + "Semestre 2: " + cont2 + "\n"
                    + "Semestre 3: " + cont3 + "\n"
                    + "Semestre 4: " + cont4 + "\n"
                    + "Semestre 5: " + cont5 + "\n"
                    + "Semestre 6: " + cont6 + "\n"
                    + "Semestre 7: " + cont7 + "\n"
                    + "Semestre 8: " + cont8 + "\n"
                    + "Semestre 9: " + cont9 + "\n";
        } else {
            char aux = cadena.charAt(0);
            switch (aux) {
                case '1':
                    cont1++;
                    break;
                case '2':
                    cont2++;
                    break;
                case '3':
                    cont3++;
                    break;
                case '4':
                    cont4++;
                    break;
                case '5':
                    cont5++;
                    break;
                case '6':
                    cont6++;
                    break;
                case '7':
                    cont7++;
                    break;
                case '8':
                    cont8++;
                    break;
                case '9':
                    cont9++;
                    break;
            }
            return contarSemestres(cadena.substring(1), cont1, cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9);
        }
    }

    @Override
    public int ContarEstudiantes(String cadena, int contEs
    ) {
        if (cadena.length() == 0) {
            return contEs;
        } else {
            char aux = cadena.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("E")) {
                contEs++;
            }
            return ContarEstudiantes(cadena.substring(1), contEs);
        }

    }

    @Override
    public int ContarDocentes(String cadena, int contD
    ) {
        if (cadena.length() == 0) {
            return contD;
        } else {
            char aux = cadena.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("D")) {
                contD++;
            }

        }
        return ContarDocentes(cadena.substring(1), contD);
    }

    @Override
    public int ContarAdminisrativos(String cadena, int contA
    ) {
        if (cadena.length() == 0) {
            return contA;
        } else {
            char aux = cadena.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("A")) {
                contA++;
            }

        }
        return ContarAdminisrativos(cadena.substring(1), contA);
    }

    @Override
    public int sumaPares(int x
    ) {
        if (x == 0) {
            return 0;
        } else {
            if (x % 2 == 0) {
                return x + sumaPares(x - 1);
            } else {
                return sumaPares(x - 1);
            }
        }
    }

    @Override
    public int BuscarMujeres(String palabra, int contador
    ) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("M")) {
                contador++;
            }
            return BuscarMujeres(palabra.substring(1), contador);
        }
    }

    @Override
    public String Folio(String cadena, String num
    ) {
        if (cadena.length() == 0) {
            return num;
        } else {
            char aux = cadena.charAt(0);

            if (Character.isDigit(aux)) {
                num = num + aux;
            }

            return Folio(cadena.substring(1), num);
        }
    }

    private String MostrarTipoPersona() {
        return null;
    }

    private void MostrarEstadisticas() {
        jtextEstadistica.setText("");
        jtextEstadistica.setText("ESTADISTICAS:\n" + MostrarGeneros() + "\n" + MostrarSemestres() + MostrarTipos());
    }

    private String generarIdCombi() {
        String ID = String.valueOf(101);
        int auxNumero = 5;
        if (p.getUltimo() == null) {
            auxNumero = 45 + sumaPares(5);
        } else {
            HashMap<Integer, Personal[]> aux = p.getUltimo();
            for (Map.Entry<Integer, Personal[]> x : aux.entrySet()) {
                if (x.getKey() == null) {
                    auxNumero += sumaPares(auxNumero);
                } else {
                    auxNumero += (x.getKey() + sumaPares(auxNumero) + 45);
                }
            }
        }
        return ID + auxNumero;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfMatricula = new javax.swing.JTextField();
        bAgregarAlu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSemestre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfGrupo = new javax.swing.JTextField();
        tfCarrera = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistro = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtextCombis = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtextEstadistica = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfGenero = new javax.swing.JTextField();
        tfNom = new javax.swing.JTextField();
        cbAdministrativo = new javax.swing.JCheckBox();
        cbDocente = new javax.swing.JCheckBox();
        bAgregarAlu1 = new javax.swing.JButton();
        bAgregarAlu2 = new javax.swing.JButton();
        cbAlumno = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Matricula :");

        tfMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMatriculaActionPerformed(evt);
            }
        });

        bAgregarAlu.setBackground(new java.awt.Color(0, 153, 0));
        bAgregarAlu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bAgregarAlu.setForeground(new java.awt.Color(255, 255, 255));
        bAgregarAlu.setText("Agregar");
        bAgregarAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarAluActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre :");

        jLabel3.setText("Semestre : ");

        jLabel4.setText("Carrera :");

        jLabel5.setText("Grupo :");

        jLabel6.setText("Genero :");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Matricula", "Nombre", "Semestre", "Carrera", "Grupo", "Genero"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRegistro);

        jButton2.setText("SUBIR A LA COMBI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro", jPanel2);

        jtextCombis.setColumns(20);
        jtextCombis.setRows(5);
        jScrollPane3.setViewportView(jtextCombis);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Colectivos", jPanel5);

        jtextEstadistica.setColumns(20);
        jtextEstadistica.setRows(5);
        jScrollPane2.setViewportView(jtextEstadistica);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estadisticas", jPanel3);

        jPanel4.setBackground(new java.awt.Color(12, 155, 12));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setForeground(new java.awt.Color(102, 255, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Colectivos Mapache");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomActionPerformed(evt);
            }
        });

        cbAdministrativo.setText("Administrativo");
        cbAdministrativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdministrativoActionPerformed(evt);
            }
        });

        cbDocente.setText("Docente");

        bAgregarAlu1.setBackground(new java.awt.Color(0, 153, 0));
        bAgregarAlu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bAgregarAlu1.setForeground(new java.awt.Color(255, 255, 255));
        bAgregarAlu1.setText("Transporte");
        bAgregarAlu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarAlu1ActionPerformed(evt);
            }
        });

        bAgregarAlu2.setBackground(new java.awt.Color(0, 153, 0));
        bAgregarAlu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bAgregarAlu2.setForeground(new java.awt.Color(255, 255, 255));
        bAgregarAlu2.setText("Actualizar Estadisticas");
        bAgregarAlu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarAlu2ActionPerformed(evt);
            }
        });

        cbAlumno.setText("Alumno");
        cbAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNom)
                    .addComponent(tfGenero)
                    .addComponent(tfGrupo)
                    .addComponent(tfCarrera)
                    .addComponent(tfSemestre)
                    .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bAgregarAlu)
                            .addComponent(cbAdministrativo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDocente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAlumno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bAgregarAlu1)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bAgregarAlu2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(cbAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbDocente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAdministrativo)
                                .addGap(18, 18, 18)
                                .addComponent(bAgregarAlu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(87, 87, 87)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(tfSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(tfCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(tfGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAgregarAlu2))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(tfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAgregarAlu1))))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomActionPerformed
    }//GEN-LAST:event_tfNomActionPerformed

    private void bAgregarAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarAluActionPerformed

        if (cbAdministrativo.isSelected() == true && cbDocente.isSelected() == true && cbAdministrativo.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Solo seleccione un tipo de persona");
        } else {
            char tipo = 0;
            if (cbAlumno.isSelected() == true) {
                tipo = 'A';
                String matricula = tfMatricula.getText();
                tfMatricula.setText("");
                String nombre = tfNom.getText();
                tfNom.setText("");
                int semestre = Integer.parseInt(tfSemestre.getText());
                tfSemestre.setText("");
                String carrera = tfCarrera.getText();
                tfCarrera.setText("");
                char grupo = tfGrupo.getText().charAt(0);
                tfGrupo.setText("");
                char genero = tfGenero.getText().charAt(0);
                tfGenero.setText("");
                Personal p = new Personal(tipo, matricula, nombre, semestre, carrera, grupo, genero);
                c.Insertar(p);
            } else {
                if (cbDocente.isSelected() == true) {
                    String matricula = tfMatricula.getText();
                    tfMatricula.setText("");
                    String nombre = tfNom.getText();
                    tfNom.setText("");
                    tipo = 'D';
                    int semestre = 0;
                    tfSemestre.setText("");
                    String carrera = tfCarrera.getText();;
                    tfCarrera.setText("");
                    char grupo = ' ';
                    tfGrupo.setText("");
                    char genero = tfGenero.getText().charAt(0);
                    tfGenero.setText("");
                    Personal p = new Personal(tipo, matricula, nombre, semestre, carrera, grupo, genero);
                    c.Insertar(p);
                }
                if (cbAdministrativo.isSelected() == true) {
                    String matricula = tfMatricula.getText();
                    tfMatricula.setText("");
                    String nombre = tfNom.getText();
                    tfNom.setText("");
                    tipo = 'S';
                    int semestre = 0;
                    tfSemestre.setText("");
                    String carrera = tfCarrera.getText();
                    tfCarrera.setText("");
                    char grupo = ' ';
                    tfGrupo.setText("");
                    char genero = tfGenero.getText().charAt(0);
                    tfGenero.setText("");
                    Personal p = new Personal(tipo, matricula, nombre, semestre, carrera, grupo, genero);
                    c.Insertar(p);
                }

            }
            Escribir();
            JOptionPane.showMessageDialog(null, "Agregado");
            LlenarTabla();
        }


    }//GEN-LAST:event_bAgregarAluActionPerformed

    private void tfMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMatriculaActionPerformed
    }//GEN-LAST:event_tfMatriculaActionPerformed

    private void cbAdministrativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdministrativoActionPerformed
    }//GEN-LAST:event_cbAdministrativoActionPerformed

    private void bAgregarAlu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarAlu1ActionPerformed
        fmrInfo newframe = new fmrInfo();
        newframe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAgregarAlu1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (p.Llena() == true) {
            JOptionPane.showMessageDialog(null, "Sin combis disponibles");
        } else {
            int bandera;
            int personas = getNoPersonas();
            if (personas != 3) {
                personas++;
                setNoPersonas(personas);
                Personal personaSube = c.Quitar();
                ArrayCombi.AgregarPersona(personaSube);
                Escribir();
                LlenarTabla();
                bandera = JOptionPane.showConfirmDialog(null, "desea subir a mas personas a la combi?");
                if (bandera == 0) {
                }
                if (bandera == 1) {
                    JOptionPane.showMessageDialog(null, "La combi se fue con " + ArrayCombi.tamannio() + " personas abordo");
                    List<Personal> copiaCombi = ArrayCombi.ConsultaPersonas();
                    Personal[] conversion = new Personal[copiaCombi.size()];
                    conversion = copiaCombi.toArray(conversion);
                    HashMap<Integer, Personal[]> mapa = new HashMap<>();
                    String posicion = String.valueOf((int) Math.random());
                    int auxNumero = 5;
                    if (p.getUltimo() == null) {
                        auxNumero = 45 + sumaPares(5);
                    } else {
                        HashMap<Integer, Personal[]> aux = p.getUltimo();
                        for (Map.Entry<Integer, Personal[]> x : aux.entrySet()) {
                            if (x.getKey() == null) {
                                auxNumero += sumaPares(auxNumero);
                            } else {
                                auxNumero += (x.getKey() + sumaPares(auxNumero) + 45);
                            }
                        }
                    }
                    mapa.put((Integer.parseInt(posicion + auxNumero)), conversion);
                    p.insertar(mapa);
                    personas = 0;
                    setNoPersonas(personas);
                    ArrayCombi = null;
                    ArrayCombi = new PersonasArrayList();
                    mostrarJAreaCombis();
                }
                if (bandera == 2) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "La combi esta llena");
                JOptionPane.showMessageDialog(null, "La combi se fue con " + ArrayCombi.tamannio() + " personas abordo");
                List<Personal> copiaCombi = ArrayCombi.ConsultaPersonas();
                Personal[] conversion = new Personal[copiaCombi.size()];
                conversion = copiaCombi.toArray(conversion);
                HashMap<Integer, Personal[]> mapa = new HashMap<>();
                String posicion = String.valueOf((int) Math.random());
                int auxNumero = 5;
                if (p.getUltimo() == null) {
                    auxNumero = 100 + sumaPares(5);
                } else {
                    HashMap<Integer, Personal[]> aux = p.getUltimo();
                    for (Map.Entry<Integer, Personal[]> x : aux.entrySet()) {
                        if (x.getKey() == null) {
                            auxNumero += sumaPares(auxNumero);
                        } else {
                            auxNumero += (x.getKey() + sumaPares(auxNumero) + 15);
                        }
                    }
                }
                mapa.put((Integer.parseInt(posicion + auxNumero)), conversion);
                p.insertar(mapa);
                personas = 0;
                setNoPersonas(personas);
                ArrayCombi = null;
                ArrayCombi = new PersonasArrayList();
                mostrarJAreaCombis();
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAgregarAlu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarAlu2ActionPerformed
        // TODO add your handling code here:
        MostrarEstadisticas();
    }//GEN-LAST:event_bAgregarAlu2ActionPerformed

    private void cbAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAlumnoActionPerformed

//
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fmrColectivosMapache.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fmrColectivosMapache.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fmrColectivosMapache.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fmrColectivosMapache.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fmrColectivosMapache().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregarAlu;
    private javax.swing.JButton bAgregarAlu1;
    private javax.swing.JButton bAgregarAlu2;
    private javax.swing.JCheckBox cbAdministrativo;
    private javax.swing.JCheckBox cbAlumno;
    private javax.swing.JCheckBox cbDocente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jtextCombis;
    private javax.swing.JTextArea jtextEstadistica;
    private javax.swing.JTable tblRegistro;
    private javax.swing.JTextField tfCarrera;
    private javax.swing.JTextField tfGenero;
    private javax.swing.JTextField tfGrupo;
    private javax.swing.JTextField tfMatricula;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfSemestre;
    // End of variables declaration//GEN-END:variables
}
