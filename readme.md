## Enunciado

Diseñar una aplicación que posibilite consultar la siguiente información sobre una base de datos filmográfica:

- Consultar películas dado un director. Mostrará todas las películas dirigidas por el director proporcionado, ofreciendo título, actores, año de estreno y mediante un botón, el cartel de la película.

- Consultar películas dado un actor. Mostrará los mismos datos que en la consulta por director, salvo que también se incluirá el nombre del director.

- Consultar películas según año de estreno. Mostrará exactamente la misma información que en la consulta basada en el nombre de un actor, pero ajustándose el criterio específico del año.

** Nombre aplicacion: ejercicio2ajax **

Nota: La base de datos la diseñará cada alumno según su mejor criterio.  El nombre de la base de datos será film<Apellido1><Apellido2>. El nombre del datasource será dsfilm.

## DataSource:
                <xa-datasource jndi-name="java:jboss/datasources/dsfilm" pool-name="dsfilm" enabled="true" use-ccm="true">
                    <xa-datasource-property name="ServerName">
                        localhost
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                        filmromangarcia
                    </xa-datasource-property>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
                </xa-datasource>

