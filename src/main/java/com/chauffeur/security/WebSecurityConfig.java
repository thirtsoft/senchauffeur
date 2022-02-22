package com.chauffeur.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.chauffeur.security.jwt.JwtAuthEntryPoint;
import com.chauffeur.security.jwt.JwtAuthTokenFilter;
import com.chauffeur.security.service.UserDetailsServiceImpl;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			//	registry.addMapping("/**").allowedOrigins("https://senchauffeur.herokuapp.com");
				

            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/registerUser").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()
                .antMatchers("/**/chauffeurs/createWithFiles").permitAll()
                .antMatchers("/**/chauffeurs/update/**").permitAll() 
                .antMatchers("/**/chauffeurs/all").permitAll() 
                .antMatchers("/**/chauffeurs/searchChauffeurOrderByIdDesc").permitAll() 
                .antMatchers("/**/chauffeurs/**").permitAll() 
                .antMatchers("/**/chauffeurs/searchChauffeursByPermis/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByKeyword/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByDisponibilite/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/photoChauffeur/**").permitAll()
                .antMatchers("/**/chauffeurs/cvChauffeur/**").permitAll()
                .antMatchers("/**/chauffeurs/downloadContratFile/**").permitAll()
                .antMatchers("/**/chauffeurs/NumbersOfChauffeurs").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByDisponibityByPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByLocalityPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByPermisPageables/**").permitAll()
                .antMatchers("/**/recruteurs/all").permitAll()
                .antMatchers("/**/recruteurs/create").permitAll()
                .antMatchers("/**/recruteurs/**").permitAll()
                .antMatchers("/**/recruteurs/update/*").permitAll()
                .antMatchers("/**/recruteurs/NumbersOfRecruteurs").permitAll()
                .antMatchers("/**/annonces/create").permitAll()
                .antMatchers("/**/annonces/createAnnonceWithUser/**").permitAll()
                .antMatchers("/**/annonces/update/*").permitAll()
                .antMatchers("/**/annonces/updateStatusOfAnnonce/*").permitAll()
                .antMatchers("/**/annonces/findById/*").permitAll()
                .antMatchers("/**/annonces/findAnnonceByCustomerId/{userId}").permitAll()
                .antMatchers("/**/annonces/all").permitAll()
                .antMatchers("/**/annonces/searchAnnonceOrderByIdDesc").permitAll()
                .antMatchers("/**/annonces/searchbyReference/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByKeyword/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByLibelle/**").permitAll()
                .antMatchers("/**/annonces/search5LatestAnnonceByIdDesc").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusEncours").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusPending").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusValide").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusRejet").permitAll()
                .antMatchers("/**/annonces/searchAnnonceBySelectedIsTrue").permitAll()
                .antMatchers("/**/annonces/searchAnnoncesByPermis/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByCustomerIdOrderByIdDesc/{id}").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByPageables/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByPermisPageables/**").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonces").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceByStatusPending").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceInMonth").permitAll()
                .antMatchers("/**/annonces/numberOfAnnonceByMonth").permitAll()
                .antMatchers("/**/annonces/numberOfAnnonceByYear").permitAll()
                .antMatchers("/**/addresses/create").permitAll()
                .antMatchers("/**/addresses/findById/*").permitAll()
                .antMatchers("/**/addresses/update/*").permitAll()
                .antMatchers("/**/addresses/all").permitAll()
                .antMatchers("/**/addresses/searchAddressOrderByIdDesc").permitAll()
                .antMatchers("/**/addresses/**").permitAll()
                .antMatchers("/**/notifications/createRatingToChauffeur/**").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/searchAllNotificationsOrderByIdDesc").permitAll()
                .antMatchers("/**/notifications/create").permitAll()
                .antMatchers("/**/notifications/update/**").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotificationByChauffeurId/{idChauff}").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByChauffeurId/{idChauff}").permitAll()
                .antMatchers("/**/permis/**").permitAll()
                .antMatchers("/**/permis/create").permitAll()
                .antMatchers("/**/permis/update/**").permitAll()
                .antMatchers("/**/permis/all").permitAll()
                .antMatchers("/**/permis/searchPermisOrderByIdDesc").permitAll()
                .antMatchers("/**/tarifs/all").permitAll() 
                .antMatchers("/**/tarifs/searchTarifsOrderByIdDesc").permitAll() 
                .antMatchers("/**/tarifs/**").permitAll() 
                .antMatchers("/**/tarifs/create").permitAll() 
                .antMatchers("/**/tarifs/update/**").permitAll()
                .antMatchers("/**/tarifs/searchTarifsByAnnonce/**").permitAll()
                .antMatchers("/**/tarifs/searchTarifByPageables/**").permitAll()
                .antMatchers("/**/tarifs/searchTarifByAnnoncePageables/**").permitAll()
                
                .antMatchers("/**/jetons/create").permitAll()
                .antMatchers("/**/jetons/update/*").permitAll()
                .antMatchers("/**/jetons/searchJetonsByIdDesc").permitAll()
                .antMatchers("/**/jetons/delete/{idJeton}").permitAll()
                .antMatchers("/**/jetons/findById/*").permitAll()
                .antMatchers("/**/jetons/*").permitAll()
                .antMatchers("/**/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc").permitAll()
                .antMatchers("/**/historiqueAnnonces/findById/{idHistoriqueAnnonce}").permitAll()
                .antMatchers("/**/historiqueAnnonces/create").permitAll()
                .antMatchers("/**/historiqueAnnonces/update/{idHistoriqueAnnonce}").permitAll()
                .antMatchers("/**/historiqueAnnonces/**").permitAll()
                .antMatchers("/**/newsleters/searchNewsleterOrderByIdDesc").permitAll()
                .antMatchers("/**/newsleters/**").permitAll()
                .antMatchers("/**/newsleters/delete/{idNewsleter}").permitAll()
                .antMatchers("/**/newsleters/*").permitAll()
                .antMatchers("/**/newsleters/**").permitAll()
                .antMatchers("/**/historiqueLogins/searchHistoriqueLoginByIdDesc").permitAll()
                .antMatchers("/**/historiqueLogins/*").permitAll()
                .antMatchers("/**/historiqueLogins/**").permitAll() 
                
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/searchAllUtilisateurOrderByIdDesc").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/*").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                
                
                
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
