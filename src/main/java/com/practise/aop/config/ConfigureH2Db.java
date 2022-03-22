package com.practise.aop.config;

import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;

@Configuration
@EnableJdbcRepositories
public class ConfigureH2Db extends AbstractJdbcConfiguration {
	
    @Bean
    public DataSource dataSource() throws SQLException {                                                   

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DataSource ds = builder.setType(EmbeddedDatabaseType.H2).setName("testdb").build();
		return ds;
    }
//
//    @Bean
//    public ConnectionBuilder createConnectionBuilder (DataSource dataSource) throws SQLException {                                                   
//        return dataSource.createConnectionBuilder().user("sa").password("");
//    }
//    
//    @Bean
//    public Connection connection(ConnectionBuilder connectionBuilder) throws SQLException {                                                   
//        return connectionBuilder.build();
//    }
    
    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) { 
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {                     
        return new DataSourceTransactionManager(dataSource);
    }
}
