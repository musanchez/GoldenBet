package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.UsuarioDto;
import com.goldenBet.GoldenBet.models.Usuario;
import com.goldenBet.GoldenBet.repository.IRepositoryUsuario;
import lombok.SneakyThrows;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("ServiceUsuario")
public class ServiceUsuario implements IServiceUsuario {

    private final IRepositoryUsuario repositoryUsuario;

    @Autowired
    public ServiceUsuario(
            @Qualifier("RepoUsuario") IRepositoryUsuario usuarioRepo
    ) {
        repositoryUsuario = usuarioRepo;
    }

    @Override
    @SneakyThrows
    public void create(UsuarioDto usuarioDto) {
        String userPassword = usuarioDto.getClave();

        if(!isPasswordValid(userPassword))
            throw new Exception("ERROR, contraseña no cumple con los parámetros requeridos");

        Usuario usuario = new Usuario();

        System.out.println(repositoryUsuario.count());

        usuario.setId("usr-" + (repositoryUsuario.count() + 1));
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setCedula(usuarioDto.getCedula());
        usuario.setNombre(usuarioDto.getNombre());

        //CONTRASEÑA
        //usuario.setPassword(usuarioDto.getClave());
        String encryptedPassword = generateRandomEncryptedPassword();
        usuario.setPassword(encryptedPassword);

        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setPregunta(usuarioDto.getPregunta());
        usuario.setUsername(usuarioDto.getUsuario());
        usuario.setRespuesta(usuarioDto.getRespuesta());
        usuario.setSexo(usuarioDto.getSexo());

        repositoryUsuario.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return repositoryUsuario.findAll();
    }

    @Override
    public void delete(String id) {
        repositoryUsuario.deleteById(id);
    }

    private boolean isPasswordValid(String password) {
        // Check if the password is at least 8 characters in length
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains at least 1 uppercase letter
        if (!containsUppercase(password)) {
            return false;
        }

        // Check if the password contains at least 1 special (non-alphanumeric) character
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        // Check if the password contains at least 1 number
        if (!containsNumber(password)) {
            return false;
        }

        // If all checks pass, the password is valid
        return true;
    }

    private boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialCharacter(String password) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean containsNumber(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    //generar la salt
    private String generateRandomSalt(int length) {
        SecureRandom random = new SecureRandom();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder salt = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            salt.append(characters.charAt(random.nextInt(characters.length())));
        }
        return salt.toString();
    }

    private String generateRandomEncryptedPassword() {
        SecureRandom random = new SecureRandom();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_+=<>?";

        StringBuilder password = new StringBuilder(50);
        for (int i = 0; i < 50; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String salt = generateRandomSalt(16); // Genera una sal aleatoria de 16 caracteres
        textEncryptor.setPassword(salt);

        return "{ENC}" + textEncryptor.encrypt(password.toString());
    }
}
