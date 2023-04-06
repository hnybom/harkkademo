package fi.solita.hnybom.harkkademo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import javax.sql.DataSource


@Configuration
class JdbcDataSourceConfig {

    @Autowired
    lateinit var environment: Environment

}