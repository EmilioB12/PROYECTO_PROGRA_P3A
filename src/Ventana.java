import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JTabbedPane tabbedPane1;
    private JTextField textField1nombreUsuario;
    private JTextField textField1idBanner;
    private JComboBox<String> comboBox1tipoPersona;
    private JTextField textField1placa;
    private JComboBox<String> comboBox2tipoAutomovil;
    private JButton agregarUsuarioButton;
    private JButton editarUsuarioButton;
    private JButton buscarButton;
    private JButton eliminarUsuarioButton;
    private JTextField textField1nompreParqueadero;
    private JTextField textField1espacioParqueadero;
    private JButton editarParqueaderoButton;
    private JButton agregarParqueaderoButton1;
    private JButton buscarParqueaderoButton;
    private JList list1;
    private JButton eliminarParqueaderoButton;
    private JList list2;
    private JTextField textField1idUsuarioPlaca;
    private JButton agregarVehiculosButton;
    private JButton eliminarVehiculosButton;
    private JTextField textField1placaeliminar;
    private JSpinner spinner1Nivel;
    private JPanel Ventana;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JPanel pestanaLogin;
    private JPanel pestanaUsuarios;
    private JPanel pestanaVehiculos;
    private JPanel pestanaParqueadero;
    private JPanel pestanaReserva;
    private JComboBox comboDia;
    private JSpinner spinnerHoras;
    private JComboBox comboParqueaderoDisp;
    private JTextField textPlacaReserva;
    private JTextField textPersonaReserva;
    private JButton reservarButton;
    private JButton modificarReservaButton;
    private JButton eliminarReservaButton;
    private JButton buscarReservaButton;

    private Lista personas = new Lista();
    private ListaParqueadero parqueaderos = new ListaParqueadero();
    private ListaReserva reserva = new ListaReserva();

    // Definir credenciales de administrador
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    public Ventana() {
        quemarDatos();
        llenarJlistUsuarios();
        System.out.println(personas.listarPersonas());

        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (textField1nombreUsuario.getText().isEmpty() ||
                            textField1idBanner.getText().isEmpty() ||
                            comboBox1tipoPersona.getSelectedItem() == null) {

                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (validarStringLetras(textField1nombreUsuario.getText().trim())) {
                            personas.adicionarElementos(new Persona(textField1nombreUsuario.getText(), textField1idBanner.getText(), comboBox1tipoPersona.getSelectedItem().toString()));
                            JOptionPane.showMessageDialog(null, "Se ha agreagado el usuario correctamente\n" +
                                    "\nNombre: " + textField1nombreUsuario.getText() +
                                    "\nID: " + textField1idBanner.getText() +
                                    "\nTipo de persona: " + comboBox1tipoPersona.getSelectedItem().toString()
                            );
                            limpiarDatos();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresar solo letras en el nombre de usuario");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                llenarJlistUsuarios();
                System.out.println(personas.listarPersonas());
            }
        });

        editarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField1nombreUsuario.getText().isEmpty() ||
                            textField1idBanner.getText().isEmpty() ||
                            comboBox1tipoPersona.getSelectedItem() == null) {

                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        personas.editar(
                                textField1nombreUsuario.getText(),
                                textField1idBanner.getText(),
                                comboBox1tipoPersona.getSelectedItem().toString()
                        );
                        JOptionPane.showMessageDialog(null, "Se ha modificado el usuario correctamente\n" +
                                "\nNombre: " + textField1nombreUsuario.getText() +
                                "\nID: " + textField1idBanner.getText() +
                                "\nTipo de persona: " + comboBox1tipoPersona.getSelectedItem().toString()
                        );

                        // Habilitar los campos para agregar otro usuario
                        textField1nombreUsuario.setEnabled(true);
                        textField1idBanner.setEnabled(true);
                        comboBox1tipoPersona.setEnabled(true);

                        // Limpia los campos después de editar al usuario
                        limpiarDatos();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                llenarJlistUsuarios();
                System.out.println(personas.listarPersonas());
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuscar = textField1idBanner.getText();
                Persona persona = personas.buscarPersona(idBuscar);
                if (persona != null) {
                    textField1nombreUsuario.setText(persona.getNombre());
                    textField1nombreUsuario.setEnabled(false);
                    textField1idBanner.setEnabled(true);
                    comboBox1tipoPersona.setSelectedItem(persona.getTipoPersona());
                    comboBox1tipoPersona.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el ID de banner proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuscar = textField1idBanner.getText();
                try {
                    personas.eliminarPersona(idBuscar);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                    limpiarDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                llenarJlistUsuarios();
            }
        });
        agregarParqueaderoButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarStringLetras(textField1nompreParqueadero.getText().trim())) {
                    try {
                        // Crear un nuevo objeto Parqueadero con los datos ingresados
                        Parqueadero parqueadero = new Parqueadero(textField1nompreParqueadero.getText(),
                                Integer.parseInt(textField1espacioParqueadero.getText()),
                                new Espacio(Integer.parseInt(spinner1Nivel.getValue().toString())));
                        parqueaderos.agregarParqueaderoP(parqueadero);

                        JOptionPane.showMessageDialog(null, "Se ha agregado el Parqueadero correctamente\n" +
                                "\nNombre del Parqueadero: " + textField1nompreParqueadero.getText() +
                                "\nEspacio por nivel del parqueadero: " + Integer.parseInt(textField1espacioParqueadero.getText()) +
                                "\nNiveles del parqueadero: " + Integer.parseInt(spinner1Nivel.getValue().toString()));

                        // Imprimir la numeración de los espacios
                        parqueadero.imprimirEspacios();

                        limpiarDatosParqueadero();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al agregar parqueadero. Solo se permiten números en el campo de espacio");
                    }
                    llenarJlistParqueaderos();
                    System.out.println(parqueaderos.listarParqueadero());
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresar solo letras para el nombre del parqueadero");
                }
            }
        });
        editarParqueaderoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreParqueadero = textField1nompreParqueadero.getText();
                    int espacioParqueadero = Integer.parseInt(textField1espacioParqueadero.getText());
                    int nivelParqueadero = Integer.parseInt(spinner1Nivel.getValue().toString());

                    parqueaderos.editarParqueadero(nombreParqueadero, espacioParqueadero, nivelParqueadero);

                    JOptionPane.showMessageDialog(null, "Se ha modificado el Parqueadero correctamente\n" +
                            "\nNombre del Parqueadero: " + nombreParqueadero +
                            "\nEspacio del parqueadero: " + espacioParqueadero +
                            "\nNivel del parqueadero: " + nivelParqueadero);

                    Parqueadero parqueadero = parqueaderos.buscarParqeuadero(nombreParqueadero);
                    if (parqueadero != null) {
                        parqueadero.imprimirEspacios();
                    }

                    System.out.println(parqueaderos.listarParqueadero());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el campo " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                llenarJlistParqueaderos();
            }
        });
        buscarParqueaderoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1nompreParqueadero.getText();
                Parqueadero parqueadero = parqueaderos.buscarParqeuadero(nombre);
                if (parqueadero != null) {
                    textField1nompreParqueadero.setEnabled(true);
                    textField1espacioParqueadero.setText(String.valueOf(parqueadero.getCantidadEspacio()));

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún parqueadero con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        eliminarParqueaderoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buscarLugar = textField1nompreParqueadero.getText();
                try {
                    parqueaderos.eliminarParqueadero(buscarLugar);
                    JOptionPane.showMessageDialog(null, "Parqueadero eliminado correctamente.");
                    limpiarDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar parqueadero: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                llenarJlistParqueaderos();
            }
        });
        agregarVehiculosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idPersona = textField1idUsuarioPlaca.getText();
                    String placa = textField1placa.getText();
                    String tipoVehiculo = comboBox2tipoAutomovil.getSelectedItem().toString();

                    // Verificar si el ID de la persona está vacío
                    if (idPersona.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo ID de Persona está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Validar placa
                    if (!personas.validarPlaca(placa)) {
                        JOptionPane.showMessageDialog(null, "La placa del vehiculo no es valida");
                        return;
                    }
                    // Buscar la persona en la lista utilizando el ID proporcionado
                    Persona persona = personas.buscarPersona(idPersona);

                    // Verificar si se encontró la persona
                    if (persona != null) {
                        // Verificar si la persona ya tiene dos vehículos
                        if (persona.getVehiculos().size() >= 2) {
                            throw new Exception("El límite por persona es dos vehículos.");
                        }

                        // Crear el vehículo
                        Vehiculo vehiculo = new Vehiculo(placa, tipoVehiculo);

                        // Agregar el vehículo a la persona encontrada
                        persona.addVehiculo(vehiculo);

                        JOptionPane.showMessageDialog(null, "Vehículo agregado correctamente a la persona con ID: " + idPersona);

                        // Limpiar los campos después de agregar el vehículo
                        textField1placa.setText("");
                        comboBox2tipoAutomovil.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar vehículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarVehiculosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = textField1placaeliminar.getText();
                try {
                    personas.eliminarVehiculoPorPlaca(placa);
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el vehículo con la placa: " + placa);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar vehículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();

                if (username.equals(adminUsername) && password.equals(adminPassword)) {
                    // Administrador
                    tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaUsuarios), true);
                    tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaParqueadero), true);
                    tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaVehiculos), true);
                    tabbedPane1.setSelectedComponent(pestanaUsuarios);
                    JOptionPane.showMessageDialog(null, "Login de administrador exitoso.");
                } else {
                    // Verificar si el usuario está en la lista de personas
                    Persona persona = personas.buscarPersona(password);
                    if (persona != null && persona.getNombre().equals(username)) {
                        // Usuario
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaUsuarios), false);
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaParqueadero), false);
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaVehiculos), true);
                        tabbedPane1.setSelectedComponent(pestanaVehiculos);
                        JOptionPane.showMessageDialog(null, "Login de usuario exitoso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales de usuario incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaUsuarios), false);
        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaParqueadero), false);
        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaVehiculos), false);
        //hola
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los datos de los campos de texto y combobox
                    String dia = comboDia.getSelectedItem().toString();
                    int horasReserva = (Integer) spinnerHoras.getValue();
                    String nombreParqueadero = comboParqueaderoDisp.getSelectedItem().toString();
                    String placaVehiculo = textPlacaReserva.getText();
                    String nombrePersona = textPersonaReserva.getText();

                    // Validar campos
                    if (dia.isEmpty() || nombreParqueadero.isEmpty() || placaVehiculo.isEmpty() || nombrePersona.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Buscar el parqueadero
                    Parqueadero parqueadero = parqueaderos.buscarParqeuadero(nombreParqueadero);
                    if (parqueadero == null) {
                        JOptionPane.showMessageDialog(null, "Parqueadero no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Buscar el vehículo
                    Vehiculo vehiculo = null;
                    for (Persona p : personas.getPersonas()) {
                        for (Vehiculo v : p.getVehiculos()) {
                            if (v.getPlaca().equals(placaVehiculo)) {
                                vehiculo = v;
                                break;
                            }
                        }
                        if (vehiculo != null) break;
                    }

                    if (vehiculo == null) {
                        JOptionPane.showMessageDialog(null, "Vehículo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Buscar la persona
                    Persona persona = personas.buscarPersonaNombre(nombrePersona);
                    if (persona == null) {
                        JOptionPane.showMessageDialog(null, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Crear una nueva reserva
                    Reserva reserva = new Reserva(horasReserva, dia, parqueadero, vehiculo, persona);

                    // Agregar la reserva a la lista de reservas

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Reserva creada con éxito\n" +
                            "Día: " + dia +
                            "\nHoras: " + horasReserva +
                            "\nParqueadero: " + nombreParqueadero +
                            "\nVehículo: " + placaVehiculo +
                            "\nPersona: " + nombrePersona);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void limpiarDatos() {
        textField1nombreUsuario.setText("");
        textField1idBanner.setText("");
        comboBox1tipoPersona.setSelectedIndex(0);
        textField1placa.setText("");
        comboBox2tipoAutomovil.setSelectedIndex(0);
    }

    public void limpiarDatosParqueadero() {
        textField1nompreParqueadero.setText("");
        textField1espacioParqueadero.setText("");
    }

    public void quemarDatos() {
        try {
            personas.adicionarElementos(new Persona("Israel", "A00107465", "Estudiante"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void llenarJlistUsuarios() {
        DefaultListModel dl = new DefaultListModel<>();
        for (Persona p : personas.getPersonas()) {
            dl.addElement(p);
        }
        list1.setModel(dl);
    }

    public void llenarJlistParqueaderos() {
        DefaultListModel dl = new DefaultListModel<>();
        for (Parqueadero pa : parqueaderos.getParqueaderos()) {
            dl.addElement(pa);
        }
        list2.setModel(dl);
    }

    public static boolean validarStringLetras(String dato) {
        return dato.matches("[a-zA-Z ]*");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
