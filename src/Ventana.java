        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.time.LocalDate;
        import java.time.temporal.ChronoUnit;
        import java.util.ArrayList;
        import java.util.List;

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
    private JComboBox comboDiaReserva;
    private JSpinner spinnerHorasReserva;
    private JComboBox comboParqueaderoReserva;
    private JTextField textPlacaReserva;
    private JTextField textPersonaReserva;
    private JButton reservarButton;
    private JButton modificarReservaButton;
    private JButton eliminarReservaButton;
    private JButton buscarReservaButton;
    private JList list3Reserva;
            private JTextField textMesReserva;
    private JButton ENTRARButton;
    private JButton SALIRButton;
    private JComboBox comboBoxPlacaReserva;
    private JSpinner spinnerDiasReserva;
    private JTextField textAnioReserva;
            private JComboBox comboBoxHorasReserva;

            private Lista personas = new Lista();
    private ListaParqueadero parqueaderos = new ListaParqueadero();
    private ListaReservaParqueadero reservasParqueadero = new ListaReservaParqueadero();



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
                        Parqueadero parqueadero = new Parqueadero(textField1nompreParqueadero.getText(),
                                Integer.parseInt(textField1espacioParqueadero.getText()),
                                new Espacio(Integer.parseInt(spinner1Nivel.getValue().toString())));
                        parqueaderos.agregarParqueaderoP(parqueadero);

                        JOptionPane.showMessageDialog(null, "Se ha agregado el Parqueadero correctamente\n" +
                                "\nNombre del Parqueadero: " + textField1nompreParqueadero.getText() +
                                "\nEspacio por nivel del parqueadero: " + Integer.parseInt(textField1espacioParqueadero.getText()) +
                                "\nNiveles del parqueadero: " + Integer.parseInt(spinner1Nivel.getValue().toString()));

                        parqueadero.imprimirEspacios();

                        limpiarDatosParqueadero();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al agregar parqueadero. Solo se permiten números en el campo de espacio");
                    }
                    llenarJlistParqueaderos();
                    llenarComboParqueaderoReserva(); // Llenar el combo box después de agregar
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

                    Parqueadero parqueadero = parqueaderos.buscarParqueadero(nombreParqueadero);
                    if (parqueadero != null) {
                        parqueadero.imprimirEspacios();
                    }

                    System.out.println(parqueaderos.listarParqueadero());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el campo " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                llenarJlistParqueaderos();
                llenarComboParqueaderoReserva(); // Llenar el combo box después de editar
            }
        });
        buscarParqueaderoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1nompreParqueadero.getText();
                Parqueadero parqueadero = parqueaderos.buscarParqueadero(nombre);
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
                llenarComboParqueaderoReserva(); // Llenar el combo box después de eliminar
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
                        persona.actualizarComboBox(comboBoxPlacaReserva); // Actualiza el ComboBox con las nuevas placas

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

                    llenarComboPlacaReserva(); // Actualizar el combobox después de eliminar el vehículo
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
                    textField1idUsuarioPlaca.setEnabled(true);
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
                        // Habilitar las pestañas relevantes para el usuario
                        textField1idUsuarioPlaca.setEnabled(false);
                        textField1idUsuarioPlaca.setText(textField2.getText());
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaUsuarios), false);
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaParqueadero), false);
                        tabbedPane1.setEnabledAt(tabbedPane1.indexOfComponent(pestanaVehiculos), true);
                        tabbedPane1.setSelectedComponent(pestanaVehiculos);

                        // Mostrar mensaje de éxito y cargar los datos del usuario en la GUI
                        JOptionPane.showMessageDialog(null, "Login de usuario exitoso.");
                        textField1nombreUsuario.setText(persona.getNombre());
                        textField1idBanner.setText(persona.getId());
                        comboBox1tipoPersona.setSelectedItem(persona.getTipoPersona());

                        // Llenar la lista de vehículos del usuario si existen
                        DefaultListModel<Vehiculo> vehiculosModel = new DefaultListModel<>();
                        for (Vehiculo vehiculo : persona.getVehiculos()) {
                            vehiculosModel.addElement(vehiculo);
                        }
                        list2.setModel(vehiculosModel); // Cambiar a list2 o al componente correcto

                        // Actualizar comboBoxPlacaReserva con las placas del usuario
                        comboBoxPlacaReserva.removeAllItems();
                        for (Vehiculo vehiculo : persona.getVehiculos()) {
                            comboBoxPlacaReserva.addItem(vehiculo.getPlaca());
                        }

                        // Actualizar otras partes de la GUI relacionadas con el usuario, según sea necesario
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

        spinnerDiasReserva.setModel(new SpinnerNumberModel(1, 1, 31, 1));

        LocalDate fechaActual = LocalDate.now();
        textMesReserva.setText(String.valueOf(fechaActual.getMonthValue()));
        textAnioReserva.setText(String.valueOf(fechaActual.getYear()));
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Obtener los datos de los campos de texto y combobox
                    int dia = Integer.parseInt(spinnerDiasReserva.getValue().toString());
                    int mes = Integer.parseInt(textMesReserva.getText());
                    int anio = Integer.parseInt(textAnioReserva.getText());

                    int horasReserva = (Integer) spinnerHorasReserva.getValue();
                    String nombreParqueadero = comboParqueaderoReserva.getSelectedItem().toString();
                    String placaVehiculo = comboBoxPlacaReserva.getSelectedItem().toString();
                    String nombrePersona = textField1.getText();


                    // Validar campos
                    if (nombreParqueadero.isEmpty() || placaVehiculo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validar fecha y hora
                    LocalDate fechaReserva = LocalDate.of(anio, mes, dia);
                    LocalDate fechaActual = LocalDate.now();
                    long diasDeDiferencia = ChronoUnit.DAYS.between(fechaActual, fechaReserva);

                    if (diasDeDiferencia < 1 || diasDeDiferencia > 2) {
                        JOptionPane.showMessageDialog(null, "La reserva debe hacerse mínimo 1 día antes y máximo 2 días antes.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Buscar el parqueadero
                    Parqueadero parqueadero = parqueaderos.buscarParqueadero(nombreParqueadero);
                    if (parqueadero == null) {
                        JOptionPane.showMessageDialog(null, "Parqueadero no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Buscar el vehículo
                    Vehiculo vehiculo = null;
                    for (Persona p : personas.getPersonas()) {
                        for (Vehiculo v : p.getVehiculos()) {
                            if (v.getPlaca().equalsIgnoreCase(placaVehiculo)) {
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
                    double precio = horasReserva * 0.75; // Suponiendo un precio fijo por hora
                    ReservaParqueadero nuevaReserva = new ReservaParqueadero(horasReserva, dia, mes, anio, parqueadero, vehiculo, persona, horasReserva, precio);

                    // Asignar espacio en el parqueadero
                    int espacioAsignado = parqueadero.asignarEspacioDisponible();
                    if (espacioAsignado == -1) {
                        JOptionPane.showMessageDialog(null, "No hay espacios disponibles en el parqueadero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Agregar la reserva a la lista de reservas
                    reservasParqueadero.agregarReserva(nuevaReserva);

                    // Actualizar el JList de reservas
                    llenarJlistReserva();

                    // Mostrar mensaje de éxito con detalles adicionales
                    JOptionPane.showMessageDialog(null, "Reserva creada con éxito\n" +
                            "Fecha: "+dia+"/"+mes+"/"+anio+
                            "\nHoras: " + horasReserva +
                            "\nParqueadero: " + nombreParqueadero +
                            "\nVehículo: " + placaVehiculo +
                            "\nPersona: " + nombrePersona +
                            "\nEspacio asignado: " + espacioAsignado +
                            "\nCosto de estancia: $" + precio);


                    imprimirDatosReserva(nuevaReserva);

                    fechaActual = LocalDate.now();
                    textMesReserva.setText(String.valueOf(fechaActual.getMonthValue()));
                    textAnioReserva.setText(String.valueOf(fechaActual.getYear()));

                    limpiarCamposReserva();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textMesReserva.setEnabled(false);
        textAnioReserva.setEnabled(false);

    }

    private void imprimirDatosReserva(ReservaParqueadero reserva) {
        System.out.println("Datos de la reserva:");
        System.out.println("Día: " + reserva.getDia());
        System.out.println("Horas de reserva: " + reserva.getHorasReserva());
        System.out.println("Parqueadero: " + reserva.getParqueadero().getLugar());
        System.out.println("Vehículo: " + reserva.getVehiculo().getPlaca());
        System.out.println("Persona: " + reserva.getPersona().getNombre());
        System.out.println("Tiempo de reserva: " + reserva.getTiempoReserva() + " horas");
        System.out.println("Precio: $" + reserva.getPrecio());
        System.out.println();
    }

    private void limpiarCamposReserva() {
        spinnerDiasReserva.setValue(1);
        spinnerHorasReserva.setValue(0);
        comboParqueaderoReserva.setSelectedIndex(0);
        comboBoxPlacaReserva.setSelectedIndex(0);
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

    public void quemarDatos1() {
        try {
            personas.adicionarElementos(new Persona("Israel", "A00107465", "Estudiante"));
            personas.adicionarElementos(new Persona("Guille", "A00102030", "Estudiante"));
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
        DefaultListModel<Parqueadero> dl = new DefaultListModel<>();
        for (Parqueadero parqueadero : parqueaderos.getParqueaderos()) {
            dl.addElement(parqueadero);
        }
        list2.setModel(dl);
    }
    public void llenarJlistReserva() {
        DefaultListModel<ReservaParqueadero> dl1 = new DefaultListModel<>();
        for (ReservaParqueadero reserva : reservasParqueadero.getReservas()) {
            dl1.addElement(reserva);
        }
        list3Reserva.setModel(dl1);
    }

    public void llenarComboParqueaderoReserva() {
        comboParqueaderoReserva.removeAllItems();  // Limpiar el combobox
        for (Parqueadero parqueadero : parqueaderos.getParqueaderos()) {
            comboParqueaderoReserva.addItem(parqueadero.getLugar());
        }
    }

    public void llenarComboPlacaReserva() {
        comboBoxPlacaReserva.removeAllItems();  // Limpiar el combobox
        for (Persona persona : personas.getPersonas()) {
            for (Vehiculo vehiculo : persona.getVehiculos()) {
                comboBoxPlacaReserva.addItem(vehiculo.getPlaca());
            }
        }
    }

    public static boolean validarStringLetras(String dato) {
        return dato.matches("[a-zA-Z ]*");
    }

            public void quemarDatos() {
                try {
                    // Crear vehículos
                    Vehiculo vehiculo1 = new Vehiculo("ABC1234", "Automovil");
                    Vehiculo vehiculo2 = new Vehiculo("XYZ5678", "Automovil");
                    Vehiculo vehiculo3 = new Vehiculo("LMN9012", "Moto");
                    Vehiculo vehiculo4 = new Vehiculo("JKL3456", "Moto");

                    // Crear listas de vehículos
                    List<Vehiculo> vehiculos1 = new ArrayList<>();
                    vehiculos1.add(vehiculo1);
                    vehiculos1.add(vehiculo2);

                    List<Vehiculo> vehiculos2 = new ArrayList<>();
                    vehiculos2.add(vehiculo3);
                    vehiculos2.add(vehiculo4);

                    // Crear personas
                    Persona persona1 = new Persona("Israel", "A00107465", "Estudiante", vehiculos1);
                    Persona persona2 = new Persona("Guillermo", "A00102030", "Profesor", vehiculos2);

                    // Agregar personas a la lista
                    personas.adicionarElementos(persona1);
                    personas.adicionarElementos(persona2);

                } catch (Exception e) {
                    System.out.println("Error al quemar datos: " + e.getMessage());
                }
            }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}