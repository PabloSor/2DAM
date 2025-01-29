package org.iesch.ad.DemoFiltros_ModelosUsuario.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void afterPropertiesSet() {
        setRealmName("IESCH");
        super.afterPropertiesSet();
    }
}
