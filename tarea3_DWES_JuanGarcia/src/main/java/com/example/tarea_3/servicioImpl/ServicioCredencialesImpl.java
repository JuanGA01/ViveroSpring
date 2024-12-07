package com.example.tarea_3.servicioImpl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.tarea_3.modelo.Credenciales;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.repositorio.CredencialesRepositorio;
import com.example.tarea_3.repositorio.PersonaRepositorio;
import com.example.tarea_3.servicio.ServicioCredenciales;

@Service
public class ServicioCredencialesImpl implements ServicioCredenciales {
    
    private CredencialesRepositorio credencialesRepo;
    private PersonaRepositorio personaRepo;

    @Override
    public String loginUsuario(String usuario, String password) {
        // Buscar las credenciales del usuario en la base de datos
        Optional<Credenciales> credencialesOpt = credencialesRepo.findByUsuario(usuario);
        
        // Si no existe el usuario, retornar un mensaje de error
        if (!credencialesOpt.isPresent()) {
            return "El usuario no existe";
        }
        
        // Verificar si la contraseña es correcta
        Credenciales credenciales = credencialesOpt.get();
        if (credenciales.getPassword().equals(password)) {
            return "Bienvenido " + usuario;
        } else {
            return "Contraseña incorrecta";
        }
        
    }

    @Override
    public Boolean UsuarioCorrecto(String usuario, String password) {
        // Buscar las credenciales del usuario en la base de datos
        Optional<Credenciales> credencialesOpt = credencialesRepo.findByUsuario(usuario);
        
        // Si no existe el usuario, retornar false
        if (!credencialesOpt.isPresent()) {
            return false;
        }
        
        // Verificar si la contraseña es correcta
        Credenciales credenciales = credencialesOpt.get();
        return credenciales.getPassword().equals(password);
    }

    @Override
    public Boolean UsuarioExistente(String usuario) {
        // Buscar las credenciales del usuario
        Optional<Credenciales> credencialesOpt = credencialesRepo.findByUsuario(usuario);
        
        // Si el usuario no existe, retorna false
        return credencialesOpt.isPresent();
    }
    

    @Override
    public String GuardarCredenciales(Long idPersona, Credenciales credenciales) {
        // Verificar si el usuario ya existe antes de guardarlo
        if (!UsuarioExistente(credenciales.getUsuario())) {
            // Recuperar la entidad Persona asociada al idPersona
            Persona persona = personaRepo.findById(idPersona)
                    .orElseThrow(() -> new RuntimeException("La persona con ID " + idPersona + " no existe."));

            // Configurar la entidad Credenciales
            Credenciales credencialesNuevas = new Credenciales();
            credencialesNuevas.setId(idPersona); // Reutiliza el ID de Persona como clave primaria
            credencialesNuevas.setUsuario(credenciales.getUsuario());
            credencialesNuevas.setPassword(credenciales.getPassword());
            // La asociación con Persona se maneja automáticamente gracias a @MapsId
            persona.setCrecenciales(credencialesNuevas);

            // Guardar las credenciales usando el repositorio
            credencialesRepo.save(credencialesNuevas);

            return "Credenciales guardadas correctamente";
        } else {
            return "Error: El usuario ya existe.";
        }
    }
    
    @Override
    public Credenciales obtenerCredencialesAutenticadas(String usuario, String password) {
        return credencialesRepo.findByUsuario(usuario)
                .filter(cred -> cred.getPassword().equals(password)) // Comparación directa de contraseñas
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas."));
    }

    
}

