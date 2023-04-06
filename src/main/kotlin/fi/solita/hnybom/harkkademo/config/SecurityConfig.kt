package fi.solita.hnybom.harkkademo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain


/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Value("\${auth0.audience}")
    private lateinit var audience: String

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private lateinit var issuer: String

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests()
            .requestMatchers("/**").permitAll()
            //.requestMatchers("/api/public").permitAll()
            //.requestMatchers("/api/private/**").authenticated()
            .and().cors()
            //.and().oauth2ResourceServer().jwt()
        return http.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {

        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation<JwtDecoder>(issuer) as NimbusJwtDecoder
        val audienceValidator: OAuth2TokenValidator<Jwt> = AudienceValidator(audience)
        val withIssuer = JwtValidators.createDefaultWithIssuer(issuer)
        val withAudience: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)
        jwtDecoder.setJwtValidator(withAudience)
        return jwtDecoder
    }
}