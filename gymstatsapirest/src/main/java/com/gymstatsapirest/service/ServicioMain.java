package com.gymstatsapirest.service;
import com.gymstatsapirest.model.AutenticacionUsuario;
import com.gymstatsapirest.model.Maquina;
import com.gymstatsapirest.model.Tarifa;
import com.gymstatsapirest.repository.MaquinaRepository;
import com.gymstatsapirest.repository.TarifaRepository;
import com.gymstatsapirest.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ServicioMain
{
    @Autowired
    private TarifaRepository tarifaRepository;
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Autowired
    private Utils utils;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Tarifa> darTarifas()
    {
        return tarifaRepository.findAll();
    }
    public List<Maquina> darMaquina()
    {
        return maquinaRepository.findAll();
    }

    public Utils getUtils() {
        return utils;
    }
    public ResponseEntity<?> login(AutenticacionUsuario autenticacionUsuario)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        autenticacionUsuario.getUsername(),
                        autenticacionUsuario.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public class JwtResponse
    {
        private String token;
        private String type = "Bearer";

        public JwtResponse(String accessToken) {
            this.token = accessToken;
        }

        public String getAccessToken() {
            return token;
        }

        public void setAccessToken(String accessToken) {
            this.token = accessToken;
        }

        public String getTokenType() {
            return type;
        }

        public void setTokenType(String tokenType) {
            this.type = tokenType;
        }
    }
}

